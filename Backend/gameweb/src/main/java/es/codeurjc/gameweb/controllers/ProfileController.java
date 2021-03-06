package es.codeurjc.gameweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.gameweb.services.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class ProfileController {
    
    private static final Path IMAGES_FOLDER = null;
    @Autowired
    private CommonFunctions commonFunctions;
    @Autowired
    private UserService userService;
    
    @PostMapping("/Profile/{name}")
    public String changeName(Model model, @RequestParam String name) {
        model.addAttribute("name", name);
        model.addAttribute("password", commonFunctions.getU().getPassword());
        commonFunctions.getU().setInfo(name);
        userService.deleteById(commonFunctions.getU().getId());
        userService.save(commonFunctions.getU());
        commonFunctions.getSession(model);
        return "Profile";
    }

    @PostMapping("/CambiarContraseña")
    public String changePass(Model model, @RequestParam String password) {
        model.addAttribute("name", commonFunctions.getU().getInfo());
        model.addAttribute("password", password);
        commonFunctions.getU().setPassword(password);
        commonFunctions.getSession(model);
        return "Profile";
    }

    @PostMapping("/upload_image")
    public String uploadImage( @RequestParam MultipartFile image) throws IOException {
        Files.createDirectories(IMAGES_FOLDER);
        Path imagePath = IMAGES_FOLDER.resolve("images");
        image.transferTo(imagePath);
        return "uploaded_image";
    }
    

}