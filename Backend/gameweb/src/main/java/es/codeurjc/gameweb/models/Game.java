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

    public Game(String gameTitle,ArrayList<Integer> listScores, Genres gameGenre){
        this.gameGenre=gameGenre;
        this.gameTitle=gameTitle;
        this.listScores=listScores;
    }
    
}
