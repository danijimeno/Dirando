package es.daw.dirando.model;

import java.util.List;

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
	private String nombre;
	
	@Column(name="Publicidad")
	@OneToMany(mappedBy="")
	private List<Publicidad> publicidad;
	
	@OneToMany(mappedBy="categoria")
	private List<Producto> productos; 
	

	
	
	//GETTERs AND SETTERs
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Publicidad> getPublicidad() {
		return publicidad;
	}

	public void setPublicidad(List<Publicidad> publicidad) {
		this.publicidad = publicidad;
	}

}
