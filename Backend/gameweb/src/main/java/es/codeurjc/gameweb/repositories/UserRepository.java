package es.codeurjc.gameweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gameweb.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
