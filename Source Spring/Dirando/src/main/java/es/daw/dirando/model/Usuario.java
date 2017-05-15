package es.daw.dirando.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	/*User id Unique*/
	@Column(name="Name")
	private String name;
	
	@Column(name="full_name")
	private String full_name;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="Password")
	private String password;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@Column(name="Role")
	private List<String> role;
	
	@Column(name="Img_Usuario")
	private String imgRuta;
	
	@Column(name="address")
	private String address;
	
	@Column(name="phone_number")
	private long phone;
	
	@JsonIgnore
	@Column(name="Comments")
	@OneToMany (cascade=CascadeType.ALL)
	private List<Comment> comments;
	
	@JsonIgnore
	@Column(name="Pedidos")
	@OneToMany (cascade=CascadeType.ALL)
	private List<Pedido> pedidos;

	
	public Usuario(){
		this.pedidos = new ArrayList<Pedido>();
		this.comments = new ArrayList<Comment>();
	}
	
	/*To init a new User*/
	public Usuario(String nombre,String full_name,String email,String imgRuta ,String pass, String phone, String address, String... role){
		this.name = nombre;
		this.full_name = full_name;
		this.email = email;
		this.imgRuta = imgRuta;
		this.password = new BCryptPasswordEncoder().encode(pass);
		this.address=address;
		this.phone=Long.parseLong(phone);
		this.role = new ArrayList<>(Arrays.asList(role));
		this.pedidos = new ArrayList<Pedido>();
		this.comments = new ArrayList<Comment>();
	}
	
	
	//GETTERs & SETTERs
	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(Comment comments) {
		this.comments.add(comments);
	}
	
	public long getId() {
		return id;
	}
	
	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return full_name;
	}

	public void setFullName(String fullName) {
		this.full_name = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRole() {
		return role;
	}

	public void setRole(List<String> role) {
		this.role = role;
	}
	
	public void setRole(String role) {
		this.role.add(role);
	}
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Pedido pedido) {
		this.pedidos.add(pedido);
	}
	
	public String getAddress (){
		return this.address;
	}
	
	public void setAddress (String address){
		this.address=address;
	}
	
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
}
