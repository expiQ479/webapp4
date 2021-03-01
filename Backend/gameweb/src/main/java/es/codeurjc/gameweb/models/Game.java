package es.codeurjc.gameweb.models;

import java.util.ArrayList;

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
    public ArrayList<Integer> listScores;
    public Genres gameGenre;
    //imagenes
    
    
}
