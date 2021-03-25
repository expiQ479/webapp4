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
            for(int i=0;i<=numPage;i++){
                List<Game> wantedGames=gamePostService.findByCategoryPage(PageRequest.of(numPage-i, 8), Genres.Horror);
                games.addAll(wantedGames);
            }
        }
        if (Shooter){
            for(int i=0;i<=numPage;i++){
                List<Game> wantedGames=gamePostService.findByCategoryPage(PageRequest.of(numPage-i, 8), Genres.Shooter);
                games.addAll(wantedGames);
            }
        }
        if (Action){
            for(int i=0;i<=numPage;i++){
                List<Game> wantedGames=gamePostService.findByCategoryPage(PageRequest.of(numPage-i, 8), Genres.Action);
                games.addAll(wantedGames);
            }
        }
        if (Platformer){
            for(int i=0;i<=numPage;i++){
                List<Game> wantedGames=gamePostService.findByCategoryPage(PageRequest.of(numPage-i, 8), Genres.Platformer);
                games.addAll(wantedGames);
            }
        }
        if (Sports){
            for(int i=0;i<=numPage;i++){
                List<Game> wantedGames=gamePostService.findByCategoryPage(PageRequest.of(numPage-i, 8), Genres.Sports);
                games.addAll(wantedGames);
            }
        }
        if (Puzzles){
            for(int i=0;i<=numPage;i++){
                List<Game> wantedGames=gamePostService.findByCategoryPage(PageRequest.of(numPage-i, 8), Genres.Puzzles);
                games.addAll(wantedGames);
            }
        }
        if (Narrative){
            for(int i=0;i<=numPage;i++){
                List<Game> wantedGames=gamePostService.findByCategoryPage(PageRequest.of(numPage-i, 8), Genres.Narrative);
                games.addAll(wantedGames);
            }
        }
        if (RPG){
            for(int i=0;i<=numPage;i++){
                List<Game> wantedGames=gamePostService.findByCategoryPage(PageRequest.of(numPage-i, 8), Genres.RPG);
                games.addAll(wantedGames);
            }
        }
        
        model.addAttribute("numPage", numPage);
        model.addAttribute("games", games);
        Page<Game> gamePage=gameRepo.findAll(PageRequest.of(numPage, 8));
        model.addAttribute("canLoadMore", false);
        model.addAttribute("maximo", gamePage.getTotalPages());
		return "gameList";
	}
	
	
	
	

	
	
	
}
