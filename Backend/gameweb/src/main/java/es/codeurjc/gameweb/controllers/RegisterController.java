package es.codeurjc.gameweb.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.gameweb.models.User;
import es.codeurjc.gameweb.services.GamePostService;
import es.codeurjc.gameweb.services.UserService;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommonFunctions commonFunctions;

    @Autowired
	private GamePostService gamePostService;

    @PostMapping("/IndexLogged")
    public String newUser(Model model, @RequestParam String name, @RequestParam String password, @RequestParam String password1) throws IOException {
        List<User> users = userService.findAll();
        boolean encontrado= false;
        for(int i=0; i<users.size(); i++){
            if(users.get(i).getInfo().equals(name)){
                encontrado= true;
            }
        }
        if(!encontrado){
            if(password.equals(password1)){
                ArrayList<Long> myGames = new ArrayList<>();
                User user = new User(name,password,myGames);
                user.setAdmin(false);
                setUserImage(user, "/sample_images/user-image-default.jpg");
                userService.save(user);
                user.setLogged(true);
                commonFunctions.setU(user);
                commonFunctions.getSession(model);
            model.addAttribute("games", gamePostService.findAll());
            model.addAttribute("whatList", "Recomendados");
            model.addAttribute("nextPage", 1);
            return "index";
            }
            else{
                commonFunctions.getSession(model);
                model.addAttribute("problem", "Contraseña de confirmacion incorrecta");
                return "RegisterPage";
            }
        }
        commonFunctions.getSession(model);
        model.addAttribute("problem", "Nombre de usuario ya existente");
        return "RegisterPage";
    }

    public void setUserImage(User user, String classpathResource) throws IOException {
        user.setImage(true);
        Resource image = new ClassPathResource(classpathResource);
        user.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));

    }

}
