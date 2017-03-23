package es.daw.dirando.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.daw.dirando.service.UserServices;
import es.daw.dirando.model.Usuario;


@RestController
@RequestMapping("/rest")
public class WebRestControllerLog {
		
	@Autowired
	private UserServices us;
	
	/*START*************************************/
	/*REST METHODS FRONTEND*/
	/**************************************/
		
		@RequestMapping(value = "/isLogged", method = RequestMethod.GET)
		public Usuario isLogged(Authentication http) {
			Usuario user = new Usuario();
			if(http != null){
	    		user =us.getUser(http.getName());
	    	}
	        return user;
		}
		
		/********************************/
		@RequestMapping(value = "/logOut", method = RequestMethod.PUT)
		public String logOut(@RequestBody String data) {
			return "in work...";
		}
		@RequestMapping(value = "/logIn", method = RequestMethod.PUT)
		public String logIn(@RequestBody String data) {
			return "in work...";
		}
		/******************************/
		
	/*FINISH FRONTEND METHODS*************************************/
		
}