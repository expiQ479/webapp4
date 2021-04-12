package es.codeurjc.gameweb.rest;
import java.net.URI;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.gameweb.models.*;
import es.codeurjc.gameweb.repositories.PostRepository;
import es.codeurjc.gameweb.services.*;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import javax.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/api")
public class PostsControllerREST {
    @Autowired
    private PostService pService;    
    @Autowired
    private GameService gamePostService;
    @Autowired
    private PostRepository postRepo;

    @GetMapping("/listPosts")
    public Collection<Post> getPosts(){
        return pService.findAll();
    }
    @GetMapping("/listPosts/{id}")
    public ResponseEntity<Post> getIndividualPost(@PathVariable long id){
        Post post=pService.findById(id).get();
        if(post!=null){
            return ResponseEntity.ok(post);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/listPosts/{gameID}")
    public ResponseEntity<Post> createPost(@ModelAttribute Post post,@PathVariable long gameID){
        post.setFromGame(gamePostService.findById(gameID).get());
        post.setTheType(PostType.Guides);
        pService.save(post);
        URI location=fromCurrentRequest().path("/listPosts/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location).body(post);
    }
    @DeleteMapping("/listPosts/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable long id){
        Post post=pService.findById(id).get();
        if(post!=null){
            pService.deleteById(id);
            return ResponseEntity.ok(post);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    
}
