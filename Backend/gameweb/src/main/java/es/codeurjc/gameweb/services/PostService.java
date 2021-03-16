package es.codeurjc.gameweb.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.gameweb.models.Game;
import es.codeurjc.gameweb.models.Post;
import es.codeurjc.gameweb.models.PostType;
import es.codeurjc.gameweb.repositories.PostRepository;

@Service
public class PostService {
    @Autowired
	private PostRepository posts;

	public void save(Post post) {
		posts.save(post);		
	}

	public List<Post> findAll() {
		return posts.findAll();
	}
	public ArrayList<Post> findPostOfGame(Game game){
		ArrayList<Post> aux=new ArrayList<Post>();
		for(Post p : posts.findAll()){
			if(p.getFromGame().equals(game)){
				aux.add(p);
			}
		}
		return aux;
	}
	public ArrayList<Post> findPostOfType(ArrayList<Post> given,PostType theType){
		ArrayList<Post> aux=new ArrayList<Post>();
		for(Post p : given){
			if(p.getTheType().equals(theType)){
				aux.add(p);
			}
		}
		return aux;
	}
	public Optional<Post> findById(long id) {
		return posts.findById(id);
	}
	
	public void replace(Post updatedPost) {

		posts.findById(updatedPost.getId()).orElseThrow();

		posts.save(updatedPost);		
	}

	public void deleteById(long id) {
		
		posts.deleteById(id);		
	}
}
