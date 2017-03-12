package es.daw.dirando.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import es.daw.dirando.model.Publicidad;

@Entity
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="COD_Categoria")
	private long id;
	
	@Column(name="Nom_Categoria")
	private String name;
	
	@Column(name="Publicidad")
	@OneToMany(mappedBy="")
	private List<Publicidad> publicidad;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="")
	private List<Producto> productos; 
	
	
	public Categoria() {
		
	}
	
	public Categoria(String name) {
		this.name=name;
		this.productos=new ArrayList<>();
	}
	
	
	//GETTERs AND SETTERs
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return name;
	}

	public void setNombre(String nombre) {
		this.name = nombre;
	}

	public List<Publicidad> getPublicidad() {
		return publicidad;
	}

	public void setPublicidad(List<Publicidad> publicidad) {
		this.publicidad = publicidad;
	}
	
	public List<Producto> getProductos() {
		return productos;
	}
	
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

}
