package es.codeurjc.gameweb.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.gameweb.models.Game;
import es.codeurjc.gameweb.models.Message;
import es.codeurjc.gameweb.services.GamePostService;

public class PostsController {
    @Autowired
    private CommonFunctions commonFunctions;
  


    @Autowired
	private GamePostService gamePostService;

    private Game myGame;
    
    @RequestMapping("/listPosts/{id}/{tipoPost}")
    public String newPost(Model model,@PathVariable Long id){  
        /*commonFunctions.getU().addElementToGameList(gamePostService.findById(id));
        commonFunctions.getSession(model); */    
        return "listPosts";  
    }  
}
