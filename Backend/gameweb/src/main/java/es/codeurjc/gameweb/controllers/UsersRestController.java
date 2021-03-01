package es.codeurjc.gameweb.controllers;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.gameweb.models.Game;
import es.codeurjc.gameweb.models.Genres;
import es.codeurjc.gameweb.models.User;
import es.codeurjc.gameweb.models.UserRepository;

@RestController
@RequestMapping("/Users")
public class UsersRestController {
    
    @Autowired
    private UserRepository user;

    @PostConstruct
    public void init(){
        ArrayList<Integer> valoraciones = new ArrayList<>();
        valoraciones.add(10);
        valoraciones.add(9);
        valoraciones.add(8);
        ArrayList<Game> suscripciones = new ArrayList<>();
        suscripciones.add(new Game("Stardew valley", valoraciones, "RPG"));
        user.save(new User("Kike", "12345", suscripciones, true));
    }
}
