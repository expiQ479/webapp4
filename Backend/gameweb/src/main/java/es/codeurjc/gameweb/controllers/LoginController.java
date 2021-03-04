package es.codeurjc.gameweb.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import es.codeurjc.gameweb.models.User;
import es.codeurjc.gameweb.services.UserService;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommonFunctions commonFunctions;

    @PostMapping("/Iniciar Sesion")
    public String newUser(Model model, User user) throws IOException {
        if(userService.equals(user)){
            user.setLogged(true);
            commonFunctions.setU(user);
            return "index";
        }
        commonFunctions.getSession(model);
        return "LogInPage";
    }

}
