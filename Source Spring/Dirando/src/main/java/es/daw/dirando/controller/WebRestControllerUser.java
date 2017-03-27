package es.daw.dirando.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import es.daw.dirando.service.UserServices;
import es.daw.dirando.model.Usuario;


@RestController
@RequestMapping("/rest")
public class WebRestControllerUser {
	
	
	@Autowired
	private UserServices us;
	
		@RequestMapping(value = "/updateAccount", method = RequestMethod.PUT)
		public void updateAccount(Authentication http, String phone, String pass, String fullName, String address, String email) {
			if(http != null){
				us.updateUser(http.getName(), phone, new BCryptPasswordEncoder().encode(pass), fullName, address, email);
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
		public void addNewUser(@RequestBody Usuario user){
			us.addUser(Long.toString(user.getPhone()), user.getName(), user.getPassword(), user.getFullName(), user.getAddress(), user.getEmail());
		}
				
		@RequestMapping(value = "/correctLogIn", method = RequestMethod.GET)
		public @ResponseBody String correctLogIn() {
			return "In Log!";
		}
		
		@RequestMapping(value = "/errorLogIn", method = RequestMethod.GET)
		public @ResponseBody String errorLogIn() {
			return "LogIn error!";
		}
		
		
		
}