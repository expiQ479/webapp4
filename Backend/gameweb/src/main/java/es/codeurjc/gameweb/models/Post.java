package es.codeurjc.gameweb.models;

import java.sql.Date;

public abstract class Post {
    public String title;
    public Date creationDate,updateDate;
    public Game fromGame;
    
    public Post(String title, Date creationDate, Date updateDate, Game fromGame){
        this.title=title;
        this.creationDate=creationDate;
        this.updateDate=updateDate;
        this.fromGame=fromGame;
    }
}
