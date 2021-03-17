package es.codeurjc.gameweb.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ManyToAny;
import java.sql.Blob;

@Entity

public class Post {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
    
    private String title;
    private String creationDate;
    private String updateDate;
    @ManyToOne
    private Game fromGame;
    private String postText;
    //debería ser tipo User pero debido a problemas lo dejare para después y que por ahora solo muestre el nombre en el post expandido.
    private String author;
    private PostType theType;
    @Lob
	private Blob imageFile;
    private boolean image;
    
    public Post(){}
    public Post(String title, String creationDate, String updateDate,String author,String postText,PostType theType){
        super();
        this.title=title;
        this.creationDate=creationDate;
        this.updateDate=updateDate;
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

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public PostType getTheType() {
        return this.theType;
    }
    public void setTheType(PostType theType) {
        this.theType = theType;
    }
    public Blob getImageFile() {
        return this.imageFile;
    }

    public void setImageFile(Blob imageFile) {
        this.imageFile = imageFile;
    }
    public boolean isImage() {
        return this.image;
    }

    public void setImage(boolean image) {
        this.image = image;
    }
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
