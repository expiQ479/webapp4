package es.codeurjc.gameweb.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.gameweb.models.Game;
import es.codeurjc.gameweb.models.User;
import es.codeurjc.gameweb.services.GamePostService;
import es.codeurjc.gameweb.services.UserService;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
	private GamePostService gamePostService;
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

    @PostMapping("/Index")
    public String IniciarSesion(Model model, @RequestParam String name, @RequestParam String password) throws IOException {
        List<User> userList = userService.findAll();
        for(int i=0; i<userList.size(); i++){
            if(userList.get(i).getInfo().equals(name) && userList.get(i).getPassword().equals(password)){
                model.addAttribute("games", gamePostService.findAll());
                ArrayList<Game> toShow=gamePostService.getNumberOfGames(3, gamePostService.findBestRatedGames());                 
                model.addAttribute("selectedList",toShow);
                model.addAttribute("whatList", "Mejor valorados");
                model.addAttribute("nextPage", 1);
                return "index";
            }
        }
        return "LogInPage";
    }

}