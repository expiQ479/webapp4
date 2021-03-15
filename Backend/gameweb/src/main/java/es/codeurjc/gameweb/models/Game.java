package es.codeurjc.gameweb.models;

import java.util.ArrayList;

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

    public Game(){}

    public Game(String gameTitle, Genres genre, String description) {
        super();
        this.gameTitle = gameTitle;
        this.genre = genre;
        this.description = description; 

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
        return this.image;
    }

    public void setImage(boolean image) {
        this.image = image;
    }

   

}
