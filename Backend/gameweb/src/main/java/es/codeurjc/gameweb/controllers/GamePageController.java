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
import es.codeurjc.gameweb.models.Genres;
import es.codeurjc.gameweb.models.Message;
import es.codeurjc.gameweb.services.ChatService;

@Controller
public class GamePageController {
    @Autowired
    private CommonFunctions commonFunctions;
  
    @Autowired
	private ChatService ChatService;

    @RequestMapping("/GamePage/{name}/subButton")
    public String subButton(Model model,@PathVariable String name){    
        commonFunctions.getU().addElementToGameList(new Game(name, Genres.Horror, "Un juego muy guapo"));
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
    public String newChat(Model model,@RequestParam String sentChat) {
        commonFunctions.getSession(model);
        model.addAttribute("name", "DBD"); //añadir cuando toque, la obtencion del nombre del juego por base de datos
        for (Integer i=0;i<=ChatService.findAll().size()-1;i++){
            if(commonFunctions.getU().getInfo().equals(ChatService.findById(i).getNameUser())) 
            ChatService.findById(i).setMessageWriter(true);
            else
            ChatService.findById(i).setMessageWriter(false);
        }
    
        Message MyMessage = new Message(commonFunctions.getU().getInfo(), sentChat,true);
        ChatService.save(MyMessage);
        model.addAttribute("Messages",ChatService.findAll());
       
        	
        return "GamePage";
    }
     
}
