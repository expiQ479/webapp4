package es.codeurjc.gameweb.controllers;
import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.gameweb.models.Chat;
import es.codeurjc.gameweb.models.Game;
import es.codeurjc.gameweb.models.Post;
import es.codeurjc.gameweb.models.PostType;
import es.codeurjc.gameweb.models.User;
import es.codeurjc.gameweb.services.GameService;
import es.codeurjc.gameweb.services.PostService;
import es.codeurjc.gameweb.services.UserService;

@Controller
public class AdminUpdatesController {
    @Autowired
	private GameService gamePostService;
	@Autowired
    private UserService userService;
	@Autowired
    private PostService pService;
	
	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {

			model.addAttribute("logged", true);
			model.addAttribute("userName", principal.getName());
			model.addAttribute("admin", request.isUserInRole("ADMIN"));

		} else {
			model.addAttribute("logged", false);
		}
	}

	@GetMapping("/adminUpdates/")
    public String showAdminGamesPage(Model model) {
        model.addAttribute("games", gamePostService.findAll());

        return "adminUpdates";
    }

    @PostMapping("/adminUpdates/gameAdded")
	public String newGame(Model model,MultipartFile imageField, Game game) throws IOException {
		if (!imageField.isEmpty()) {
			game.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			game.setImage(true);
		}
		else
			game.setImage(false);
		game.setChat(new Chat());
		gamePostService.save(game);
		model.addAttribute("customMessage", "Juego creado con exito");
		return "successPage";
	}

	@GetMapping("/adminUpdates/addGame")
    public String addGame(Model model) {
        return "newGame";
    }

    @PostMapping("/adminUpdates/editGame")
	public String editBookProcess(Model model,MultipartFile imageField, Game game, boolean removeImage)
			throws IOException, SQLException {
		Optional<Game> myGame=gamePostService.findById(game.getId());
		Game g=myGame.get();
		Chat aux = g.getChat();
		updateImage(game, removeImage, imageField);
		game.setChat(aux);
		gamePostService.save(game);
		model.addAttribute("customMessage", "Juego modificado con exito");
		return "successPage";
	}

	@GetMapping("/adminUpdates/editGame/{id}")
    public String editGame(Model model, @PathVariable long id) {
        Optional<Game> game = gamePostService.findById(id);
		if (game.isPresent()) {
			model.addAttribute("game", game.get());
			return "editGame";
		} else {
            model.addAttribute("whatList", "Recomendados");
			return "index";
		}
    }

	@GetMapping("/adminUpdates/delete/{id}")
	public String deletePost(Model model, @PathVariable long id) {
		Optional<Game> game = gamePostService.findById(id);
		if (game.isPresent()) {
			gamePostService.delete(id);
		}
		List<User> users = userService.findAll();
		//It search the users that are suscribe to the game and it remove the game from their suscriptions
		User user;
		for(int i=0; i<users.size(); i++){
			user = users.get(i);
			if(!user.getMyGames().isEmpty()){
				if(user.getMyGames().contains(id)){
					user.getMyGames().remove(id);
					userService.save(user);
				}
			}
		}
		model.addAttribute("customMessage", "El juego se ha eliminado con exito");
		return "successPage";
	}

	@PostMapping("/adminUpdates/newPost/{id}")
    public String CreatePost(Model model,@PathVariable Long id,@RequestParam String newTitle,@RequestParam String theType, @RequestParam String postText,MultipartFile imageField, HttpServletRequest request)throws IOException{ 
        Principal principal = request.getUserPrincipal();
        Optional<User> myUser= userService.findByName(principal.getName());
        User user =myUser.get();
		Optional<Game> myGame = gamePostService.findById(id);     
        Game game =myGame.get();
        Post thePost=new Post(newTitle, getCurrentDate(), getCurrentDate(), user.getInfo(), postText,parseType(theType));
        if (imageField != null) {
			thePost.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			thePost.setImage(true);
		}
		else
            thePost.setImage(false);
        game.addPost(thePost);
        pService.save(thePost);
        model.addAttribute("customMessage", "Post añadido con éxito");
        return "successPage";  
    }

	private void updateImage(Game game, boolean removeImage, MultipartFile imageField) throws IOException, SQLException {
		if (!imageField.isEmpty()) {
			game.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			game.setImage(true);
		} else {
			if (removeImage) {
				game.setImageFile(null);
				game.setImage(false);
			} else {
				// Maintain the same image loading it before updating the book
				Game dbGame = gamePostService.findById(game.getId()).orElseThrow();
				if (dbGame.isImage()) {
					game.setImageFile(BlobProxy.generateProxy(dbGame.getImageFile().getBinaryStream(),
							dbGame.getImageFile().length()));
					game.setImage(true);
				}
			}
		}
	}

	private String getCurrentDate(){
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yy");
        LocalDateTime now=LocalDateTime.now();
        String newDate=formatter.format(now);
        return newDate;
    }

    private PostType parseType(String theType){
        PostType ty=null;
        switch(theType){
            case "Guías":
                ty=PostType.Guides;
                break;
            case "Noticias":
                ty=PostType.News;
                break;
            case "Actualizaciones":
                ty=PostType.Updates;
                break;
        }
        return ty;
    }     
}
