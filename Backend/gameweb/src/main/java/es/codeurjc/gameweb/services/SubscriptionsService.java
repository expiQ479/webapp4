package es.codeurjc.gameweb.services;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import es.codeurjc.gameweb.models.Game;
import es.codeurjc.gameweb.models.User;
 
@Service
public class SubscriptionsService {
    @Autowired
    private UserService userService;
    @Autowired
    private GameService gameService;
    public User subscriptionFunction(long gameID,User user){
        Game game=gameService.findById(gameID).get();
        if(game!=null){
            user.addElementToGameList(gameID);
        }
        return user;
 
    }
    public User unsubscriptionFunction(long gameID,User user){
        Game game=gameService.findById(gameID).get();
        if(game!=null){
            for(int i = 0; i < user.getMyGames().size(); i++){
                if(user.getMyGames().get(i)==gameID){
                    user.getMyGames().remove(i);
                }
            }
        }
        return user;
    }
}
