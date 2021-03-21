package es.codeurjc.gameweb.controllers;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.gameweb.models.Chat;
import es.codeurjc.gameweb.models.Game;
import es.codeurjc.gameweb.models.Genres;
import es.codeurjc.gameweb.models.User;
import es.codeurjc.gameweb.services.GamePostService;
import es.codeurjc.gameweb.services.UserService;


@Controller
public class GameController {
	@Autowired
	private GamePostService gamePostService;
	@Autowired
    private UserService userService;
	
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

	@PostMapping("/GameAdded")
	public String newPost(Model model,MultipartFile imageField, Game game) throws IOException {
		if (!imageField.isEmpty()) {
			game.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			game.setImage(true);
		}
		else
			game.setImage(false);
		game.setChat(new Chat());
		gamePostService.save(game);
		model.addAttribute("customMessage", "Juego creado con exito");
		return "savedGame";
	}
	@PostMapping("/searchGames")
	public String editBookProcess(Model model, boolean Horror,boolean Shooter,boolean Action,
    boolean Platformer, boolean Sports, boolean Puzzles, boolean Narrative, boolean RPG){
        List<Game> games = new ArrayList<Game>();
        if (Horror){
            List<Game> wantedGames=gamePostService.findByCategory(Genres.Horror);
            games.addAll(wantedGames);
        }
        if (Shooter){
            List<Game> wantedGames=gamePostService.findByCategory(Genres.Shooter);
            games.addAll(wantedGames);
        }
        if (Action){
            List<Game> wantedGames=gamePostService.findByCategory(Genres.Action);
            games.addAll(wantedGames);
        }
        if (Platformer){
            List<Game> wantedGames=gamePostService.findByCategory(Genres.Platformer);
            games.addAll(wantedGames);
        }
        if (Sports){
            List<Game> wantedGames=gamePostService.findByCategory(Genres.Sports);
            games.addAll(wantedGames);
        }
        if (Puzzles){
            List<Game> wantedGames=gamePostService.findByCategory(Genres.Puzzles);
            games.addAll(wantedGames);
        }
        if (Narrative){
            List<Game> wantedGames=gamePostService.findByCategory(Genres.Narrative);
            games.addAll(wantedGames);
        }
        if (RPG){
            List<Game> wantedGames=gamePostService.findByCategory(Genres.RPG);
            games.addAll(wantedGames);
        }
        model.addAttribute("games", games);
		return "GameList";
	}
	
	@PostMapping("/editGame")
	public String editBookProcess(Model model,MultipartFile imageField, Game game, boolean removeImage)
			throws IOException, SQLException {
		Optional<Game> myGame=gamePostService.findById(game.getId());
		Game g=myGame.get();
		Chat aux = g.getChat();
		updateImage(game, removeImage, imageField);
		game.setChat(aux);
		gamePostService.save(game);
		model.addAttribute("customMessage", "Juego modificado con exito");
		return "savedGame";
	}
	@GetMapping("/game/{id}/delete")
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
		return "savedGame";
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
	
	

	
	
	
}
