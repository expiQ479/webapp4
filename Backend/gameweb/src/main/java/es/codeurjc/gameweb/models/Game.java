package es.codeurjc.gameweb.models;

import java.util.ArrayList;

public class Game {
    
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
