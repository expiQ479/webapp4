package es.codeurjc.gameweb.controllers;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import es.codeurjc.gameweb.models.*;
import es.codeurjc.gameweb.models.Game;
import org.springframework.http.HttpHeaders;

import es.codeurjc.gameweb.services.ChatService;
import es.codeurjc.gameweb.services.GamePostService;
import es.codeurjc.gameweb.services.ImageService;

@Controller
public class NavigationController implements ErrorController {
    @Autowired
    private CommonFunctions commonFunctions;
    @Autowired
    private ChatService chatService;
    @Autowired
    private GamePostService gamePostService;
    @Autowired
    private ImageService imagePostService;
    @Autowired
    private static final String IMAGES = "images";
    private Game myGame;

    @GetMapping("/")
    public String showIndex(Model model) {
        commonFunctions.getSession(model);
        model.addAttribute("games", gamePostService.findAll());
        return "index";
    }

    @GetMapping("/games/{id}/image")
    public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {

        Optional<Game> book = gamePostService.findById(id);
        if (book.isPresent() && book.get().getImageFile() != null) {

            Resource file = new InputStreamResource(book.get().getImageFile().getBinaryStream());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(book.get().getImageFile().length()).body(file);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/adminUpdates")
    public String showAdminGamesPage(Model model) {
        commonFunctions.getSession(model);
        model.addAttribute("games", gamePostService.findAll());

        return "adminUpdates";
    }

    @GetMapping("/game/{id}/image")
    public ResponseEntity<Object> downloadImage(@PathVariable int id) throws MalformedURLException {
        return imagePostService.createResponseFromImage(IMAGES, id);
    }

    @GetMapping("/RegisterPage")
    public String showRegister() {
        return "RegisterPage";
    }

    @GetMapping("/addGame")
    public String addGame(Model model) {
        commonFunctions.getSession(model);
        return "newGame";
    }

    @RequestMapping("/GamePage/{id}")
    public String showGame(Model model, @PathVariable Long id) {

        Optional<Game> myGame = gamePostService.findById(id);
        Optional<Chat> myChat = chatService.findById(id+1);
        Game game = myGame.get();
        Chat chat;
        chat = myChat.get();
        model.addAttribute("game", game);
        // save the ID of the game to connect it to a chat
        // iterate the chat messages to allign them to the right or to the left
        for (Integer i = 0; i <= chat.getListMessages().size() - 1; i++) {
            if (commonFunctions.getU().getInfo().equals(chat.getListMessages().get(i).getNameUser()))
                chat.getListMessages().get(i).setMessageWriter(true);
            else
                chat.getListMessages().get(i).setMessageWriter(false);
        }
        model.addAttribute("Messages", chat.getListMessages());
        commonFunctions.getSession(model);

        return "GamePage";
    }
    @RequestMapping("/morePosts") 
    public String showMorePosts(Model model) {
        
        return "morePosts";
    }



@RequestMapping("/listPosts/{id}/{tipoPost}")
    public String showListPost(Model model,@PathVariable("id") Long id,@PathVariable("tipoPost") String tipoPost) {
        myGame = gamePostService.findById(id);
        ArrayList<UpdatePost> myPosts= new ArrayList<UpdatePost>();
        myPosts.add(new UpdatePost("Primero", null, null, null, null,"este es el primer texto"));
        myPosts.add(new UpdatePost("Segundo", null, null, null, null,"este es el sec texto"));
        myPosts.add(new UpdatePost("Tercero", null, null, null, null,"este es el third texto"));
        model.addAttribute("name",myGame.getGameTitle());
        model.addAttribute("tipoPost", tipoPost);
        model.addAttribute("lista", myPosts);
        commonFunctions.getSession(model);
        return "listPosts";
    }
    @RequestMapping("/Profile") 
    public String showProfile(Model model) {
        model.addAttribute("name", commonFunctions.getU().getInfo());
        model.addAttribute("password", commonFunctions.getU().getPassword());
        commonFunctions.getSession(model);
        return "Profile";
    }

    @RequestMapping("/Profile/{name}/Suscripciones")
    public String showSuscriptions(Model model, @PathVariable String name) {
        model.addAttribute("name", name);
        commonFunctions.getSession(model);
        return "Suscripciones";
    }

    @GetMapping("/Sesion+Cerrada")
    public String SignOff(Model model) {
        commonFunctions.getU().setLogged(false);
        commonFunctions.getSession(model);
        return "index";
    }

    @GetMapping("/LogInPage")
    public String showLogIn() {
        return "LogInPage";
    }

    @RequestMapping("/listPosts/{name}")
    public String showListPost(Model model, @PathVariable String name) {
        ArrayList<UpdatePost> myPosts = new ArrayList<UpdatePost>();
        myPosts.add(new UpdatePost("Primero", null, null, null, null, "este es el primer texto"));
        myPosts.add(new UpdatePost("Segundo", null, null, null, null, "este es el sec texto"));
        myPosts.add(new UpdatePost("Tercero", null, null, null, null, "este es el third texto"));
        model.addAttribute("name", name);
        model.addAttribute("lista", myPosts);
        commonFunctions.getSession(model);
        return "listPosts";
    }

    @RequestMapping("/expandedPost/{titlePost}")
    public String showExpandedPost(Model model, @PathVariable String titlePost) {
        model.addAttribute("titlePost", titlePost);
        commonFunctions.getSession(model);
        return "expandedPost";
    }

    @GetMapping("/Juegos")
    public String showListGames(Model model) {
        commonFunctions.getSession(model);
        model.addAttribute("games", gamePostService.findAll());
        return "gameList";
    }

    @GetMapping("/EditarJuego/{id}")
    public String showListGames(Model model, @PathVariable long id) {
        commonFunctions.getSession(model);
        model.addAttribute("game", gamePostService.findById(id));
        return "editGame";
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error-404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error-500";
            }
        }
        return "error";
    }

    @Override
    public String getErrorPath() {
        return null;
    }

}
