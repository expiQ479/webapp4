package es.codeurjc.gameweb.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import es.codeurjc.gameweb.models.Game;
import es.codeurjc.gameweb.models.Genres;
import es.codeurjc.gameweb.services.GameService;

@Controller
public class GamesListController {
	@Autowired
	private GameService gamePostService;	
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
    @GetMapping("/gameList/")
    public String showListGames(Model model) {
        model.addAttribute("games", gamePostService.findAll());
        return "gameList";
    }
	
	@PostMapping("/gameList/filter")
	public String searchGames(Model model, boolean Horror,boolean Shooter,boolean Action,
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
		return "gameList";
	}
	
	
	
	

	
	
	
}
