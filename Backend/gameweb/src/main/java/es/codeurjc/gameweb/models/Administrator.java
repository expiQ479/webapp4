package es.codeurjc.gameweb.models;

import java.util.ArrayList;

public class Administrator extends User {

    public Administrator(String info, String contraseña,ArrayList<Game> myGames,boolean admin) {
        super(info, contraseña,myGames,admin);
    }
    
}
