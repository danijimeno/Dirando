package es.daw.dirando.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="Nombre")
	private String name;
	
	@Column(name="Apellidos")
	private String apellidos;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="Password")
	private String password;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@Column(name="Role")
	private List<String> role;
	
	@Column(name="Pedidos")
	@OneToMany
	private List<Pedido> pedidos;

	public Usuario(){}
	
	public Usuario(String nombre, String pass, String... role){
		this.name = nombre;
		this.password = new BCryptPasswordEncoder().encode(pass);;
		this.role = new ArrayList<>(Arrays.asList(role));
	}
	


	//GETTERs & SETTERs
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
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

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	
}
