package es.codeurjc.gameweb.models;

import java.sql.Blob;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@Entity
@DynamicUpdate
@Table(name="users")
public class User {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
	private Blob imageFile;

	private boolean image;

    private String info;
    private String password;
    private boolean logged = false;
    private boolean admin = false;

    private ArrayList<Long> myGames;

    public User(){}

    public User(String info, String password, ArrayList<Long> myGames) {
        super();
        this.info = info;
        this.password = password;
        this.myGames= myGames;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isAdmin() {
        return admin;
    }

    public String getInfo() {
        return info;
    }

    public boolean isLogged() {
        return logged;
    }
    public ArrayList<Long> getMyGames(){
        return this.myGames;
    }
    public void setMyGames(ArrayList<Long> myGames){
        this.myGames=myGames;
    }
    public void setLogged(boolean logged) {
        this.logged = logged;
    }
    public void addElementToGameList(Game game){
        if(this.myGames==null){
            this.myGames=new ArrayList<Long>();
            this.myGames.add(game.getId());
        }
        else{
            this.myGames.add(game.getId());
        }
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String contraseña) {
        this.password = contraseña;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Blob getImageFile() {
        return this.imageFile;
    }

    public void setImageFile(Blob imageFile) {
        this.imageFile = imageFile;
    }

    public boolean isImage() {
        return image;
    }

    public void setImage(boolean image) {
        this.image = image;
    }

}