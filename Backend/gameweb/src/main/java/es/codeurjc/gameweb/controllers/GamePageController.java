package es.codeurjc.gameweb.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import es.codeurjc.gameweb.models.*;

@Controller
public class GamePageController {
    /*@Autowired
    private User user;*/
    @RequestMapping(value="/subButton")
    public String subButton(){
        /*ArrayList<Game> games=user.getMyGames();
        for(int i=0;i<games.size();i++){
            System.out.println(games.get(i));
        }*/
        
        return "index";
    }
}
