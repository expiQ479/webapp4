package es.codeurjc.gameweb.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import es.codeurjc.gameweb.models.*;

@Controller
public class GamePageController {
    @Autowired
    private CommonFunctions commonFunctions;

    @RequestMapping("/GamePage/{name}/subButton")
    public String subButton(@PathVariable String name){    
        ArrayList<Game> someGames=new ArrayList<Game>();
        someGames.add(new Game(name, null, Genres.Action));
        commonFunctions.getU().setMyGames(someGames);
        for(int i=0;i<someGames.size();i++){
            System.out.println(commonFunctions.getU().getMyGames().get(i).gameTitle);
            System.out.println(commonFunctions.getU().getMyGames().get(i).gameGenre.name());
        }  
        return "Suscripciones";  
    }   
}
