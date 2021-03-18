package es.codeurjc.gameweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/Subscriptions/{gameTitle}")
    public String eliminarSubs(Model model, @PathVariable String gameTitle){
        for(int i=0; i<commonFunctions.getU().getMyGames().size(); i++){
            if(gamePostService.findById(commonFunctions.getU().getMyGames().get(i)).get().getGameTitle().equals(gameTitle)){
                commonFunctions.getU().getMyGames().remove(commonFunctions.getU().getMyGames().get(i));
                model.addAttribute("listaSubs",commonFunctions.getU().getMyGames());
                commonFunctions.getSession(model);
                userService.save(commonFunctions.getU());
            }
        }
        commonFunctions.getSession(model);
        return "Subscriptions";
    } 
   
}
