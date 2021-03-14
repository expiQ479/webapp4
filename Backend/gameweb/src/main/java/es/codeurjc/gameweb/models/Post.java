package es.codeurjc.gameweb.models;

import java.sql.Date;

public class Post {
    public String title;
    public Date creationDate,updateDate;
    public Game fromGame;
    public String postText;
    public Administrator author;
    public PostType theType;
    public Post(String title, Date creationDate, Date updateDate, Game fromGame,Administrator author,String postText,PostType theType){
        this.title=title;
        this.creationDate=creationDate;
        this.updateDate=updateDate;
        this.fromGame=fromGame;
        this.postText=postText;
        this.author=author;
        this.theType=theType;
    }
}
