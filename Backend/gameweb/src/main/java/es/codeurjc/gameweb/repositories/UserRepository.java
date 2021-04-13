package es.codeurjc.gameweb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gameweb.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByInfo(String info);
}
