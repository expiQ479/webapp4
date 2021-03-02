package es.codeurjc.gameweb.models;

import java.util.ArrayList;

public class Game {
    private String gameTitle;

    private String genre;
    private ArrayList<Integer> listScores = new ArrayList<Integer>();
    private float averageScore;
    private String description;
    private Long id;
    public Game(String gameTitle, String genre, String description) {
        this.gameTitle = gameTitle;
        this.genre = genre;
        this.description = description;
    }
    // images

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public ArrayList<Integer> getListScores() {
        return listScores;
    }

    public void setListScores(ArrayList<Integer> listScores) {
        this.listScores = listScores;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public float getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
    }

    

}
