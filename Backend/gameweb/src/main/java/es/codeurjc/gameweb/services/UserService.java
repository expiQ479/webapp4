package es.codeurjc.gameweb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public Optional<User> findByName(String name) {
        return users.findByName(name);
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