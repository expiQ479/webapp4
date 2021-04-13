package es.codeurjc.gameweb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gameweb.models.Game;
import es.codeurjc.gameweb.models.Genres;

public interface GameRepository extends JpaRepository<Game, Long> {

    //@Query("SELECT g FROM Game g WHERE g.genre=:genre")
    List<Game> findByGenre(Genres genre);
    
}
