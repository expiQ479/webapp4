package es.codeurjc.gameweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavigationController{
    @Autowired
    private CommonFunctions commonFunctions;
    @GetMapping("/")
    public String showIndex(Model model) {
        commonFunctions.getSession(model);
        return "index";
    }
    @GetMapping("/expandedPost")
    public String showExpandedPost(Model model) {
        commonFunctions.getSession(model);
        return "expandedPost";
    }
    @GetMapping("/RegisterPage") 
        public String showRegister() {
        return "RegisterPage";
    }
    @RequestMapping("/GamePage/{name}") 
    public String showGame(Model model, @PathVariable String name) {
        model.addAttribute("name", name);
        commonFunctions.getSession(model);
        return "GamePage";
    }
    @RequestMapping("/Profile/{name}") 
    public String showProfile(Model model, @PathVariable String name) {
        model.addAttribute("name", name);
        model.addAttribute("password", "12345");
        commonFunctions.getSession(model);
        return "Profile";
    }

    @RequestMapping("/Profile/{name}/Suscripciones") 
    public String showSuscriptions(Model model, @PathVariable String name) {
        model.addAttribute("name", name);
        commonFunctions.getSession(model);
        return "Suscripciones";
    }

    @GetMapping("/Sesion+Cerrada")
    public String SignOff(Model model) {
        commonFunctions.getU().setLogged(false);
        commonFunctions.getSession(model);
        return "index";
    }
    @GetMapping("/LogInPage") 
    public String showLogIn() {
        return "LogInPage";
    }
    @GetMapping("/listPosts")
    public String showListPost(Model model) {
        commonFunctions.getSession(model);
        return "listPosts";
    }
    @GetMapping("/Juegos")
    public String showListGames(Model model) {
        commonFunctions.getSession(model);
        return "gameList";
    }
    
}
