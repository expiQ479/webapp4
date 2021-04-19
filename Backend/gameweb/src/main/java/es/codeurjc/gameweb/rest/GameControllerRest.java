package es.codeurjc.gameweb.rest;
 
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
 
import javax.servlet.http.HttpServletRequest;
 
import com.fasterxml.jackson.annotation.JsonView;
 
import org.apache.catalina.connector.Response;
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
import org.springframework.web.multipart.MultipartFile;
 
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;
 
import es.codeurjc.gameweb.models.Game;
import es.codeurjc.gameweb.models.Genres;
import es.codeurjc.gameweb.models.User;
import es.codeurjc.gameweb.models.Game.gameBasico;
import es.codeurjc.gameweb.services.AlgorithmService;
import es.codeurjc.gameweb.services.GameService;
import es.codeurjc.gameweb.services.ImageService;
import es.codeurjc.gameweb.services.ScoresService;
import es.codeurjc.gameweb.services.SubscriptionsService;
import es.codeurjc.gameweb.services.UserService;
 
@RestController
@RequestMapping("/api/games")
public class GameControllerRest {
 
    @Autowired
    private GameService gameService;
 
    @Autowired
    private ImageService imageService;
 
    @Autowired
    private UserService userService;
    @Autowired
    private ScoresService scoresService;
    @Autowired
    private AlgorithmService algorithmService;
    @Autowired
    private SubscriptionsService subService;
 
    private static final String POSTS_FOLDER = "gameImages";
 
    @JsonView(gameBasico.class)
    @GetMapping("/")
    public Collection<Game> getGames() {
        return gameService.findAll();
    }
 
    @JsonView(gameBasico.class)
    @GetMapping("/genres")
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
    @GetMapping("/page")
    private Collection<Game> getGamesPaginated(@RequestParam int numPage){
        return gameService.findAll(PageRequest.of(numPage, 4)).getContent();
    }
    @JsonView(gameBasico.class)
    @GetMapping("/{id}")
    public ResponseEntity<Game> getGame(@PathVariable long id) {
 
        Optional<Game> game = gameService.findById(id);
 
        if (game.get() != null) {
            return ResponseEntity.ok(game.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @JsonView(gameBasico.class)
    @GetMapping("/{id}/recommendations")
    public Collection<Game> getRecommendedGames(@PathVariable long id,HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        User user = userService.findByName(principal.getName()).get();
        if(user!=null){
            return gameService.findGamesOfGenre(algorithmService.recommendedAlgorithm(user).getKey());
        }
        else{
            return null;
        }
    }
    @JsonView(gameBasico.class)
    @GetMapping("/bestRated")
    public Collection<Game> getBestRatedGames(){
        return gameService.findBestRatedGames();
 
    }
    @JsonView(gameBasico.class)
    @GetMapping("/{id}/scores")
    public ResponseEntity<HashMap<Long, Integer>> getScores(@PathVariable long id) {
 
        Optional<Game> game = gameService.findById(id);
 
        if (game.get() != null) {
            return ResponseEntity.ok(game.get().getMapScores());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @JsonView(gameBasico.class)
    @PostMapping("/")
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
 
        gameService.save(game);
 
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(game.getId()).toUri();
 
        return ResponseEntity.created(location).body(game);
    }
 
    @JsonView(gameBasico.class)
    @PutMapping("/{id}")
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
    @JsonView(gameBasico.class)
    @PostMapping("/{id}/score")
    public ResponseEntity<Game> setScore(@PathVariable long id,@RequestParam Integer stars,HttpServletRequest request){
        Game game = gameService.findById(id).get();
        if(game!=null){
            gameService.save(scoresService.putScore(request, id, stars));
            return ResponseEntity.ok(game);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Game> deleteGame(@PathVariable long id) {
        Optional<Game> game = gameService.findById(id);
        if (game.get() != null) {
            gameService.delete(id);
            return ResponseEntity.ok(game.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
 
    @PostMapping("/{id}/image")
	public ResponseEntity<Object> uploadImage(@PathVariable long id, @RequestParam MultipartFile imageFile) throws IOException {
        Game game=gameService.findById(id).get();
        if(game!=null){
            URI location = fromCurrentRequest().build().toUri();
 
            game.setImagePath(location.toString());
            gameService.save(game);
 
            imageService.saveImage(POSTS_FOLDER, game.getId(), imageFile);
            return ResponseEntity.created(location).build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
 
	}
    @GetMapping("/{id}/image")
	public ResponseEntity<Object> downloadImage(@PathVariable long id) throws MalformedURLException {
 
		return this.imageService.createResponseFromImage(POSTS_FOLDER, id);
	}
    @JsonView(gameBasico.class)
    @PutMapping("/{gameId}/submits")
    public ResponseEntity<User> uploadSubscriptions(@PathVariable long gameId, HttpServletRequest request) throws IOException {
        Principal principal = request.getUserPrincipal();
        Optional<User> user = userService.findByName(principal.getName());
 
        if( user.get() != null){
 
            userService.save(subService.subscriptionFunction(gameId, user.get()));
 
            return ResponseEntity.ok(user.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @JsonView(gameBasico.class)
    @PutMapping("/{gameId}")
    public ResponseEntity<User> removeSubscriptions(@PathVariable long gameId, HttpServletRequest request) throws IOException {
        Principal principal = request.getUserPrincipal();
        Optional<User> user = userService.findByName(principal.getName());
 
        if( user.get() != null){
            userService.save(subService.unsubscriptionFunction(gameId ,user.get()));
 
            return ResponseEntity.ok(user.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
 
}
