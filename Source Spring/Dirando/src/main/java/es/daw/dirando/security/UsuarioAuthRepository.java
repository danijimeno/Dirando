package es.daw.dirando.security;

import java.util.ArrayList;
import java.util.List;

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

import es.daw.dirando.model.Usuario;
import es.daw.dirando.repository.UsuarioRepository;



@Component
public class UsuarioAuthRepository implements AuthenticationProvider{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public Authentication authenticate(Authentication httpSecurity) throws AuthenticationException {
		
		Usuario user = usuarioRepository.findUserByName(httpSecurity.getName());

		if (user == null) {
			throw new BadCredentialsException("User not found");
		}

		String password = (String) httpSecurity.getCredentials();
		if (!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
			throw new BadCredentialsException("Wrong password");
		}

		List<GrantedAuthority> roles = new ArrayList<>();
		for (String role : user.getRole()) {
			roles.add(new SimpleGrantedAuthority(role));
		}

		return new UsernamePasswordAuthenticationToken(user.getName(), password, roles);
		
	}

	@Override
	public boolean supports(Class<?> authenticationObject) {
		// TODO Auto-generated method stub
		return true;
	}

}
