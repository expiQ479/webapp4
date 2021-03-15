package es.codeurjc.gameweb.models;

import java.util.ArrayList;
import java.lang.Math;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import java.sql.Blob;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
public class Game {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
	private Blob imageFile;

	private boolean image;

    private String gameTitle;
    private Genres genre;
    private ArrayList<Integer> listScores = new ArrayList<Integer>();
    private float averageScore;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    @OneToOne(cascade=CascadeType.ALL)
    private Chat chat;


    public Game(String gameTitle, Genres genre, String description) {
        this.gameTitle = gameTitle;
        this.genre = genre;
        this.description = description; 
        this.listScores.add(2);
        this.listScores.add(2);
        this.listScores.add(3);
        this.listScores.add(4);
        this.listScores.add(5);
        this.listScores.add(5);
        this.listScores.add(5);
        this.averageScore=doAverageScore(listScores);
    }
    public Game(){}

    public float doAverageScore(ArrayList<Integer> MyScores){
        float aux = 0;
        for(int i=0;i<=MyScores.size()-1;i++){
            aux= aux + MyScores.get(i);
        }
        aux = aux/(MyScores.size());
        aux = aux*10;
        aux = Math.round(aux);
        aux = aux/10;
        return aux;
    }
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
    public Genres getGenre() {
        return genre;
    }

    public void setGenre(Genres genre) {
        this.genre = genre;
    }

    public float getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
    
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Blob getImageFile() {
        return imageFile;
    }

    public void setImageFile(Blob blob) {
        this.imageFile = blob;
    }

    public boolean isImage() {
        return image;
    }

    public void setImage(boolean image) {
        this.image = image;
    }

   

}
