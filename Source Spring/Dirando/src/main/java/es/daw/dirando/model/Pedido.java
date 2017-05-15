package es.daw.dirando.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

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
	
	@Column(name="shoppingCart")
	@ManyToMany
	private List<Producto> sCart;

	public Pedido (){
		sCart= new ArrayList<>();
	}
	
	
	
	/*Getter & Setters*/
	public List<Producto> getPedidos (){
		return this.sCart;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public void setPedido (Producto p){
		sCart.add(p);
	}
	
	public void setsCart(List<Producto> sCart) {
		this.sCart = sCart;
	}
	
	public List<Producto> getsCart() {
		return sCart;
	}
	
}
