package es.codeurjc.gameweb.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import es.codeurjc.gameweb.models.User;
import es.codeurjc.gameweb.services.UserService;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommonFunctions commonFunctions;

    @PostMapping("/Registrar")
    public String newUser(Model model, User user) throws IOException {
        user.setLogged(true);
        user.setAdmin(false);
        commonFunctions.getSession(model);
        userService.save(user);
        return "index";
    }

}
