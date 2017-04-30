package es.daw.dirando.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import es.daw.dirando.model.UserComponent;
import es.daw.dirando.model.Usuario;
import es.daw.dirando.repository.UsuarioRepository;




@Component
public class UsuarioAuthRepository implements AuthenticationProvider{

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UserComponent userComponent;
	
	private static final Logger log = LoggerFactory.getLogger(LoginControllerRest.class);

	@Override
	public Authentication authenticate(Authentication httpSecurity) throws AuthenticationException {

		String username = httpSecurity.getName();
		String password = (String) httpSecurity.getCredentials();

		log.info(username);
		Usuario user = usuarioRepository.findUserByName(username);

		if (user == null) {
			throw new BadCredentialsException("User not found");
		}

		if (!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
			throw new BadCredentialsException("Wrong password");
		}else{
			userComponent.setLoggedUser(user);
			List<GrantedAuthority> roles = new ArrayList<>();
			for (String role : user.getRole()) {
				roles.add(new SimpleGrantedAuthority(role));
			}

			return new UsernamePasswordAuthenticationToken(user.getName(), password, roles);
		}



	}

	@Override
	public boolean supports(Class<?> authenticationObject) {
		// TODO Auto-generated method stub
		return true;
	}

}
