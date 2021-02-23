package es.codeurjc.gameweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {

    @GetMapping("/LogInPage") //Cuando en el html se pide esta Url, devolvemos la pagina
    public String showLogIn() {
        return "LogInPage";
    }
}
