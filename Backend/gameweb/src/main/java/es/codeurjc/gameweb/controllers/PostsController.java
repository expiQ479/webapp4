package es.codeurjc.gameweb.controllers;

import java.io.IOException;
import java.sql.SQLException;
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
import es.codeurjc.gameweb.services.PostService;

@Controller
public class PostsController {
    @Autowired
    private CommonFunctions commonFunctions;

    @Autowired
	private GamePostService gamePostService;

    @Autowired
    private PostService pService;
    private Optional<Game> myGame;
     
    @PostMapping("/newPost/{id}")
    public String CreatePost(Model model,@PathVariable Long id,@RequestParam String newTitle,@RequestParam String theType, @RequestParam String postText){ 
        myGame = gamePostService.findById(id);
        Game game =myGame.get();
        Post thePost;
        switch(theType){
            case "Guías":
                thePost=new Post(newTitle, "6/11/21", "6/11/21", commonFunctions.getU().getInfo(), postText, PostType.Guides);
                game.addPost(thePost);
                pService.save(thePost);
                break;
            case "Noticias":
                thePost=new Post(newTitle, "6/11/21", "6/11/21", commonFunctions.getU().getInfo(), postText, PostType.News);
                game.addPost(thePost);
                pService.save(thePost);
                break;
            case "Actualizaciones":
                thePost=new Post(newTitle, "6/11/21", "6/11/21", commonFunctions.getU().getInfo(), postText, PostType.Updates);
                game.addPost(thePost);
                pService.save(thePost);
                break;
        }
        
        commonFunctions.getSession(model);
        model.addAttribute("customMessage", "Post añadido con éxito");
        return "savedGame";  
    }  
    @PostMapping("/editPost/{id}")
    public String editPost(Model model,@PathVariable Long id, @RequestParam String newTitle,@RequestParam String newType,@RequestParam String author,@RequestParam String newPostText)throws IOException, SQLException{
        
        PostType ty=null;
        switch(newType){
            case "Guias":
                ty=PostType.Guides;
                break;
            case "Noticias":
                ty=PostType.News;
                break;
            case "Actualizaciones":
                ty=PostType.Updates;
                break;
        }
        Post theUpdatedOne=pService.findById(id).get();
        theUpdatedOne.setTitle(newTitle);
        theUpdatedOne.setTheType(ty);
        theUpdatedOne.setAuthor(author);
        theUpdatedOne.setPostText(newPostText);
        pService.save(theUpdatedOne);
        commonFunctions.getSession(model);
        model.addAttribute("customMessage", "Post editado con éxito");
        return "savedGame";  
    }
}
