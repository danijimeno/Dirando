package es.daw.dirando.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.daw.dirando.service.UserServices;
import es.daw.dirando.model.Usuario;


@RestController
@RequestMapping("/rest")
public class WebRestControllerUser {
	
	@Autowired
	private UserServices us;
	
	/*START*************************************/
	/*REST METHODS FRONTEND*/
	/**************************************/
		
		@RequestMapping(value = "/updateAccount", method = RequestMethod.PUT)
		public void updateAccount(Authentication http, String phone, String pass, String fullName, String address, String email) {
			if(http != null){
				us.updateUser(http.getName(), phone, pass, fullName, address, email);
			}
		}
		
		@RequestMapping(value = "/getUser", method = RequestMethod.GET)
		public Usuario getUser(Authentication http) {
			if(http != null){
				return us.getUser(http.getName());
			}
			return new Usuario();
		}
				
		@RequestMapping(value = "/addNewUser", method = RequestMethod.POST)
		public void addNewUser(String phone, String name, String pass, String fullName, String address, String email) {
			us.addUser(phone, name, pass, fullName, address, email);
		}
				
	/*FINISH FRONTEND METHODS*************************************/
		
}