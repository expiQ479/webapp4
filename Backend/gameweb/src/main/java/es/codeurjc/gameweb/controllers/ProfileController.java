package es.codeurjc.gameweb.controllers;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
    private UserService userService;

    @Autowired
    private GamePostService gamePostService;

    @PostMapping("/Profile")
    public String changeName(Model model, @RequestParam String name, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        Optional<User> myUser= userService.findByName(principal.getName());
        User user =myUser.get();
        List<User> users = userService.findAll();
        boolean encontrado= false;
        for(int i=0; i<users.size(); i++){
            if(users.get(i).getInfo().equals(name)){
                encontrado= true;
            }
        }
        if(!encontrado){
            user.setInfo(name);
            userService.save(user);
            model.addAttribute("user", user);
            return "Profile";
        }
        model.addAttribute("customMessage", "Ya existe ese nombre de usuario");
        return "savedGame";
    }

    @RequestMapping("/Subscriptions")
    public String showSubscriptions(Model model, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> myUser= userService.findByName(principal.getName());
        User user =myUser.get();
        ArrayList<Game> myGames = new ArrayList<>();
        if(user.getMyGames()==null){
            myGames = null;
        }
        else{
            for(int i=0; i<user.getMyGames().size(); i++){
                myGames.add(gamePostService.findById(user.getMyGames().get(i)).get());
            }
        }
        model.addAttribute("listaSubs",myGames);
        if(user.getMyGames().isEmpty()){
            model.addAttribute("noSubs", "No tienes ninguna subscripcion");
        }
        else{
            model.addAttribute("noSubs", "");
        }
        return "Subscriptions";
    }

    @PostMapping("/CambiarContraseña")
    public String changePass(Model model, @RequestParam String password, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        Optional<User> myUser= userService.findByName(principal.getName());
        User user =myUser.get();
        user.setPassword(password);
        userService.save(user);
        model.addAttribute("user", user);
        return "Profile";
    }

    @PostMapping("/CambiarFoto")
	public String newFotoUser(Model model, MultipartFile image, HttpServletRequest request) throws IOException, SQLException {
        Principal principal = request.getUserPrincipal();
        Optional<User> myUser= userService.findByName(principal.getName());
        User user =myUser.get();
        updateImage(user, true, image);
        userService.save(user);
        model.addAttribute("user", user);	
		return "Profile";
	}

    @RequestMapping("/Subscriptions/{id}")
    public String eliminarSubs(Model model, @PathVariable Long id, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> myUser= userService.findByName(principal.getName());
        User user =myUser.get();
        ArrayList<Game> myGames = new ArrayList<>();
        for(int i=0; i<user.getMyGames().size(); i++){
            if(user.getMyGames().get(i).equals(id)){
                user.getMyGames().remove(user.getMyGames().get(i));
                userService.save(user);
            }
            else{
                myGames.add(gamePostService.findById(user.getMyGames().get(i)).get());
            }
        }
        model.addAttribute("listaSubs", myGames);
        if(user.getMyGames().isEmpty()){
            model.addAttribute("noSubs", "No tienes ninguna subscripcion");
        }
        else{
            model.addAttribute("noSubs", "");
        }
        return "Subscriptions";
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
				User dbUser = userService.findById(user.getId()).orElseThrow();
				if (dbUser.isImage()) {
					user.setImageFile(BlobProxy.generateProxy(dbUser.getImageFile().getBinaryStream(),
							dbUser.getImageFile().length()));
					user.setImage(true);
				}
			}
		}
	}

}