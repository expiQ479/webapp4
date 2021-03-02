package es.codeurjc.gameweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SubscriptionListController {
    @Autowired
    private CommonFunctions commonFunctions;
    @RequestMapping("/Subscriptions")
    public String showSubscriptions(Model model){
        model.addAttribute("listaSubs",commonFunctions.getU().getMyGames());
        commonFunctions.getSession(model);
        System.out.println(commonFunctions.getU().getMyGames().size());
        return "Subscriptions";
    }
}
