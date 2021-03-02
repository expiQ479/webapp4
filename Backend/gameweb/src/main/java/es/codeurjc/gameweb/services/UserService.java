package es.codeurjc.gameweb.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import es.codeurjc.gameweb.models.Game;
import es.codeurjc.gameweb.models.Genres;
import es.codeurjc.gameweb.models.User;

@Service
public class UserService{

    private ConcurrentMap<Long, User> user = new ConcurrentHashMap<>();
    private AtomicLong nextId = new AtomicLong();

    public UserService(){
        ArrayList<Game> suscripciones = new ArrayList<>();
        suscripciones.add(new Game("Stardew valley", Genres.RPG, "Juego entretenido de una granja"));
        suscripciones.add(new Game("Skyrim", Genres.RPG, "Juego RPG clasico"));
        save(new User("Kike", "12345", suscripciones, true));
        save(new User("Pepe", "54321", suscripciones, false));
    }

    public Collection<User> findAll() {
		return user.values();
	}

	public User findById(long id) {
		return user.get(id);
	}

	public void save(User u) {
		long id = nextId.getAndIncrement();

		u.setId(id);

		this.user.put(id, u);
	}

	public void deleteById(long id) {
		this.user.remove(id);
	}
}