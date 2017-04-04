package es.daw.dirando.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import es.daw.dirando.model.Pedido;
import es.daw.dirando.model.Usuario;

import es.daw.dirando.repository.UsuarioRepository;

@Service
public class UserServices {

	@Autowired
	private Pedido pedido; //For the cart session...
	
	@Autowired
	private UsuarioRepository usuarioRepository;
				
		//Add new User in to the System (Generic User)
		public void addUser(String phone, String name, String pass, String fullName, String address, String email) {
	    	usuarioRepository.save(new Usuario (name,fullName,email,"img/logoNEW.jpg",pass,phone,address,"ROLE_USER"));
	    }
		
		//Update user data
		public void updateUser(String name, long phone, String pass, String fullName, String address, String email) {
	    	Usuario user = usuarioRepository.findUserByName(name);
	    	user.setPhone(phone);
	    	user.setPassword(pass);
	    	user.setFullName(fullName);
	    	user.setAddress(address);
	    	user.setEmail(email);
	    	saveUser(name);
		}
						
		//Get a User (type User) by name
		public Usuario getUser (String nameHttp){
			return usuarioRepository.findUserByName(nameHttp);
		}
		
		//Save new user into database
		public void saveUser(String httpName){
			usuarioRepository.saveAndFlush(getUser(httpName));
		}
		
		//Count the users into the database
		public long getUserNumber(){
			return usuarioRepository.count();	
		}
		
		
		//To get the total orders from specific user
		public int getTotalOrders(String nameHttp){
			return usuarioRepository.findUserByName(nameHttp).getPedidos().size();
		}
		
		//The user are logged and the cart isn't empty?
		public String isLoggedANDThereAreProducts (Authentication http){
			if(http != null && pedido.getPedidos().size()>0){
	    		return "0";
	    	}else if(pedido.getPedidos().size()<1){
	    		return "2";
	    	}else{
	    		return "1";
	    	}
		}
		
		public List<Usuario> getUsers (){
			return usuarioRepository.findAll();
		}
		
		public Usuario getUserById (long id){
			return usuarioRepository.findOne(id);
		}
		
		public Usuario addUserAdmin(Usuario user){
			List<String> ro = new ArrayList<>();
			ro.add("ROLE_USER");
			ro.add("ROLE_ADMIN");
			user.setRole(ro);
			
			usuarioRepository.save(user);
			return user;
		}
		
		
		
		
		
		
		
}