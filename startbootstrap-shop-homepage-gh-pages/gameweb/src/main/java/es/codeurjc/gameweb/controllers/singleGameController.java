package es.codeurjc.gameweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class singleGameController {
    @RequestMapping("/GamePage/{name}")
    public String showGame(Model model, @PathVariable String name) {
        model.addAttribute("name", name);
        return "GamePage";
    }
}
