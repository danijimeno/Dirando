package es.daw.dirando.repository;


import org.springframework.data.repository.CrudRepository;

import es.daw.dirando.model.Categoria;

public interface CategoriaRepository extends CrudRepository<Categoria, Long>{
	
}
