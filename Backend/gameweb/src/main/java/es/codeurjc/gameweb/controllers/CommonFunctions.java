package es.codeurjc.gameweb.controllers;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import es.codeurjc.gameweb.models.*;

@Component
public class CommonFunctions{

    private User u = new User(null,null,null);
    public Model getSession(Model model){
        model.addAttribute("logged",u.isLogged());
        model.addAttribute("admin", u.isAdmin());
        return model;
    }

    public User getU() {
        return u;
    }

    public void setU(User user){
        this.u=user;
    }

    
}
