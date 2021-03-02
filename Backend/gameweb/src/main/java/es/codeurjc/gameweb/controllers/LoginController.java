package es.codeurjc.gameweb.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommonFunctions commonFunctions;

    @PostMapping("/Iniciar sesion")
	public String newPost(Model model, User user) {
        if(userService.equals(user)){
            commonFunctions.setU(userService.findById(id));
            commonFunction.getU().setLogged(true);
        }
        commonFunctions.getSession(model);
		gamePostService.save(game);
		return "index";
	}

}
