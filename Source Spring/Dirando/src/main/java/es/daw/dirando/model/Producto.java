package es.daw.dirando.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import es.daw.dirando.model.Categoria;


@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Entity
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="COD_Producto")
	private long id;

	@Column(name="Nom_Producto")
	private String nombre;

	@Column(name="Des_Producto")
	private String desProducto;

	@Column(name="EUR_Producto")
	private float precio;

	@Column(name="Cod_Referencia")
	private int ref;
	
	@Column(name="Valoracion")
	private int valoracion;

	@Column(name="Rut_Imagen")
	private String image;

	@Column(name="Stock")
	private int stock;
	
	
	@ManyToOne
	private Categoria categoria;


	public Producto(){}
	
	public Producto(long id){
		this.id=id;
	}
	
	public Producto(String nombre,String desProducto,float precio,int valoracion,String imagen,int stock ,String... categoria){
		this.nombre = nombre;
		this.desProducto = desProducto; 
		this.precio= precio;
		this.valoracion = valoracion;
		this.image = imagen;
		this.stock = stock;
		
		setReferencia(this.id, 0);
	}
	
	public Producto(String nombre,String desProducto,float precio,int valoracion,String imagen,int stock ,Categoria categoria){
		this.nombre = nombre;
		this.desProducto = desProducto; 
		this.precio= precio;
		this.valoracion = valoracion;
		this.image = imagen;
		this.stock = stock;
		this.categoria=categoria;
		
		setReferencia(this.id, 0);
	}
	
	
	
	
	//GETTERs AND SETTERs
	
	public int getReferencia(){
		return this.ref;
	}
	
	private void setReferencia(long id,int cod){
		this.ref=(int) (1000*cod+id);
	}
	
	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDesProducto() {
		return desProducto;
	}

	public void setDesProducto(String desProducto) {
		this.desProducto = desProducto;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(long precio) {
		this.precio = precio;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}	
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", descripcion=" + desProducto + ", imagen=" + image +
				", precio=" + precio + ", cantidad=" + stock + ", categoria=" + categoria + "]";
	}

}
