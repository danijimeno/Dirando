package es.daw.dirando.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Publicidad {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="Nom_Publicidad")
	private String nomPublicidad;
	
	@Column(name="Rut_Imagen")
	private String imagen;
	
	public Publicidad(){}
	
	public Publicidad(String nombre,String ruta){
		this.nomPublicidad = nombre;
		this.imagen = ruta;
	}
	


	
	//GETTERs AND SETTERs
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomPublicidad() {
		return nomPublicidad;
	}

	public void setNomPublicidad(String nomPublicidad) {
		this.nomPublicidad = nomPublicidad;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
}
