package es.daw.dirando.security;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.daw.dirando.model.UserComponent;
import es.daw.dirando.model.Usuario;

@RestController
public class LoginControllerRest {
	
	@Autowired
	private UserComponent userComponent;
	
	
	
	private static final Logger log = LoggerFactory.getLogger(LoginControllerRest.class);
	
	@RequestMapping("/rest/login")
	public ResponseEntity<Usuario> logIn(){
		if (!userComponent.isLoggedUser()) {
			log.info("Not user logged");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			Usuario loggedUser = userComponent.getLoggedUser();
			log.info("Logged as " + loggedUser.getName());
			return new ResponseEntity<>(loggedUser, HttpStatus.OK);
		}
	}
	
	@RequestMapping("/rest/logout")
	public ResponseEntity<Boolean> logOut(HttpSession session){
		if(!userComponent.isLoggedUser()){
			log.info("No user logged");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}else{
			session.invalidate();
			log.info("Logged out");
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
	}
}
