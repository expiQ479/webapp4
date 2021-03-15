package es.codeurjc.gameweb.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

//import java.util.ArrayList;
@Entity
@DynamicUpdate
public class Message {
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String userId;
    private String nameUser;
    private String msgString;
    private boolean isMessageWriter;
    //private ArrayList<String> HTMLMessage;

    
    public Message(String nameUser, String msgString, boolean isMessageWriter) {
        this.nameUser = nameUser;
        this.msgString = msgString;
        this.isMessageWriter = isMessageWriter;
    }
    public Message() {}

    public String getNameUser() {
        return nameUser;
    }


    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }


    public String getMsgString() {
        return msgString;
    }

    public void setMsgString(String msgString) {
        this.msgString = msgString;
    }

    public boolean isMessageWriter() {
        return isMessageWriter;
    }

    public void setMessageWriter(boolean isMessageWriter) {
        this.isMessageWriter = isMessageWriter;
    }

    public void setID(long id) {
    }

    
}
