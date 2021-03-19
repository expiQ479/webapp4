package es.codeurjc.gameweb.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.gameweb.models.Game;
import es.codeurjc.gameweb.models.Genres;
import es.codeurjc.gameweb.models.User;
import es.codeurjc.gameweb.services.GamePostService;
import es.codeurjc.gameweb.services.UserService;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommonFunctions commonFunctions;

    @Autowired
	private GamePostService gamePostService;

    @PostMapping("/Index")
    public String IniciarSesion(Model model, @RequestParam String name, @RequestParam String password) throws IOException {
        List<User> userList = userService.findAll();
        for(int i=0; i<userList.size(); i++){
            if(userList.get(i).getInfo().equals(name) && userList.get(i).getPassword().equals(password)){
                User user = userList.get(i);
                user.setLogged(true);
                commonFunctions.setU(user);
                commonFunctions.getSession(model);
                model.addAttribute("games", gamePostService.findAll());
                model.addAttribute("whatList", "Recomendados");
                model.addAttribute("nextPage", 1);
                return "index";
            }
        }
        commonFunctions.getSession(model);
        return "LogInPage";
    }

}