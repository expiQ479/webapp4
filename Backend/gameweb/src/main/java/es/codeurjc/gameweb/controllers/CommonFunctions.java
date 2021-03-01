package es.codeurjc.gameweb.controllers;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import es.codeurjc.gameweb.models.*;

@Component
public class CommonFunctions{
    private RegularUser u = new RegularUser("Kike","12345",null);
    public Model getSession(Model model){
        model.addAttribute("logged",u.isLogged());
        model.addAttribute("admin",u.isAdmin());
        return model;
    }

    public User getU() {
        return u;
    }

    
}
