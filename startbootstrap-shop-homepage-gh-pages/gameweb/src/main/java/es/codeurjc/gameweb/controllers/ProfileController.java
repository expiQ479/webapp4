package es.codeurjc.gameweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class ProfileController {
    private static final Path IMAGES_FOLDER = null;
    @Autowired
    private CommonFunctions commonFunctions;

    @RequestMapping("/Profile/{name}") // se pide una url con un atributo
    public String showProfile(Model model, @PathVariable String name) {
        model.addAttribute("name", name);
        model.addAttribute("password", "12345");
        commonFunctions.getSession(model);
        return "Profile";
    }

    @RequestMapping("/Profile/{name}/Suscripciones") // se pide una url con un atributo
    public String showSuscriptions(Model model, @PathVariable String name) {
        model.addAttribute("name", name);
        commonFunctions.getSession(model);
        return "Suscripciones";
    }

    @GetMapping("/Sesion+Cerrada")
    public String greeting(Model model) {
        commonFunctions.getU().setLogged(false);
        commonFunctions.getSession(model);
        return "index";
    }

    @PostMapping("/Profile/{name}")
    public String cambiarNombre(Model model, @RequestParam String name) {
        model.addAttribute("name", name);
        model.addAttribute("password", commonFunctions.getU().getContraseña());
        commonFunctions.getU().setInfo(name);
        commonFunctions.getSession(model);
        return "Profile";
    }

    @PostMapping("/CambiarContraseña")
    public String cambiarContraseña(Model model, @RequestParam String password) {
        model.addAttribute("name", commonFunctions.getU().getInfo());
        model.addAttribute("password", password);
        commonFunctions.getU().setContraseña(password);
        commonFunctions.getSession(model);
        return "Profile";
    }

    @PostMapping("/upload_image")
    public String uploadImage( @RequestParam MultipartFile image) throws IOException {
        Files.createDirectories(IMAGES_FOLDER);
        Path imagePath = IMAGES_FOLDER.resolve("image.jpg");
        image.transferTo(imagePath);
        return "uploaded_image";
    }

}