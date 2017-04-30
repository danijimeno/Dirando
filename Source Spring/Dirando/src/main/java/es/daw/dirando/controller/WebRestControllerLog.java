package es.daw.dirando.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rest")
public class WebRestControllerLog {
		
		@RequestMapping(value = "/log", method = RequestMethod.GET)
		public ResponseEntity<String> isLogged(Authentication http) {
			if(http != null){
				return new ResponseEntity<>("Logged In!",HttpStatus.OK );
	    	}else{
	    		return new ResponseEntity<>("Not Logged !",HttpStatus.OK );
	    	}
		}
		
		
		
		/*Redirection of the LogIn form; message methods*/
			@RequestMapping(value = "/correctLogIn", method = RequestMethod.GET)
			public ResponseEntity<String> correctLogIn() {
				return new ResponseEntity<>("In Log!", HttpStatus.ACCEPTED);
			}
			@RequestMapping(value = "/errorLogIn", method = RequestMethod.GET)
			public ResponseEntity<String> errorLogIn() {
				return new ResponseEntity<>("LogIn error!", HttpStatus.BAD_REQUEST);
			}
		/*End Redirection of the LogIn form; message methods*/	

}