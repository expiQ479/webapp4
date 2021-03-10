package es.codeurjc.gameweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gameweb.models.Chat;

public interface ChatRepository  extends JpaRepository<Chat, Long>{
    
}
