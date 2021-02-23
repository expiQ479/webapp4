package es.codeurjc.gameweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

public class ProfileController {
    @Autowired
    private CommonFunctions commonFunctions;
    @RequestMapping("/Profile/{name}") //se pide una url con un atributo
    public String showGame(Model model, @PathVariable String name) {
        model.addAttribute("name", name);
        commonFunctions.getSession(model);
        return "Profile";
    }
}
