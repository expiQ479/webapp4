package es.codeurjc.gameweb.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommonFunctions commonFunctions;

    
}
