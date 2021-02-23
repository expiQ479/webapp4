package es.codeurjc.gameweb.controllers;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class CommonFunctions{
    public Model getSession(Model model){
        model.addAttribute("logged", true);
        return model;
    }
}
