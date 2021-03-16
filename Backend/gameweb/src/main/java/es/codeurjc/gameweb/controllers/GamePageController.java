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
    
    @RequestMapping("/GamePage/{id}/subButton")
    public String subButton(Model model,@PathVariable Long id){  
        Optional<Game> myGame=gamePostService.findById(id);
        Game game=myGame.get();
        commonFunctions.getU().addElementToGameList(game);
        commonFunctions.getSession(model);    
        myGame = gamePostService.findById(id);
        model.addAttribute("game", myGame); 
        model.addAttribute("customMessage", "Suscripción realizada con éxito");
        return "savedGame";   
    }  
    

    
    public Integer doAverageRatio(ArrayList<Integer> MyScores, Integer index){
        Integer aux = 0;
        Integer numberofindexinthearray = 0;
        for(int i=0;i<=MyScores.size()-1;i++){
            if (MyScores.get(i).equals(index))
                numberofindexinthearray++;
        }
        aux = (numberofindexinthearray*100)/(MyScores.size());
        return aux;
    }

    public float doAverageScore(ArrayList<Integer> MyScores){
        float aux = 0;
        for(int i=0;i<=MyScores.size()-1;i++){
            aux= aux + MyScores.get(i);
        }
        aux = aux/(MyScores.size());
        aux = aux*10;
        aux = Math.round(aux);
        aux = aux/10;
        return aux;
    }

    

    @RequestMapping("/valorar/{id}")
    public String ValorarGame(Model model, @PathVariable Long id, @RequestParam Integer stars) {

        Optional<Game> myGame = gamePostService.findById(id);
        
        Game game = myGame.get();
        model.addAttribute("game", game);
        game.getListScores().add(stars);
        
        Integer int1=doAverageRatio(game.getListScores(),1);
        Integer int2=doAverageRatio(game.getListScores(),2);
        Integer int3=doAverageRatio(game.getListScores(),3);
        Integer int4=doAverageRatio(game.getListScores(),4);
        Integer int5=doAverageRatio(game.getListScores(),5);
        model.addAttribute("gamestars1", int1);
        model.addAttribute("gamestars2", int2);
        model.addAttribute("gamestars3", int3);
        model.addAttribute("gamestars4", int4);
        model.addAttribute("gamestars5", int5);
        float myAverage= doAverageScore(game.getListScores());
        game.setAverageScore(myAverage);
        gamePostService.save(game);
        commonFunctions.getSession(model);

        return "gamestadistics";
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
