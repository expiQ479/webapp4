package es.codeurjc.gameweb.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.codeurjc.gameweb.models.Game;
import es.codeurjc.gameweb.models.Genres;
import es.codeurjc.gameweb.repositories.GameRepository;
import es.codeurjc.gameweb.services.GameService;

@Controller
public class GamesListController {
	@Autowired
	private GameService gamePostService;	
    @Autowired
	private GameRepository gameRepo;
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
    @GetMapping("/gameList/{numPage}")
    public String showListGames(Model model,@PathVariable int numPage) {
        Page<Game> gamePage=gameRepo.findAll(PageRequest.of(numPage, 8));
        model.addAttribute("games", gamePostService.findAll(PageRequest.of(numPage, 8)));
        model.addAttribute("maximo", gamePage.getTotalPages());
        model.addAttribute("canLoadMore", true);
        model.addAttribute("numPage", numPage);
        return "gameList";
    }
	@PostMapping("/gameList/filter/{numPage}")
	public String searchGames(Model model,@PathVariable int numPage, boolean Horror,boolean Shooter,boolean Action,
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
        Page<Game> gamePage=gameRepo.findAll(PageRequest.of(numPage, 8));
        model.addAttribute("games", games);
        model.addAttribute("maximo", gamePage.getTotalPages());
        model.addAttribute("canLoadMore", true);
        model.addAttribute("numPage", numPage);
        model.addAttribute("canLoadMore", false);
		return "gameList";
	}
	
	
	
	

	
	
	
}
