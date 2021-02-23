package es.codeurjc.gameweb.controllers;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import es.codeurjc.gameweb.models.User;

@Component
public class CommonFunctions{
    private User u = new User("Kike","12345");
    public Model getSession(Model model){
        model.addAttribute("logged",u.isLogged());
        return model;
    }

    public User getU() {
        return u;
    }

    
}
