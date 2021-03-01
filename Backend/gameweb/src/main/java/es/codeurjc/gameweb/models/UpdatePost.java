package es.codeurjc.gameweb.models;

import java.sql.Date;
import java.util.List;

public class UpdatePost extends Post {
    public List<String> listOfUpdates;
    public UpdatePost(String title, Date creationDate, Date updateDate, Game fromGame, List<String> listOfUpdates){
        super(title, creationDate, updateDate, fromGame);
        this.listOfUpdates=listOfUpdates;
    }

    
}
