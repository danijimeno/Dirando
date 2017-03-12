package es.daw.dirando.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	
	@Column(name="Val_RatingPositive")
	private int theBest;
	
	@Column(name="Val_RatingNeutral")
	private int mustImprove;
	
	@Column(name="Val_RatingNegative")
	private int Bad;

	@Column(name="Rut_Imagen")
	private String image;

	@Column(name="Stock")
	private int stock;
	
	@Column(name="Comments")
	@OneToMany (cascade=CascadeType.ALL)
	private List<Comment> comments;
	
	@ManyToOne (cascade=CascadeType.ALL)
	private Categoria categoria;

	
	/*Constructors*/
	public Producto(){}
	
	public Producto(long id, String name, float price){
		this.id=id;
		this.nombre=name;
		this.precio=price;
	}
	public Producto(String nombre,String desProducto,float precio,int theBest,int mustImprove ,int Bad,String imagen,int stock, Categoria categoria){
		this.nombre = nombre;
		this.desProducto = desProducto; 
		this.precio= precio;
		
		this.theBest = theBest;
		this.mustImprove = mustImprove;
		this.Bad = Bad;
		
		this.image = imagen;
		this.stock = stock;
		this.categoria = categoria;
		comments = new ArrayList<>();
		setReferencia(this.id, 0);
	}
	

	
	
	//GETTERs AND SETTERs
	
	public int getTheBest() {
		return theBest;
	}

	public void incrementTheBest() {
		this.theBest++;
	}

	public int getMustImprove() {
		return mustImprove;
	}

	public void incrementMustImprove() {
		this.mustImprove++;
	}
	
	public int getBad() {
		return Bad;
	}

	public void incrementBad() {
		Bad++;
	}
	
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
	
	public List<Comment> getComments (){
		return this.comments;
	}
	
	public void setComments (Comment comment){
		this.comments.add(comment);
	}
	
	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", descripcion=" + desProducto + ", imagen=" + image +
				", precio=" + precio + ", cantidad=" + stock + ", categoria=" + categoria + "]";
	}

}