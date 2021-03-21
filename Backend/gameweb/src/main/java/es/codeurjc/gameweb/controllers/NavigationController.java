package es.codeurjc.gameweb.controllers;

import java.security.Principal;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NavigationController implements ErrorController {
    
    @ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();

		if (principal != null) {

			model.addAttribute("logged", true);
			model.addAttribute("userName", principal.getName());
			model.addAttribute("admin", request.isUserInRole("ADMIN"));

		} else {
			model.addAttribute("logged", false);
		}
	}
    //HEADER 
    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("problem", "");
        return "register";
    }
    @GetMapping("/login")
    public String showLogIn() {
        return "login";
    }

    //COMMON ACCESS
    /*
    public Map.Entry<Genres,Integer> recommendedAlgorithm(User user){
        HashMap<Genres,Integer> amountOfGamesWithGenre=new HashMap<Genres,Integer>();
        for(Genres g : Genres.values()){
            amountOfGamesWithGenre.put(g, 0);
        }
        Map.Entry<Genres,Integer> maxEntry=null;
        for (Long game : user.getMyGames()) {
            amountOfGamesWithGenre.put(gamePostService.findById(game).get().getGenre(),amountOfGamesWithGenre.get(gamePostService.findById(game).get().getGenre())+1);
        }
        
        for(Map.Entry<Genres,Integer> entry : amountOfGamesWithGenre.entrySet()){
            if(maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0){
                maxEntry=entry;
            }           
        }
        return maxEntry;
    }
    private void setSomeList(Model model,HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        try {
            Optional<User> myUser= userService.findByName(principal.getName());  
            ArrayList<Game> toShow=gamePostService.getNumberOfGames(3, gamePostService.findGamesOfGenre(recommendedAlgorithm(myUser.get()).getKey()));                 
            model.addAttribute("selectedList",toShow);
            model.addAttribute("whatList", "Recomendados");
        } catch (Exception e) { 
            ArrayList<Game> toShow=gamePostService.getNumberOfGames(3, gamePostService.findBestRatedGames());                 
            model.addAttribute("selectedList",toShow);
            model.addAttribute("whatList", "Mejor valorados");
        }
    }
    */
    
    

    @RequestMapping("/showMoreGames") 
    public String showMoreGames(Model model) {
        
        return "showMoreGames";
    }    
    
    //ERRORS
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error-404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error-500";
            }
        }
        return "error";
    }

    @Override
    public String getErrorPath() {
        return null;
    }

}
