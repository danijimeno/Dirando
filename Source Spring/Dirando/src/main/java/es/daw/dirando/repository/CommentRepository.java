package es.daw.dirando.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import es.daw.dirando.model.Comment;
import es.daw.dirando.model.Producto;


public interface CommentRepository extends JpaRepository<Comment, Long>{
}
