package es.codeurjc.gameweb.controllers;
 
import java.security.Principal;
import java.util.HashMap;
import java.util.Optional;
 
import javax.servlet.http.HttpServletRequest;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
 
import es.codeurjc.gameweb.models.Chat;
import es.codeurjc.gameweb.models.Game;
import es.codeurjc.gameweb.models.Message;
import es.codeurjc.gameweb.models.User;
import es.codeurjc.gameweb.services.AlgorithmService;
import es.codeurjc.gameweb.services.ChatService;
import es.codeurjc.gameweb.services.GameService;
import es.codeurjc.gameweb.services.ScoresService;
import es.codeurjc.gameweb.services.SubscriptionsService;
import es.codeurjc.gameweb.services.UserService;
 
import java.sql.SQLException;
 
@Controller
public class GamePageController {  
    @Autowired
	private GameService gamePostService;
    @Autowired
    private ChatService chatService;
 
    @Autowired
	private UserService userService;
    @Autowired
    private ScoresService scoresService;
    @Autowired
    private SubscriptionsService subService;
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
    @RequestMapping("/gamePage/{id}")
    public String showGame(Model model, @PathVariable Long id, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        try{
        Optional<User> myUser= userService.findByName(principal.getName());
        User user =myUser.get();
        Optional<Game> myGame = gamePostService.findById(id);
        Optional<Chat> myChat = chatService.findById(id+1);
        Game game = myGame.get();
        Chat chat;
        chat = myChat.get();
        model.addAttribute("game", game);
        for (Integer i = 0; i <= chat.getListMessages().size() - 1; i++) {
            if (user.getInfo().equals(chat.getListMessages().get(i).getNameUser()))
                chat.getListMessages().get(i).setMessageWriter(true);
            else
                chat.getListMessages().get(i).setMessageWriter(false);
        }
        if(principal != null){
            try {
                model.addAttribute("canSub", !user.getMyGames().contains(game.getId()));
            } catch (Exception e) {
                model.addAttribute("canSub", true);
            }
 
            model.addAttribute("Messages", chat.getListMessages());
        }
        return "gamePage"; 
        } catch (Exception e){
            Optional<Game> myGame = gamePostService.findById(id); 
            Game game = myGame.get();
            model.addAttribute("game", game);
            return "gamePage";
        }  
    }
 
    @RequestMapping("/gamePage/{id}/subButton")
    public String subButton(Model model,@PathVariable Long id, HttpServletRequest request){  
        Principal principal = request.getUserPrincipal();
        Optional<Game> myGame=gamePostService.findById(id);
        Game game=myGame.get();
        Optional<User> myUser= userService.findByName(principal.getName());
        User user =myUser.get();
        if(!user.getMyGames().contains(id)){     
            userService.save(subService.subscriptionFunction(id, user));               
            model.addAttribute("game", myGame);
            model.addAttribute("customMessage", "Suscripción realizada con éxito");
            return "successPage";
        }
        else{
            model.addAttribute("game", myGame);
            model.addAttribute("customMessage", "Ya te has subscrito a ese juego");
            return "successPage";
        }
    }  
    @RequestMapping("/gamePage/{id}/unsubButton")
    public String unsubButton(Model model, @PathVariable Long id,HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> myUser= userService.findByName(principal.getName());
        User user =myUser.get();
        String gameTitle=gamePostService.findById(id).get().getGameTitle();
        userService.save(subService.unsubscriptionFunction(id, user));
        model.addAttribute("customMessage", "Desuscripción realizada con éxito");      
        return "successPage";
    }
    @GetMapping("/gamePage/{id}/image")
    public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {
 
        Optional<Game> game = gamePostService.findById(id);
        if (game.isPresent() && game.get().getImageFile() != null) {
 
            Resource file = new InputStreamResource(game.get().getImageFile().getBinaryStream());
 
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(game.get().getImageFile().length()).body(file);
 
        } else {
            return ResponseEntity.notFound().build();
        }
    } 
 
 
    @RequestMapping("/rate/{id}")
    public String ValorarGame(Model model, @PathVariable Long id, @RequestParam Integer stars,HttpServletRequest request) {
 
 
        gamePostService.save(scoresService.putScore(request, id, stars));
        model.addAttribute("customMessage", "Juego valorado con un " + stars + " con éxito");
        return "successPage";
    }
    @PostMapping("/agregarChat/{id}")
    public String newChat(Model model, @PathVariable Long id, @RequestParam String sentChat,HttpServletRequest request) {
        Optional<Game> game = gamePostService.findById(id);
        Game myGame = game.get();
        model.addAttribute("game", myGame);
        Principal principal = request.getUserPrincipal();
        Optional<User> myUser= userService.findByName(principal.getName());
        User user =myUser.get();
        //iterate the chat messages to allign them to the right or to the left
        for (Integer i=0;i<=myGame.getChat().getListMessages().size()-1;i++){
            if(user.getInfo().equals(myGame.getChat().getListMessages().get(i).getNameUser())) 
            myGame.getChat().getListMessages().get(i).setMessageWriter(true);
            else
            myGame.getChat().getListMessages().get(i).setMessageWriter(false);
        }
        model.addAttribute("Messages", myGame.getChat().getListMessages());
 
        //Create the message and add it to the chat of the game
        Message MyMessage = new Message(user.getInfo(), sentChat,true);
        myGame.getChat().getListMessages().add(MyMessage);
        model.addAttribute("Messages", myGame.getChat().getListMessages());
        gamePostService.save(myGame);
        if(principal!=null){
            model.addAttribute("canSub", !user.getMyGames().contains(myGame.getId()));
            //model.addAttribute("Messages", chat.getListMessages());
        }   
        return "gamePage";
    }
    @RequestMapping("/statistics/{id}")
    public String showGameStats(Model model, @PathVariable Long id) {
 
        Optional<Game> myGame = gamePostService.findById(id);
 
        Game game = myGame.get();
        model.addAttribute("game", game);
        Integer int1=scoresService.doAverageRatio(game.getMapScores(),1);
        Integer int2=scoresService.doAverageRatio(game.getMapScores(),2);
        Integer int3=scoresService.doAverageRatio(game.getMapScores(),3);
        Integer int4=scoresService.doAverageRatio(game.getMapScores(),4);
        Integer int5=scoresService.doAverageRatio(game.getMapScores(),5);
        model.addAttribute("gamestars1", int1);
        model.addAttribute("gamestars2", int2);
        model.addAttribute("gamestars3", int3);
        model.addAttribute("gamestars4", int4);
        model.addAttribute("gamestars5", int5);
 
        return "gameStadistics";
    }
}
