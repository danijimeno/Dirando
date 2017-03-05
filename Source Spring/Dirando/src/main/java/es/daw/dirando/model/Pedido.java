package es.daw.dirando.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;


@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="COD_Pedido")
	private long id;
	
	@Column(name="Pedidos")
	@OneToMany
	private List<Producto> pedidos;
	
	public Pedido (){
		pedidos= new ArrayList<>();
	}
	
	public List<Producto> getPedidos (){
		return this.pedidos;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public void setPedido (Producto p){
		pedidos.add(p);
	}
	
}
