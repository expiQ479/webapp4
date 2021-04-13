package es.codeurjc.gameweb.services;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.gameweb.models.*;


@Service
public class AlgorithmService {
    @Autowired
    private GameService gamePostService;
    @Autowired
	private UserService userService;

    public Map.Entry<Genres,Integer> recommendedAlgorithm(User user){
        HashMap<Genres,Integer> amountOfGamesWithGenre=new HashMap<Genres,Integer>();
        for(Genres g : Genres.values()){
            amountOfGamesWithGenre.put(g, 0);
        }
        Map.Entry<Genres,Integer> maxEntry=null;
        for (Long game : user.getMyGames()) {
            amountOfGamesWithGenre.put(gamePostService.findById(game).get().getGenre(),amountOfGamesWithGenre.get(gamePostService.findById(game).get().getGenre())+1);
        }
        
        for(Map.Entry<Genres,Integer> entry : amountOfGamesWithGenre.entrySet()){
            if(maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0){
                maxEntry=entry;
            }           
        }
        return maxEntry;
    }
    public ArrayList<Object> setSomeList(HttpServletRequest request){
        ArrayList<Object> toShow = new ArrayList<>();
        ArrayList<Integer> valor = new ArrayList<>();
        try {
            Principal principal = request.getUserPrincipal();
            Optional<User> myUser= userService.findByName(principal.getName());
            User user =myUser.get();
            ArrayList<Game> games = gamePostService.getNumberOfGames(3, gamePostService.findGamesOfGenre(recommendedAlgorithm(user).getKey())); 
            Integer i = 0;
            valor.add(i);
            toShow.add(valor);
            toShow.add(games);             
            return(toShow);
        } catch (Exception e) { 
            ArrayList<Game> games = gamePostService.getNumberOfGames(3, gamePostService.findBestRatedGames());                 
            Integer i = 1;
            valor.add(i);
            toShow.add(valor);
            toShow.add(games);             
            return(toShow);
        }
    }
    
}
