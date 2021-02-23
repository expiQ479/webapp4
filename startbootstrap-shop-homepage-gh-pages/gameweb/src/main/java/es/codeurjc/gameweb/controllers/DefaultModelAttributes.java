package es.codeurjc.gameweb.controllers;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class DefaultModelAttributes {
    @ModelAttribute("namepage") //es la variable que sustituira el nombre de la pgina
    public String namepage() {
        return "4DGames"; //nombre de la pagina web
    }

    @ModelAttribute("logged") 
    public boolean logged() {
        return false; 
    }

    @ModelAttribute("info") 
    public String info() {
        return "Kike"; 
    }

    @ModelAttribute("contraseña") 
    public String contraseña() {
        return "12345"; 
    }
}