package es.daw.dirando.repository;

import org.springframework.data.repository.CrudRepository;

import es.daw.dirando.model.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {
	

}
