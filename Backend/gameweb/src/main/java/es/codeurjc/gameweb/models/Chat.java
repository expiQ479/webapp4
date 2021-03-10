package es.codeurjc.gameweb.models;

import java.util.ArrayList;


public class Chat {

    private ArrayList<Message> listMessages; // Esto estará en base de datos
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