package es.codeurjc.gameweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gameweb.models.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
    
}
