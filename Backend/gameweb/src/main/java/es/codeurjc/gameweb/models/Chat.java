package es.codeurjc.gameweb.models;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
public class Chat {

    private ArrayList<Message> listMessages; // Esto estará en base de datos
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    public Chat(ArrayList<Message> listMessages) {
        this.listMessages = listMessages;
    }
    public Chat() {
        listMessages = new ArrayList<Message>();
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long iD) {
        this.ID = iD;
    }

    public ArrayList<Message> getListMessages() {
        return listMessages;
    }

    public void setListMessages(ArrayList<Message> listMessages) {
        this.listMessages = listMessages;
    }

}