package es.codeurjc.gameweb.models;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public abstract class User {
    private String info;
    private String password;
    private boolean logged = true;
    public User(String info, String password) {
        this.info = info;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String contraseña) {
        this.password = contraseña;
    }

}
