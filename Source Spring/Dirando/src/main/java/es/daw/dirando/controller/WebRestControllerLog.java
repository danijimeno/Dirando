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
public class WebRestControllerLog {
		
	@Autowired
	private UserServices us;
		
		@RequestMapping(value = "/isLogged", method = RequestMethod.GET)
		public Usuario isLogged(Authentication http) {
			Usuario user = new Usuario();
			if(http != null){
	    		user =us.getUser(http.getName());
	    	}
	        return user;
		}

		
}