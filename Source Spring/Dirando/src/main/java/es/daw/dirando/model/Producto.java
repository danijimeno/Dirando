package es.daw.dirando.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="COD_Producto")
	private int id;
}
