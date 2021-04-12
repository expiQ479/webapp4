package es.codeurjc.gameweb.rest;

import java.net.URI;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import es.codeurjc.gameweb.models.User;
import es.codeurjc.gameweb.models.User.userBasico;
import es.codeurjc.gameweb.services.UserService;

@RestController
@RequestMapping("/api")
public class ProfileRestController {

    @Autowired
    private UserService userService;

    @JsonView(userBasico.class)
    @GetMapping("/profile/{id}")
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
    @PostMapping("/register/")
    public ResponseEntity<User> createUser(@RequestBody User user){
        
        userService.save(user);

        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(location).body(user);
    }

    /*@DeleteMapping("/profile/{id}/subscriptions/")
    public ResponseEntity<User> deleteUser(@PathVariable long id){

        Optional<User> user = userService.findById(id);

        if(user.get() != null){
            userService.delete(id);
            return ResponseEntity.ok(user.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }*/

    @JsonView(userBasico.class)
    @PutMapping("/profile/{id}")
    public ResponseEntity<User> editUser(@PathVariable long id, @RequestBody User newUser){
        Optional<User> user = userService.findById(id);

        if( user.get() != null){

            newUser.setId(id);
            userService.save(newUser);

            return ResponseEntity.ok(user.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    
    @JsonView(userBasico.class)
    @GetMapping("/profile/{id}")
    public ResponseEntity<User> getMyGames(@PathVariable long id){

        Optional<User> user = userService.findById(id);

        if(user.get() != null){
            return ResponseEntity.ok(user.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
