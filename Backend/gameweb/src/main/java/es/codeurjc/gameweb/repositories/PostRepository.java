package es.codeurjc.gameweb.repositories;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import es.codeurjc.gameweb.models.Game;
import es.codeurjc.gameweb.models.Post;

@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
    List<Post> findAll();
    Page<Post> findAll(Pageable pageable);
	Page<Post> findByfromGame(Game fromGame,Pageable pageable);
}
