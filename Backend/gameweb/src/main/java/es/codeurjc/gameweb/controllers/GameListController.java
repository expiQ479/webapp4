package es.codeurjc.gameweb.controllers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import es.codeurjc.gameweb.models.Game;
import es.codeurjc.gameweb.models.Genres;
import es.codeurjc.gameweb.services.GamePostService;

@Controller
public class GameListController {
    @Autowired
    private CommonFunctions commonFunctions;
    @Autowired
    private GamePostService gamePostService;

    @PostMapping("/searchGames")
	public String editBookProcess(Model model, boolean Horror,boolean Shooter,boolean Action,
    boolean Platformer, boolean Sports, boolean Puzzles, boolean Narrative, boolean RPG){
        commonFunctions.getSession(model);
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

    
    
}
