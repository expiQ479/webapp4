package es.codeurjc.gameweb.controllers;

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

    @PostMapping("/Iniciar sesion")
	public String newPost(Model model, User user,Long id) {
        if(userService.equals(user)){
            commonFunctions.setU(userService.findById(id));
            commonFunctions.getU().setLogged(true);
        }
        commonFunctions.getSession(model);
		return "index";
	}

}
