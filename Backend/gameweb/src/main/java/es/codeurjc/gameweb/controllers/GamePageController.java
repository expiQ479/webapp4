package es.codeurjc.gameweb.controllers;

import java.util.ArrayList;
import java.util.Optional;

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


@Controller
public class GamePageController {
    @Autowired
    private CommonFunctions commonFunctions;
  


    @Autowired
	private GamePostService gamePostService;

    private Game myGame;
    
    @RequestMapping("/GamePage/{id}/subButton")
    public String subButton(Model model,@PathVariable Long id){  
        Optional<Game> myGame=gamePostService.findById(id);
        Game game=myGame.get();
        commonFunctions.getU().addElementToGameList(game);
        commonFunctions.getSession(model);    
        myGame = gamePostService.findById(id);
        model.addAttribute("game", myGame); 
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
    @PostMapping("/AgregarChat/{id}")
    public String newChat(Model model, @PathVariable Long id, @RequestParam String sentChat) {
        Optional<Game> game = gamePostService.findById(id);
        Game myGame = game.get();
        model.addAttribute("game", myGame);
        //iterate the chat messages to allign them to the right or to the left
        for (Integer i=0;i<=myGame.getChat().getListMessages().size()-1;i++){
            if(commonFunctions.getU().getInfo().equals(myGame.getChat().getListMessages().get(i).getNameUser())) 
            myGame.getChat().getListMessages().get(i).setMessageWriter(true);
            else
            myGame.getChat().getListMessages().get(i).setMessageWriter(false);
        }
        model.addAttribute("Messages", myGame.getChat().getListMessages());
        commonFunctions.getSession(model);
        
        //Create the message and add it to the chat of the game
        Message MyMessage = new Message(commonFunctions.getU().getInfo(), sentChat,true);
        myGame.getChat().getListMessages().add(MyMessage);
        model.addAttribute("Messages", myGame.getChat().getListMessages());
        commonFunctions.getSession(model);
        gamePostService.save(myGame);
        return "GamePage";
    }
    
    
         
      
    
     
}
