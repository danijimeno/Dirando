package es.daw.dirando.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="COD_Pedido")
	private int id;
	
	@Column(name="Pedidos")
	@OneToMany
	private List<Pedido> pedidos;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}
