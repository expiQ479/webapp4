package es.codeurjc.gameweb.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.gameweb.models.Game;
import es.codeurjc.gameweb.models.Message;
import es.codeurjc.gameweb.services.GamePostService;
import es.codeurjc.gameweb.services.ChatService;

@Controller
public class GamePageController {
    @Autowired
    private CommonFunctions commonFunctions;
  
    @Autowired
	private ChatService chatService;

    @Autowired
	private GamePostService gamePostService;

    private Game myGame;
    
    @RequestMapping("/GamePage/{name}/subButton")
    public String subButton(Model model,@PathVariable String name){    
        commonFunctions.getU().addElementToGameList(new Game(name, null, null));
        commonFunctions.getSession(model);     
        return "GamePage";  
    }  

    public float doAverageScore(ArrayList<Integer> MyScores){
        float aux = 0;
        for(int i=0;i<MyScores.size();i++){
            aux= aux + MyScores.get(i);
        }
        aux = aux/(MyScores.size());
        return aux;
    }
    
    @PostMapping("/GamePage/{name}/score")
    public String score(Model model,@PathVariable String name,@RequestParam(name="stars") Integer score){    
        ArrayList<Game> someGames=new ArrayList<Game>();
        Game MyGame = new Game(name, null, null);
        someGames.add(MyGame);
        someGames.get(0).getListScores().add(score);      
        someGames.get(0).setAverageScore(doAverageScore(someGames.get(0).getListScores()));
        commonFunctions.getSession(model);
        return "GamePage";
    }   
    @PostMapping("/AgregarChat")
    public String newChat(Model model, @RequestParam String gamename, @RequestParam String sentChat) {
        
        //Find the game we need to show
        for (Integer i=0;i<=gamePostService.findAll().size()-1;i++){
            if (gamePostService.findById(i).getGameTitle().equals(gamename))
                myGame = gamePostService.findById(i);
        }
        //save the ID of the game to connect it to a chat
        model.addAttribute("name", gamename);
        long IDgame = myGame.getId();
        
        model.addAttribute("description", myGame.getDescription());
        //iterate the chat messages to allign them to the right or to the left
        for (Integer i=0;i<=chatService.findById(IDgame).getListMessages().size()-1;i++){
            if(commonFunctions.getU().getInfo().equals(chatService.findById(IDgame).getListMessages().get(i).getNameUser())) 
            chatService.findById(IDgame).getListMessages().get(i).setMessageWriter(true);
            else
            chatService.findById(IDgame).getListMessages().get(i).setMessageWriter(false);
        }
        //Create the message and add it to the chat of the game
        Message MyMessage = new Message(commonFunctions.getU().getInfo(), sentChat,true);
        chatService.findById(IDgame).getListMessages().add(MyMessage);
        model.addAttribute("Messages", chatService.findById(IDgame).getListMessages());
        commonFunctions.getSession(model);
        return "GamePage";
    }
     
}
