package es.codeurjc.gameweb.models;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@Entity
public class User {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String info;
    private String password;
    private boolean logged = false;
    private boolean admin = false;
    private ArrayList<Game> myGames;

    public User(String info, String password, ArrayList<Game> myGames) {
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
    public ArrayList<Game> getMyGames(){
        return this.myGames;
    }
    public void setMyGames(ArrayList<Game> myGames){
        this.myGames=myGames;
    }
    public void setLogged(boolean logged) {
        this.logged = logged;
    }
    public void addElementToGameList(Game game){
        if(this.myGames==null){
            this.myGames=new ArrayList<Game>();
            this.myGames.add(game);
        }
        else{
            this.myGames.add(game);
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

}
