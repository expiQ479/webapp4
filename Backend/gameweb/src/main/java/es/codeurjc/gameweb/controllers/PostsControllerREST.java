package es.codeurjc.gameweb.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;

import es.codeurjc.gameweb.models.*;
import es.codeurjc.gameweb.repositories.PostRepository;
import es.codeurjc.gameweb.services.*;

import javax.servlet.http.HttpServletRequest;
@RestController
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
}
