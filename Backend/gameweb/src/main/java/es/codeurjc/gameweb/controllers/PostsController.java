package es.codeurjc.gameweb.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.gameweb.models.Game;
import es.codeurjc.gameweb.models.Post;
import es.codeurjc.gameweb.models.PostType;
import es.codeurjc.gameweb.services.GamePostService;

@Controller
public class PostsController {
    @Autowired
    private CommonFunctions commonFunctions;
  


    @Autowired
	private GamePostService gamePostService;

    private Optional<Game> myGame;
     
    @PostMapping("/listPosts/{id}/{tipoPost}/createPostPage/NewPost")
    public String CreatePost(Model model,@PathVariable Long id,@PathVariable String tipoPost,@RequestParam String newTitle, @RequestParam String postText){ 
        myGame = gamePostService.findById(id);
        Game game =myGame.get();
        Post thePost;
        switch(tipoPost){
            case "Guía":
                thePost=new Post(newTitle, null, null, game, null, postText, PostType.Guides);
                System.out.println(thePost.postText);
                break;
            case "Noticias":
                thePost=new Post(newTitle, null, null, game, null, postText, PostType.Guides);
                System.out.println(thePost.postText);
                break;
            case "Actualizaciones":
                thePost=new Post(newTitle, null, null, game, null, postText, PostType.Guides);
                System.out.println(thePost.postText);
                break;
        }
        commonFunctions.getSession(model);
        return "expandedPost";  
    }  
}
