package es.codeurjc.gameweb.models;

import java.util.ArrayList;

public class RegularUser extends User {

    public RegularUser(String info, String contraseña,ArrayList<Game> myGames,boolean admin) {
        super(info, contraseña,myGames,admin);
    }
    
    
}
