package es.codeurjc.gameweb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.gameweb.models.Chat;
import es.codeurjc.gameweb.repositories.ChatRepository;

@Service
public class ChatService {
    @Autowired
	private ChatRepository repository;

	public Optional<Chat> findById(long id) {
		return repository.findById(id);
	}
	
	public boolean exist(long id) {
		return repository.existsById(id);
	}

	public List<Chat> findAll() {
		return repository.findAll();
	}

	public void save(Chat chat) {
		repository.save(chat);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}

    
}
