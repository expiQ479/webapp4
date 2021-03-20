package es.codeurjc.gameweb.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import es.codeurjc.gameweb.models.Game;
import es.codeurjc.gameweb.services.GamePostService;
import es.codeurjc.gameweb.services.UserService;

@Controller
public class SubscriptionListController {
    @Autowired
    private CommonFunctions commonFunctions;

    @Autowired
    private GamePostService gamePostService;

    @Autowired
    private UserService userService;

    @RequestMapping("/Subscriptions/{id}")
    public String eliminarSubs(Model model, @PathVariable Long id){
        ArrayList<Game> myGames = new ArrayList<>();
        for(int i=0; i<commonFunctions.getU().getMyGames().size(); i++){
            if(commonFunctions.getU().getMyGames().get(i).equals(id)){
                commonFunctions.getU().getMyGames().remove(commonFunctions.getU().getMyGames().get(i));
                commonFunctions.getSession(model);
                userService.save(commonFunctions.getU());
            }
            else{
                myGames.add(gamePostService.findById(commonFunctions.getU().getMyGames().get(i)).get());
            }
        }
        model.addAttribute("listaSubs", myGames);
        commonFunctions.getSession(model);
        if(commonFunctions.getU().getMyGames().isEmpty()){
            model.addAttribute("noSubs", "No tienes ninguna subscripcion");
        }
        else{
            model.addAttribute("noSubs", "");
        }
        return "Subscriptions";
    } 
   
}
