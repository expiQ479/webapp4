package es.codeurjc.gameweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class RegisterController {
    @GetMapping("/RegisterPage") //Cuando en el html se pide esta Url, devolvemos la pagina 
        public String showRegister() {
        return "RegisterPage";
    }
}
