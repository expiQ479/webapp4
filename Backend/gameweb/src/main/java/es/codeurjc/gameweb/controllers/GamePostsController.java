package es.codeurjc.gameweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.codeurjc.gameweb.models.Game;
import es.codeurjc.gameweb.services.GamePostService;


@Controller
public class GamePostsController { 
    @Autowired
	private GamePostService gamePostService;
    @Autowired
    private CommonFunctions commonFunctions;

    @PostMapping("adminUpdates/GameAdded")
	public String newPost(Model model, Game game) {
        commonFunctions.getSession(model);
		gamePostService.save(game);
		return "savedGame";
	}
    /*@GetMapping("/Juego/{id}")
	public String showPost(Model model, @PathVariable long id) {

		Game game = gamePostService.findById(id);
		model.addAttribute("game", game);

		return "show_post";
	}*/
	
	@GetMapping("/post/{id}/delete")
	public String deletePost(Model model, @PathVariable long id) {

		gamePostService.deleteById(id);

		return "deleted_post";
	}
}
