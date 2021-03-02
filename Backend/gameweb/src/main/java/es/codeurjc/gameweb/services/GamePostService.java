package es.codeurjc.gameweb.services;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import es.codeurjc.gameweb.models.Game;
import es.codeurjc.gameweb.models.Genres;
@Service
public class GamePostService {
    
    private ConcurrentMap<Long, Game> gamesPosted = new ConcurrentHashMap<>();
	private AtomicLong nextId = new AtomicLong();

	public GamePostService() {        
		save(new Game("Pepe",Genres.Horror,"Barata"));
		save(new Game("Juan", Genres.Horror,"Compro coche"));
	}

	public Collection<Game> findAll() {
		return gamesPosted.values();
	}

	public Game findById(long id) {
		return gamesPosted.get(id);
	}

	public void save(Game game) {
		long id = nextId.getAndIncrement();

		game.setId(id);

		this.gamesPosted.put(id, game);
	}

	public void deleteById(long id) {
		this.gamesPosted.remove(id);
	}

}

