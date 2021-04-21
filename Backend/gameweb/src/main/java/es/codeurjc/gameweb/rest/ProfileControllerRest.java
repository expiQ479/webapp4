package es.codeurjc.gameweb.rest;
 
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
 
import javax.imageio.IIOException;
 
import com.fasterxml.jackson.annotation.JsonView;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
 
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;
 
import es.codeurjc.gameweb.models.User;
import es.codeurjc.gameweb.models.User.userBasico;
import es.codeurjc.gameweb.services.ImageService;
import es.codeurjc.gameweb.services.UserService;
 
@RestController
@RequestMapping("/api/profiles")
public class ProfileControllerRest {
 
    @Autowired
    private UserService userService;
 
    @Autowired
    private ImageService imageService;
 
    private static final String POSTS_FOLDER = "usersPics";
 
    @Autowired
    private PasswordEncoder passwordEncoder;
 
    @JsonView(userBasico.class)
    @GetMapping("/")
    public Collection<User> getAllUsers(){
 
        return userService.findAll();
    }
 
    @JsonView(userBasico.class)
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id){
 
        Optional<User> user = userService.findById(id);
 
        if(user.get() != null){
            return ResponseEntity.ok(user.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
 
    @JsonView(userBasico.class)
    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
 
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
 
        return ResponseEntity.created(location).body(user);
    }
 
    @JsonView(userBasico.class)
    @PutMapping("/{id}")
    public ResponseEntity<User> editUser(@PathVariable long id, @RequestBody User newUser){
        Optional<User> user = userService.findById(id);
 
        if( user.get() != null){
 
            newUser.setId(id);
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            userService.save(newUser);
 
            return ResponseEntity.ok(user.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
 
    @PostMapping("/{id}/image")
	public ResponseEntity<Object> uploadImage(@PathVariable long id, @RequestParam MultipartFile imageFile) throws IOException, SQLException {
        User user=userService.findById(id).get();
        if(user!=null){
            URI location = fromCurrentRequest().build().toUri();
 
            user.setImagePath(location.toString());
            user.setImageFile(BlobProxy.generateProxy(imageFile.getInputStream(), user.getImageFile().length()));
            userService.save(user);
 
            //imageService.saveImage(POSTS_FOLDER, game.getId(), imageFile);
            return ResponseEntity.created(location).build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
 
	}
    @GetMapping("/{id}/image")
	public ResponseEntity<Object> downloadImage(@PathVariable long id) throws MalformedURLException, SQLException {
        User user=userService.findById(id).get();
        if(user!=null){
            Resource file=new InputStreamResource(user.getImageFile().getBinaryStream());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").contentLength(user.getImageFile().length()).body(file);
        }
        else{
            return ResponseEntity.notFound().build();
        }
	}
 
    @JsonView(userBasico.class)
    @GetMapping("/{id}/subscriptions")
    public ResponseEntity<ArrayList<Long>> getSubscriptions(@PathVariable long id){
 
        Optional<User> user = userService.findById(id);
 
        if(user.get() != null){
            return ResponseEntity.ok(user.get().getMyGames());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
 
}