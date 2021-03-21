package es.codeurjc.gameweb.controllers;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import es.codeurjc.gameweb.models.Game;
import es.codeurjc.gameweb.models.Post;
import es.codeurjc.gameweb.models.PostType;
import es.codeurjc.gameweb.models.User;
import es.codeurjc.gameweb.services.GamePostService;
import es.codeurjc.gameweb.services.PostService;
import es.codeurjc.gameweb.services.UserService;

@Controller
public class PostsController {

    @Autowired
	private GamePostService gamePostService;

    @Autowired
    private PostService pService;
    private Optional<Game> myGame;
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/newPost/{id}")
    public String CreatePost(Model model,@PathVariable Long id,@RequestParam String newTitle,@RequestParam String theType, @RequestParam String postText,MultipartFile imageField, HttpServletRequest request)throws IOException{ 
        Principal principal = request.getUserPrincipal();
        Optional<User> myUser= userService.findByName(principal.getName());
        User user =myUser.get();
        myGame = gamePostService.findById(id);     
        Game game =myGame.get();
        Post thePost=new Post(newTitle, getCurrentDate(), getCurrentDate(), user.getInfo(), postText,parseType(theType));
        if (imageField != null) {
			thePost.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			thePost.setImage(true);
		}
		else
            thePost.setImage(false);
        game.addPost(thePost);
        pService.save(thePost);
        
        model.addAttribute("customMessage", "Post añadido con éxito");
        return "savedGame";  
    }  
    @PostMapping("/editPost/{id}")
    public String editPost(Model model,@PathVariable Long id, @RequestParam String newTitle,@RequestParam String newType,@RequestParam String author,@RequestParam String newPostText,MultipartFile imageField,boolean removeImage)throws IOException, SQLException{
        
        Post theUpdatedOne=pService.findById(id).get();
        theUpdatedOne.setUpdateDate(getCurrentDate());
        theUpdatedOne.setTitle(newTitle);
        theUpdatedOne.setTheType(parseType(newType));
        theUpdatedOne.setAuthor(author);
        theUpdatedOne.setPostText(newPostText);
        updateImage(theUpdatedOne, removeImage, imageField);
        pService.save(theUpdatedOne);
        model.addAttribute("customMessage", "Post editado con éxito");
        return "savedGame";  
    }
    private String getCurrentDate(){
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yy");
        LocalDateTime now=LocalDateTime.now();
        String newDate=formatter.format(now);
        return newDate;
    }
    private PostType parseType(String theType){
        PostType ty=null;
        switch(theType){
            case "Guías":
                ty=PostType.Guides;
                break;
            case "Noticias":
                ty=PostType.News;
                break;
            case "Actualizaciones":
                ty=PostType.Updates;
                break;
        }
        return ty;
    }
    private void updateImage(Post post, boolean removeImage, MultipartFile imageField) throws IOException, SQLException {
		if (!imageField.isEmpty()) {
			post.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			post.setImage(true);
		} else {
			if (removeImage) {
				post.setImageFile(null);
				post.setImage(false);
			} else {
				// Maintain the same image loading it before updating the book
				Post dbPost = pService.findById(post.getId()).orElseThrow();
				if (dbPost.isImage()) {
					post.setImageFile(BlobProxy.generateProxy(dbPost.getImageFile().getBinaryStream(),
                        dbPost.getImageFile().length()));
					post.setImage(true);
				}
			}
		}
	}
}
