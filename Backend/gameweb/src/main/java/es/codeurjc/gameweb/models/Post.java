package es.codeurjc.gameweb.models;

import java.sql.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity

public class Post {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
    
    private String title;
    private String creationDate;
    private String updateDate;
    @OneToOne
    private Game fromGame;
    private String postText;
    @OneToOne
    private User author;
    private PostType theType;

    
    public Post(){}
    public Post(String title, String creationDate, String updateDate,Game fromGame,User author,String postText,PostType theType){
        super();
        this.title=title;
        this.creationDate=creationDate;
        this.updateDate=updateDate;
        this.fromGame=fromGame;
        this.postText=postText;
        this.author=author;
        this.theType=theType;
    }
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Game getFromGame() {
        return this.fromGame;
    }

    public void setFromGame(Game fromGame) {
        this.fromGame = fromGame;
    }

    public String getPostText() {
        return this.postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public PostType getTheType() {
        return this.theType;
    }

    public void setTheType(PostType theType) {
        this.theType = theType;
    }
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
