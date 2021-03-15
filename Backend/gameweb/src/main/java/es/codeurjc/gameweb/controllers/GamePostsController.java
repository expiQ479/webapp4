package es.codeurjc.gameweb.controllers;

import java.io.IOException;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.gameweb.models.Chat;
import es.codeurjc.gameweb.models.Game;
import es.codeurjc.gameweb.models.Genres;

import es.codeurjc.gameweb.services.GamePostService;


@Controller
public class GamePostsController {
	@Autowired
	private GamePostService gamePostService;
	@Autowired
	private CommonFunctions commonFunctions;

	private static final String IMAGES = "images";

	@PostMapping("/GameAdded")
	public String newPost(Model model,MultipartFile imageField, Game game) throws IOException {
        commonFunctions.getSession(model);
		if (!imageField.isEmpty()) {
			game.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			game.setImage(true);
		}
		else
			game.setImage(false);
		game.setChat(new Chat());
		gamePostService.save(game);
		return "savedGame";
	}
	
	/*@PostMapping("/adminUpdates/{id}/Gameedited")
	public String editPost(Model model, Game game,MultipartFile image,@RequestParam Genres genre,@PathVariable long id) throws IOException {
        commonFunctions.getSession(model);
		gamePostService.update(game,id);
		imagePostService.saveImage(IMAGES, id, image);
		return "savedGame";
	}*/
	
	/*@GetMapping("/games/{id}/image")	
	public ResponseEntity<Object> downloadImage(@PathVariable int id) throws MalformedURLException {

		return imagePostService.createResponseFromImage(IMAGES, id);		
	}*/
	
    /*@GetMapping("/Juego/{id}")
	public String showPost(Model model, @PathVariable long id) {

		Game game = gamePostService.findById(id);
		model.addAttribute("game", game);

		return "show_post";
	}*/
	/*
	@GetMapping("/game/{id}/delete")
	public String deletePost(Model model, @PathVariable long id) {
		commonFunctions.getSession(model);
		gamePostService.deleteById(id);
		return "gameDeleted";
	}*/
}
