package es.codeurjc.gameweb.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.gameweb.models.Game;
import es.codeurjc.gameweb.models.User;
import es.codeurjc.gameweb.services.GamePostService;
import es.codeurjc.gameweb.services.UserService;

@Controller
public class ProfileController {
    
    @Autowired
    private CommonFunctions commonFunctions;
    @Autowired
    private UserService userService;

    @Autowired
    private GamePostService gamePostService;

    @PostMapping("/Profile")
    public String changeName(Model model, @RequestParam String name) {
        List<User> users = userService.findAll();
        boolean encontrado= false;
        for(int i=0; i<users.size(); i++){
            if(users.get(i).getInfo().equals(name)){
                encontrado= true;
            }
        }
        if(!encontrado){
            commonFunctions.getU().setInfo(name);
            userService.save(commonFunctions.getU());
            model.addAttribute("user", commonFunctions.getU());
            commonFunctions.getSession(model);
            return "Profile";
        }
        model.addAttribute("customMessage", "Ya existe ese nombre de usuario");
        return "savedGame";
    }

    @RequestMapping("/Subscriptions")
    public String showSubscriptions(Model model){
        Optional<User> myUser=userService.findById(commonFunctions.getU().getId());
        User User = myUser.get();
        ArrayList<Game> myGames = new ArrayList<>();
        if(commonFunctions.getU().getMyGames()==null){
            myGames = null;
        }
        else{
            for(int i=0; i<User.getMyGames().size(); i++){
                myGames.add(gamePostService.findById(User.getMyGames().get(i)).get());
            }
        }
        model.addAttribute("listaSubs",myGames);
        commonFunctions.getSession(model);
        return "Subscriptions";
    }

    @PostMapping("/CambiarContraseña")
    public String changePass(Model model, @RequestParam String password) {
        commonFunctions.getU().setPassword(password);
        userService.save(commonFunctions.getU());
        model.addAttribute("user", commonFunctions.getU());
        commonFunctions.getSession(model);
        return "Profile";
    }

    @PostMapping("/CambiarFoto")
	public String newFotoUser(Model model, MultipartFile image) throws IOException, SQLException {
        updateImage(commonFunctions.getU(), true, image);
        userService.save(commonFunctions.getU());
        model.addAttribute("user", commonFunctions.getU());
        commonFunctions.getSession(model);	
		return "Profile";
	}

    private void updateImage(User user, boolean removeImage, MultipartFile imageField) throws IOException, SQLException {
		if (!imageField.isEmpty()) {
			user.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			user.setImage(true);
		} else {
			if (removeImage) {
				user.setImageFile(null);
				user.setImage(false);
			} else {
				User dbUser = userService.findById(commonFunctions.getU().getId()).orElseThrow();
				if (dbUser.isImage()) {
					user.setImageFile(BlobProxy.generateProxy(dbUser.getImageFile().getBinaryStream(),
							dbUser.getImageFile().length()));
					user.setImage(true);
				}
			}
		}
	}

}