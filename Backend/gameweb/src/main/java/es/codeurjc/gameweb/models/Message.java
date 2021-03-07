package es.codeurjc.gameweb.models;

//import java.util.ArrayList;

public class Message {
    private String nameUser;
    private String msgString;
    private boolean isMessageWriter;
    //private ArrayList<String> HTMLMessage;

    
    public Message(String nameUser, String msgString, boolean isMessageWriter) {
        this.nameUser = nameUser;
        this.msgString = msgString;
        this.isMessageWriter = isMessageWriter;
    }

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
