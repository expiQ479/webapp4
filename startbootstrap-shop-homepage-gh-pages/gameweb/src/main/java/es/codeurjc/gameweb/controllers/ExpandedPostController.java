package es.codeurjc.gameweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExpandedPostController {
    @Autowired
    private CommonFunctions commonFunctions;
    @GetMapping("/expandedPost")
    public String greeting(Model model) {
        commonFunctions.getSession(model);
        return "expandedPost";
    }
}
