package es.daw.dirando.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import es.daw.dirando.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{
	Producto findProductoByRef(int ref);
	Producto findProductoById(long id);
	Page<Producto> findByNombre(String nombre, Pageable pageable);
	Page<Producto> findByCategoria(String categoria, Pageable pageable);
	Page<Producto> findAll(Pageable pageable);
}