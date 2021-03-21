package es.codeurjc.gameweb.services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import es.codeurjc.gameweb.models.*;


@Service
public class AlgorithmService {
    @Autowired
    private GameService gamePostService;

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
    public ArrayList<Object> setSomeList(User user){
        ArrayList<Object> toShow = new ArrayList<>();
        ArrayList<Integer> valor = new ArrayList<>();
        try {
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
