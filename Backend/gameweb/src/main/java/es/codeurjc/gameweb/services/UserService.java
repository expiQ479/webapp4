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
		User user1 = new User("Kike", "12345", suscripciones);
		user1.setAdmin(true);
		User user2 = new User("Pepe", "54321", suscripciones);
		user2.setAdmin(false);
        save(user1);
        save(user2);
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