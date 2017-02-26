package es.daw.dirando.repository;

import org.springframework.data.repository.CrudRepository;

import es.daw.dirando.model.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Long>{
	Producto findProductoByRef(int ref);
}
