package es.codeurjc.gameweb.models;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class User {
    private String info;
    private String contraseña;
    private boolean logged = true;
    public User(String info, String contraseña) {
        this.info = info;
        this.contraseña = contraseña;
    }
    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

}
