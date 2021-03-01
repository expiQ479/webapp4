package es.codeurjc.gameweb.controllers;

import java.util.ArrayList;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import es.codeurjc.gameweb.models.*;

@Component
public class CommonFunctions{
    
    
    private RegularUser u = new RegularUser("Kike","12345",null);
    public Model getSession(Model model){
        ArrayList<Game> someGames=new ArrayList<Game>();
        someGames.add(new Game("Nuclear throne", null, null));
        someGames.add(new Game("Pathologic", null, null));
        u.setMyGames(someGames);
        model.addAttribute("logged",u.isLogged());
        model.addAttribute("admin", u.isAdmin());

        return model;
    
}

    public User getU() {
        return u;
    }

    
}
