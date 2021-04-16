package es.codeurjc.gameweb.rest;
 
import java.net.URI;
import java.util.Collection;
import java.util.Optional;
 
import com.fasterxml.jackson.annotation.JsonView;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;
 
import es.codeurjc.gameweb.models.Game;
import es.codeurjc.gameweb.models.Genres;
import es.codeurjc.gameweb.models.Game.gameBasico;
import es.codeurjc.gameweb.services.GameService;
 
@RestController
@RequestMapping("/api")
public class AdminRestController {
 
    @Autowired
    private GameService gameService;
 
    /*@JsonView(gameBasico.class)
    @GetMapping("/adminUpdate")
    public Collection<Game> getGames() {
        return gameService.findAll();
    }*/
    @JsonView(gameBasico.class)
    @GetMapping("/adminUpdate/genres")
    public Collection<Game> getGamesByGenre(@RequestParam String genre) {
        Genres gameGenre;
        switch(genre){
            case "Horror":
                gameGenre=Genres.Horror;
                break;
            case "Action":
                gameGenre=Genres.Action;
                break;
            case "Shooter":
                gameGenre=Genres.Shooter;
                break;
            case "Platformer":
                gameGenre=Genres.Platformer;
                break;
            case "Sports":
                gameGenre=Genres.Sports;
                break;
            case "Puzzles":
                gameGenre=Genres.Puzzles;
                break;
            case "RPG":
                gameGenre=Genres.RPG;
                break;
            default:
                gameGenre=null;
                break;
        }
        return gameService.findGamesOfGenre(gameGenre);
    }
    @JsonView(gameBasico.class)
    @GetMapping("/adminUpdate/page")
    private Collection<Game> getGamesPaginated(@RequestParam int numPage){
        return gameService.findAll(PageRequest.of(numPage, 4)).getContent();
    }
    @JsonView(gameBasico.class)
    @GetMapping("/adminUpdate/{id}")
    public ResponseEntity<Game> getGame(@PathVariable long id) {
 
        Optional<Game> game = gameService.findById(id);
 
        if (game.get() != null) {
            return ResponseEntity.ok(game.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @JsonView(gameBasico.class)
    @PostMapping("/adminUpdates/")
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
 
        gameService.save(game);
 
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(game.getId()).toUri();
 
        return ResponseEntity.created(location).body(game);
    }
 
    @JsonView(gameBasico.class)
    @PutMapping("/adminUpdate/{id}")
    public ResponseEntity<Game> editGame(@PathVariable long id, @RequestBody Game newGame) {
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
    public ResponseEntity<Game> deleteGame(@PathVariable long id) {
        Optional<Game> game = gameService.findById(id);
        if (game.get() != null) {
            gameService.delete(id);
            return ResponseEntity.ok(game.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
 
}
