package es.codeurjc.gameweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class singleGameController {
    @GetMapping("/GamePage")
    public String showGame(Model model) {
        return "GamePage";
    }
}
