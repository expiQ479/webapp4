package es.codeurjc.gameweb.controllers;

import java.net.MalformedURLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import es.codeurjc.gameweb.models.*;
import es.codeurjc.gameweb.services.GamePostService;
import es.codeurjc.gameweb.services.ImageService;
import es.codeurjc.gameweb.services.ChatService;


@Controller
public class NavigationController{
    @Autowired
    private CommonFunctions commonFunctions;
    @Autowired
	private GamePostService gamePostService;
    @Autowired
	private ImageService imagePostService;
    private static final String IMAGES = "images";
    @Autowired
	private ChatService ChatService;
    @GetMapping("/")
    public String showIndex(Model model) {
        commonFunctions.getSession(model);
        return "index";
    }
    @GetMapping("/adminUpdates")
    public String showAdminGamesPage(Model model) {
        commonFunctions.getSession(model);
        model.addAttribute("games", gamePostService.findAll());

		return "adminUpdates";
	}
    @GetMapping("/game/{id}/image")	
	public ResponseEntity<Object> downloadImage(@PathVariable int id) throws MalformedURLException {
		return imagePostService.createResponseFromImage(IMAGES, id);		
	}
    @GetMapping("/RegisterPage") 
        public String showRegister() {
        return "RegisterPage";
    }
    @GetMapping("/addGame")
    public String addGame(Model model) {
        commonFunctions.getSession(model);
        return "newGame";
    }
    @RequestMapping("/GamePage/{name}") 
    public String showGame(Model model, @PathVariable String name) {
        model.addAttribute("name", name);
        model.addAttribute("Messages",ChatService.findAll());
        
        for (Integer i=0;i<=ChatService.findAll().size()-1;i++){
            if(commonFunctions.getU().getInfo().equals(ChatService.findById(i).getNameUser())) 
            ChatService.findById(i).setMessageWriter(true);
            else
            ChatService.findById(i).setMessageWriter(false);
        }
        commonFunctions.getSession(model);
        
        return "GamePage";
    }
    @RequestMapping("/Profile/{name}") 
    public String showProfile(Model model, @PathVariable String name) {
        model.addAttribute("name", name);
        model.addAttribute("password", "12345");
        commonFunctions.getSession(model);
        return "Profile";
    }

    @RequestMapping("/Profile/{name}/Suscripciones") 
    public String showSuscriptions(Model model, @PathVariable String name) {
        model.addAttribute("name", name);
        commonFunctions.getSession(model);
        return "Suscripciones";
    }

    @GetMapping("/Sesion+Cerrada")
    public String SignOff(Model model) {
        commonFunctions.getU().setLogged(false);
        commonFunctions.getSession(model);
        return "index";
    }
    @GetMapping("/LogInPage") 
    public String showLogIn() {
        return "LogInPage";
    }
    @RequestMapping("/listPosts/{name}")
    public String showListPost(Model model,@PathVariable String name) {
        ArrayList<UpdatePost> myPosts= new ArrayList<UpdatePost>();
        myPosts.add(new UpdatePost("Primero", null, null, null, null,"este es el primer texto"));
        myPosts.add(new UpdatePost("Segundo", null, null, null, null,"este es el sec texto"));
        myPosts.add(new UpdatePost("Tercero", null, null, null, null,"este es el third texto"));
        model.addAttribute("name",name);
        model.addAttribute("lista", myPosts);
        commonFunctions.getSession(model);
        return "listPosts";
    }
    @RequestMapping("/expandedPost/{titlePost}")
    public String showExpandedPost(Model model,@PathVariable String titlePost) {
        model.addAttribute("titlePost",titlePost);
        commonFunctions.getSession(model);
        return "expandedPost";
    }
    @GetMapping("/Juegos")
    public String showListGames(Model model) {
        commonFunctions.getSession(model);
        return "gameList";
    }
    
}
