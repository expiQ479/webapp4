package es.codeurjc.gameweb.models;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;

public class UpdatePost extends Post {
    public String prevText;
    public ArrayList<String> listOfUpdates;
    public UpdatePost(String title, Date creationDate, Date updateDate, Game fromGame, ArrayList<String> listOfUpdates,String prevText){
        super(title, creationDate, updateDate, fromGame);
        this.prevText=prevText;
        this.listOfUpdates=listOfUpdates;
        this.prevText=prevText;
    }

    
}
