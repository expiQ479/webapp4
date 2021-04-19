package es.codeurjc.gameweb.services;
 
import java.security.Principal;
import java.util.HashMap;
import java.util.Optional;
 
import javax.servlet.http.HttpServletRequest;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import es.codeurjc.gameweb.models.Game;
import es.codeurjc.gameweb.models.User;
@Service
public class ScoresService {
    @Autowired
    private GameService gamePostService;
    @Autowired
    private UserService userService;
    @Autowired
    private AlgorithmService algoService;
    public Game putScore(HttpServletRequest request,long id,Integer stars){
        Optional<Game> myGame = gamePostService.findById(id);
        Game game = myGame.get();
        Principal principal = request.getUserPrincipal();
        Optional<User> myUser= userService.findByName(principal.getName());
        User user =myUser.get();
 
        //we check if the array have the initialized value to change it in case it have it or just add the new one in other case
        if (game.getMapScores().containsValue(0)){
            game.getMapScores().clear();
            game.getMapScores().put(user.getId(), stars);
        }
        else 
            game.getMapScores().put(user.getId(), stars);
 
        //call to the method to do the average and set it on the game parameters
        float myAverage= algoService.doAverageScore(game.getMapScores());
        game.setAverageScore(myAverage);
        return game;
    }
 
    public Integer doAverageRatio(HashMap<Long,Integer> MyScores, Integer index){
        Integer aux = 0;
        Integer numberofindexinthearray = 0;
        for (Integer value : MyScores.values()) {
            if (value.equals(index))
            numberofindexinthearray++;
        }
        aux = (numberofindexinthearray*100)/(MyScores.size());
        return aux;
    }
}
