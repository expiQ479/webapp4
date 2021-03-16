package es.codeurjc.gameweb.controllers;

import java.io.Console;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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

import org.springframework.http.HttpHeaders;

import es.codeurjc.gameweb.services.ChatService;
import es.codeurjc.gameweb.services.GamePostService;
import es.codeurjc.gameweb.services.ImageService;
import es.codeurjc.gameweb.services.PostService;

@Controller
public class NavigationController implements ErrorController {
    @Autowired
    private CommonFunctions commonFunctions;
    @Autowired
    private ChatService chatService;
    @Autowired
    private GamePostService gamePostService;
    @Autowired
    private PostService pService;
    @Autowired
    private static final String IMAGES = "images";
    private Genres genres;
    


    @GetMapping("/")
    public String showIndex(Model model) {
        commonFunctions.getSession(model);
        HashMap<Genres,Integer> amountOfGamesWithGenre=new HashMap<Genres,Integer>();
        for(Genres g : Genres.values()){
            amountOfGamesWithGenre.put(g, 0);
        }
        if(commonFunctions.getU().isLogged()){
            for (Game game : commonFunctions.getU().getMyGames()) {
                amountOfGamesWithGenre.put(game.getGenre(),amountOfGamesWithGenre.get(game.getGenre())+1);
            }
            Map.Entry<Genres,Integer> maxEntry=null;
            for(Map.Entry<Genres,Integer> entry : amountOfGamesWithGenre.entrySet()){
                if(maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0){
                    maxEntry=entry;
                }           
            }

            model.addAttribute("recommendedGames",gamePostService.getNumberOfGames(3, gamePostService.findGamesOfGenre(maxEntry.getKey())) );

        }
        else{
            model.addAttribute("recommendedGames", null);
        }
        
        model.addAttribute("games", gamePostService.findAll());
        return "index";
    }

    @GetMapping("/games/{id}/image")
    public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {

        Optional<Game> game = gamePostService.findById(id);
        if (game.isPresent() && game.get().getImageFile() != null) {

            Resource file = new InputStreamResource(game.get().getImageFile().getBinaryStream());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(game.get().getImageFile().length()).body(file);

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
    @RequestMapping("/showMoreGames") 
    public String showMoreGames(Model model) {
        
        return "showMoreGames";
    }

    
    @RequestMapping("/listPosts/{id}/{theType}")
    public String showListPost(Model model,@PathVariable Long id,@PathVariable String theType){       
        Optional<Game> myGame = gamePostService.findById(id);
        Game game =myGame.get();       
        model.addAttribute("name",game.getGameTitle());
        model.addAttribute("postType", theType);
        PostType ty=null;
        switch(theType){
            case "Guias":
                ty=PostType.Guides;
                break;
            case "Noticias":
                ty=PostType.News;
                break;
            case "Actualizaciones":
                ty=PostType.Updates;
                break;
            default:
            System.out.println("PROBLEMOSN");
                break;
        }
        System.out.println(ty.name());
        ArrayList<Post> toShow=pService.findPostOfType(pService.findPostOfGame(game), ty);
        model.addAttribute("lista", toShow);
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

    @GetMapping("/Index")
    public String SignOff(Model model) {
        commonFunctions.getU().setLogged(false);
        commonFunctions.getSession(model);
        model.addAttribute("games", gamePostService.findAll());
        return "index";
    }

    @GetMapping("/LogInPage")
    public String showLogIn() {
        return "LogInPage";
    }

    

    @RequestMapping("/expandedPost/{id}")
    public String showExpandedPost(Model model, @PathVariable long id) {
        Optional<Post> p=pService.findById(id);
        model.addAttribute("post", p.get());
        commonFunctions.getSession(model);
        return "expandedPost";
    }

    @GetMapping("/Juegos")
    public String showListGames(Model model) {
        commonFunctions.getSession(model);
        model.addAttribute("games", gamePostService.findAll());
        model.addAttribute("genres", genres);
        return "gameList";
    }
    @RequestMapping("/createPostPage/{id}")
    public String showCreatePostPage(Model model, @PathVariable long id) {    
        commonFunctions.getSession(model);
        Optional<Game> game = gamePostService.findById(id);
		if (game.isPresent()) {
			model.addAttribute("game", game.get());
			return "createPostPage";
		} else {
			return "index";
		}
    }
    @GetMapping("/EditarJuego/{id}")
    public String editGame(Model model, @PathVariable long id) {
        commonFunctions.getSession(model);
        Optional<Game> game = gamePostService.findById(id);
		if (game.isPresent()) {
			model.addAttribute("game", game.get());
			return "editGame";
		} else {
			return "index";
		}
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

    public Integer doAverageRatio(ArrayList<Integer> MyScores, Integer index){
        Integer aux = 0;
        Integer numberofindexinthearray = 0;
        for(int i=0;i<=MyScores.size()-1;i++){
            if (MyScores.get(i).equals(index))
                numberofindexinthearray++;
        }
        aux = (numberofindexinthearray*100)/(MyScores.size());
        return aux;
    }

    public float doAverageScore(ArrayList<Integer> MyScores){
        float aux = 0;
        for(int i=0;i<=MyScores.size()-1;i++){
            aux= aux + MyScores.get(i);
        }
        aux = aux/(MyScores.size());
        aux = aux*10;
        aux = Math.round(aux);
        aux = aux/10;
        return aux;
    }

    @RequestMapping("/Estadisticas/{id}")
    public String showGameStats(Model model, @PathVariable Long id) {

        Optional<Game> myGame = gamePostService.findById(id);
        
        Game game = myGame.get();
        model.addAttribute("game", game);
        Integer int1=doAverageRatio(game.getListScores(),1);
        Integer int2=doAverageRatio(game.getListScores(),2);
        Integer int3=doAverageRatio(game.getListScores(),3);
        Integer int4=doAverageRatio(game.getListScores(),4);
        Integer int5=doAverageRatio(game.getListScores(),5);
        model.addAttribute("gamestars1", int1);
        model.addAttribute("gamestars2", int2);
        model.addAttribute("gamestars3", int3);
        model.addAttribute("gamestars4", int4);
        model.addAttribute("gamestars5", int5);
        float myAverage= doAverageScore(game.getListScores());
        game.setAverageScore(myAverage);
        commonFunctions.getSession(model);

        return "gamestadistics";
    }

}
