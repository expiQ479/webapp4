package es.codeurjc.gameweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.gameweb.models.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
}
