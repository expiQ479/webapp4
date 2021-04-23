package es.codeurjc.gameweb.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.codeurjc.gameweb.models.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import es.codeurjc.gameweb.models.Genres;
import es.codeurjc.gameweb.repositories.GameRepository;

@Service
public class GameService {

	public GameService() {        
			
	}

	@Autowired
	private GameRepository repository;

	public Optional<Game> findById(long id) {
		return repository.findById(id);
	}
	
	public boolean exist(long id) {
		return repository.existsById(id);
	}

	public List<Game> findAll() {
		return repository.findAll();
	}
	public ArrayList<Game> findGamesOfGenre(Genres gameGenre){
		ArrayList<Game> aux=new ArrayList<Game>();
		for (Game g : repository.findAll()) {
			if(g.getGenre().equals(gameGenre)){
				aux.add(g);
			}
		}
		if(!aux.isEmpty()){
			return aux;
		}
		else return null;
		
	}
	public ArrayList<Game> getNumberOfGames(int n,ArrayList<Game> theArray){
		ArrayList<Game> aux=new ArrayList<Game>();
		for(int i=0;i<n;i++){
			aux.add(theArray.get(i));
		}
		return aux;
	}
	
	public void save(Game game) {
		repository.save(game);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}
	public List<Game> findByCategory(Genres genre){
		return repository.findByGenre(genre);
	}
	
	public Page<Game> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}
	public ArrayList<Game> findByCategoryPage(Pageable pageable, Genres genre){
		Page<Game> games=repository.findAll(pageable);
		ArrayList<Game> aux=new ArrayList<Game>();
		games.forEach(game ->{
			if(game.getGenre().equals(genre)){
				aux.add(game);
			}
		});
		return aux;
	}
    public ArrayList<Game> findBestRatedGames() {
		float maxScore=0;
        ArrayList<Game> aux=new ArrayList<Game>();
		for (Game g : repository.findAll()) {
			if(g.getAverageScore() >= maxScore)
				aux.add(g);
				maxScore=g.getAverageScore();
			}
		
		if(!aux.isEmpty()){
			return aux;
		}
		else return null;
    }

    public ArrayList<Game> findNumberOfGames(int n) {
        ArrayList<Game> aux=new ArrayList<Game>();
		List<Game> allGames=this.findAll();
		for(int i=0;i<n;i++){
			aux.add(allGames.get(i));
		}
		return aux;
    }
	public void setAllImagePaths(){
        for(Game g : repository.findAll()){
            g.setImagePath("https://localhost:8443/api/games/"+g.getId()+"/images");
			repository.save(g);
        }
    }

}