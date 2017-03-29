package es.daw.dirando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
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
	
		/*Modify account Method*/
			@RequestMapping(value = "/account", method = RequestMethod.PUT)
			public ResponseEntity<Void> updateAccount(Authentication http, String phone, String pass, String fullName, String address, String email) {
				if(http != null){
					us.updateUser(http.getName(), phone, new BCryptPasswordEncoder().encode(pass), fullName, address, email);
					return new ResponseEntity<>(HttpStatus.OK);
				}else{
					return new ResponseEntity<>(HttpStatus.LOCKED);
				}
			}
		/*EndModify account Method*/
		
		/*User Methods*/
			@RequestMapping(value = "/user", method = RequestMethod.GET)
			public ResponseEntity<Usuario> getUser(Authentication http) {
				if(http != null){
					Usuario user = us.getUser(http.getName());
					if (user != null){
						return new ResponseEntity<>(user, HttpStatus.OK);
					}else{
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					}
				}else{
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				}	
			}		
			@RequestMapping(value = "/user", method = RequestMethod.POST)
			public ResponseEntity<Void> addNewUser(@RequestBody Usuario user){
				us.addUser(Long.toString(user.getPhone()), user.getName(), user.getPassword(), user.getFullName(), user.getAddress(), user.getEmail());
				return new ResponseEntity<>(HttpStatus.OK);
			}
		/*End User Methods*/
}