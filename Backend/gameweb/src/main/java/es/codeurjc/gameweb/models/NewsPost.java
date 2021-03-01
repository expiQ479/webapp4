package es.codeurjc.gameweb.models;

import java.sql.Date;

public class NewsPost extends Post {
    public Administrator author;
    public NewsPost(String title, Date creationDate, Date updateDate, Game fromGame, Administrator author) {
        super(title, creationDate, updateDate, fromGame);
        this.author=author;

    }
    
}
