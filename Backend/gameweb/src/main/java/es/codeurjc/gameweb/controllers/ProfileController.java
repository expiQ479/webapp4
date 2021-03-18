package es.codeurjc.gameweb.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.gameweb.models.User;
import es.codeurjc.gameweb.services.ImageService;
import es.codeurjc.gameweb.services.UserService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.InputStreamResource;

import org.springframework.http.HttpHeaders;

@Controller
public class ProfileController {
    
    @Autowired
    private CommonFunctions commonFunctions;
    @Autowired
    private UserService userService;
    @Autowired
	private ImageService imageUserService;
	private static final String user = "user_images";

    @PostMapping("/Profile")
    public String changeName(Model model, @RequestParam String name) {
        commonFunctions.getU().setInfo(name);
        userService.save(commonFunctions.getU());
        model.addAttribute("user", commonFunctions.getU());
        commonFunctions.getSession(model);
        return "Profile";
    }

    @RequestMapping("/Subscriptions")
    public String showSubscriptions(Model model){
        Optional<User> myUser=userService.findById(commonFunctions.getU().getId());
        User User = myUser.get();
        model.addAttribute("listaSubs",User.getMyGames());
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