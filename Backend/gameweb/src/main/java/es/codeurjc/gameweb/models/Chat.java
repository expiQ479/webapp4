package es.codeurjc.gameweb.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
public class Chat {
    @OneToMany(cascade=CascadeType.ALL)
    private List<Message> listMessages; // Esto estará en base de datos
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    public Chat(ArrayList<Message> listMessages) {
        this.listMessages = listMessages;
    }
    public Chat() {}

    public Long getID() {
        return ID;
    }

    public void setID(Long iD) {
        this.ID = iD;
    }

    public List<Message> getListMessages() {
        return listMessages;
    }

    public void setListMessages(ArrayList<Message> listMessages) {
        this.listMessages = listMessages;
    }

}