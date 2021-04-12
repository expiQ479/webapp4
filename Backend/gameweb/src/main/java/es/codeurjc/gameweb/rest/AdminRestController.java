package es.codeurjc.gameweb.rest;

import java.net.URI;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import es.codeurjc.gameweb.models.Game;
import es.codeurjc.gameweb.models.Game.gameBasico;
import es.codeurjc.gameweb.services.GameService;

@RestController
@RequestMapping("/api")
public class AdminRestController {

    @Autowired
    private GameService gameService;

    @JsonView(gameBasico.class)
    @GetMapping("/adminUpdate/{id}")
    public ResponseEntity<Game> getUser(@PathVariable long id) {

        Optional<Game> game = gameService.findById(id);

        if (game.get() != null) {
            return ResponseEntity.ok(game.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @JsonView(gameBasico.class)
    @PostMapping("/addGame/")
    public ResponseEntity<Game> createUser(@RequestBody Game game) {

        gameService.save(game);

        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(game.getId()).toUri();

        return ResponseEntity.created(location).body(game);
    }

    @JsonView(gameBasico.class)
    @PutMapping("/adminUpdate/{id}")
    public ResponseEntity<Game> editUser(@PathVariable long id, @RequestBody Game newGame) {
        Optional<Game> game = gameService.findById(id);

        if (game.get() != null) {

            newGame.setId(id);
            gameService.save(newGame);

            return ResponseEntity.ok(game.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/adminUpdate//{id}")
    public ResponseEntity<Game> deletePost(@PathVariable long id) {
        Optional<Game> game = gameService.findById(id);
        if (game.get() != null) {
            gameService.delete(id);
            return ResponseEntity.ok(game.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
