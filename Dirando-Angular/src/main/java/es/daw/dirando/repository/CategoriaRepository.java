package es.daw.dirando.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import es.daw.dirando.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	
	Categoria findByName(String name);
}
