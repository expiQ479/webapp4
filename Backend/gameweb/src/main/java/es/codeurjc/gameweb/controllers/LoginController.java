package es.codeurjc.gameweb.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.gameweb.models.User;
import es.codeurjc.gameweb.services.UserService;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommonFunctions commonFunctions;

    @PostMapping("/IniciarSesion")
    public String IniciarSesion(Model model, @RequestParam String name, @RequestParam String password) throws IOException {
        User[] userList = userService.findAll().toArray(new User[0]);
        for(int i=0; i<userList.length; i++){
            if(userList[i].getInfo().equals(name) && userList[i].getPassword().equals(password)){
                User user = userList[i];
                user.setLogged(true);
                commonFunctions.setU(user);
                commonFunctions.getSession(model);
                return "index";
            }
        }
        commonFunctions.getSession(model);
        return "LogInPage";
    }

}