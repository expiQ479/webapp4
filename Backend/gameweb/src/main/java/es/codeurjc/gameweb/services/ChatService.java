package es.codeurjc.gameweb.services;


import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;


import es.codeurjc.gameweb.models.Message;


@Service
public class ChatService{

    private ConcurrentMap<Long, Message> Chat = new ConcurrentHashMap<>();
    private AtomicLong nextId = new AtomicLong();

    public ChatService(){
        
		save(new Message("Paco", "hola soy un desgraciao x1",true));
        save(new Message("Paco", "hola soy un desgraciao x2",false));

        
    }

    public Collection<Message> findAll() {
		return Chat.values();
	}

	public Message findById(long id) {
		return Chat.get(id);
	}

	public void save(Message MessageToSave) {
		long id = nextId.getAndIncrement();

		MessageToSave.setID(id);

		this.Chat.put(id, MessageToSave);
	}

	public void deleteById(long id) {
		this.Chat.remove(id);
	}
}