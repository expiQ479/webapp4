package es.codeurjc.gameweb.controllers;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.gameweb.models.Game;
import es.codeurjc.gameweb.models.Genres;

import es.codeurjc.gameweb.services.GamePostService;
import es.codeurjc.gameweb.services.ImageService;

@Controller
public class GamePostsController {
	@Autowired
	private GamePostService gamePostService;
	@Autowired
	private CommonFunctions commonFunctions;
	@Autowired
	private ImageService imagePostService;

	private static final String IMAGES = "images";

	@PostMapping("adminUpdates/GameAdded")
	public String newPost(Model model, Game game, MultipartFile image, @RequestParam Genres genre) throws IOException {
        commonFunctions.getSession(model);
		gamePostService.save(game);
		imagePostService.saveImage(IMAGES, game.getId(), image);	
		return "savedGame";
	}
	@PostMapping("/adminUpdates/{id}/Gameedited")
	public String editPost(Model model, Game game,MultipartFile image,@RequestParam Genres genre,@PathVariable long id) throws IOException {
        commonFunctions.getSession(model);
		gamePostService.update(game,id);
		imagePostService.saveImage(IMAGES, id, image);
		return "savedGame";
	}
	
	@GetMapping("/games/{id}/image")	
	public ResponseEntity<Object> downloadImage(@PathVariable int id) throws MalformedURLException {

		return imagePostService.createResponseFromImage(IMAGES, id);		
	}
	
    /*@GetMapping("/Juego/{id}")
	public String showPost(Model model, @PathVariable long id) {

		Game game = gamePostService.findById(id);
		model.addAttribute("game", game);

		return "show_post";
	}*/
	
	@GetMapping("/game/{id}/delete")
	public String deletePost(Model model, @PathVariable long id) {
		commonFunctions.getSession(model);
		gamePostService.deleteById(id);
		return "gameDeleted";
	}
}
