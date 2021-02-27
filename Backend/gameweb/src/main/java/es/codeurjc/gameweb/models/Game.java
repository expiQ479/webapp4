package es.codeurjc.gameweb.models;

import java.util.List;

public class Game {
    enum Genres{
        Horror,
        Shooter,
        Action,
        Platformer,
        Sports,
        Puzzles,
        Narrative
    }
    public String gameTitle;
    public List<Integer> listScores;
    public Genres gameGenre;
    //imagenes
    
    
}
