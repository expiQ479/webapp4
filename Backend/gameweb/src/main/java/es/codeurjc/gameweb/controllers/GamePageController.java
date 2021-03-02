package es.codeurjc.gameweb.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.gameweb.models.*;

@Controller
public class GamePageController {
    @Autowired
    private CommonFunctions commonFunctions;

    @RequestMapping("/GamePage/{name}/subButton")
    public String subButton(@PathVariable String name){    
        ArrayList<Game> someGames=new ArrayList<Game>();
        someGames.add(new Game(name, null, null));
        commonFunctions.getU().setMyGames(someGames);
        for(int i=0;i<someGames.size();i++){
            System.out.println(commonFunctions.getU().getMyGames().get(i).getGameTitle());
        }  
        return "Suscripciones";  
    }  

    public float doAverageScore(ArrayList<Integer> MyScores){
        float aux = 0;
        for(int i=0;i<MyScores.size();i++){
            aux= aux + MyScores.get(i);
        }  

        aux = aux/(MyScores.size());

        return aux;
    }
    
    @PostMapping("/GamePage/{name}/score")
    public String score(@PathVariable String name,@RequestParam(name="stars") Integer score){    
        ArrayList<Game> someGames=new ArrayList<Game>();
        Game MyGame = new Game(name, null, null);
        someGames.add(MyGame);
        someGames.get(0).getListScores().add(score);
        someGames.get(0).setAverageScore(doAverageScore(someGames.get(0).getListScores()));
        return "GamePage";
    }   
    
}
