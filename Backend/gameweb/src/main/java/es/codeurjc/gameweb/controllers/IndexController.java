package es.codeurjc.gameweb.controllers;

import java.security.Principal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import es.codeurjc.gameweb.services.AlgorithmService;
import es.codeurjc.gameweb.services.GameService;

@Controller
public class IndexController {

    @Autowired
    private GameService gamePostService;

    @Autowired
    private AlgorithmService algorithm;

   @ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {

			model.addAttribute("logged", true);
			model.addAttribute("userName", principal.getName());
			model.addAttribute("admin", request.isUserInRole("ADMIN"));

		} else {
			model.addAttribute("logged", false);
		}
	}
    @GetMapping("/index")
    public String SignOff(Model model) {
        model.addAttribute("games", gamePostService.findBestRatedGames());
        model.addAttribute("whatList", "Mejor valorados");
        model.addAttribute("nextPage", 1);
        return "index";
    } 
    @GetMapping("/")
    public String showIndex(Model model, HttpServletRequest request) {  
        model.addAttribute("games", gamePostService.findBestRatedGames());
        ArrayList<Object> gamesToShow;
        gamesToShow=algorithm.setSomeList(request);
        model.addAttribute("selectedList",gamesToShow.get(1));
        model.addAttribute("whatList", "Mejor valorados");
        model.addAttribute("nextPage", 1);
        return "index";
    }   
}
