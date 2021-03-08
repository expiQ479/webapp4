package es.codeurjc.gameweb.services;


import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import es.codeurjc.gameweb.models.Chat;
import org.springframework.stereotype.Service;


import es.codeurjc.gameweb.models.Message;


@Service
public class ChatService{
	
    private ConcurrentMap<Long, Chat> Chat = new ConcurrentHashMap<>();
    private AtomicLong nextId = new AtomicLong();

    public ChatService(){
        
		ArrayList<Message> listDefaultMessages = new ArrayList<>();
		Message defaultMessage1 = new Message("Paco", "hola soy un desgraciao x1",false);
		Message defaultMessage2 = new Message("Paco", "hola soy un desgraciao x2",true);
		listDefaultMessages.add(defaultMessage1);
		listDefaultMessages.add(defaultMessage2);
		save(new Chat(listDefaultMessages));
		save(new Chat(listDefaultMessages));
		save(new Chat(listDefaultMessages));
		save(new Chat(listDefaultMessages));
        
    }

    public Collection<Chat> findAll() {
		return Chat.values();
	}

	public Chat findById(long id) {
		return Chat.get(id);
	}

	public void save(Chat ChatToSave) {
		long id = nextId.getAndIncrement();

		ChatToSave.setID(id);

		this.Chat.put(id, ChatToSave);
	}

	public void deleteById(long id) {
		this.Chat.remove(id);
	}
}