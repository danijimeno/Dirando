package es.daw.dirando.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.daw.dirando.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	

}
