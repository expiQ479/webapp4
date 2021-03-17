package es.codeurjc.gameweb.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.codeurjc.gameweb.controllers.CommonFunctions;
import es.codeurjc.gameweb.models.Game;
import es.codeurjc.gameweb.models.Genres;
import es.codeurjc.gameweb.models.User;
import java.util.List;
import java.util.Optional;
import es.codeurjc.gameweb.repositories.UserRepository;

@Service
public class UserService{

    @Autowired
    private UserRepository users;

    public Optional<User> findById(long id) {
        return users.findById(id);
    }

    public boolean exist(long id) {
        return users.existsById(id);
    }

    public List<User> findAll() {
        return users.findAll();
    }

    public void save(User user){
        users.save(user);
    }

    public void delete(long id) {
        users.deleteById(id);
    }
}