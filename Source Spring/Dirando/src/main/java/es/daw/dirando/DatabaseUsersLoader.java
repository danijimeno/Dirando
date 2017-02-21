package es.daw.dirando;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.daw.dirando.model.Usuario;
import es.daw.dirando.repository.UsuarioRepository;


@Component
public class DatabaseUsersLoader {

    @Autowired
    private UsuarioRepository userRepository;

    @PostConstruct
    private void initDatabase() {
    	
    	userRepository.save(new Usuario("user", "pass", "ROLE_USER"));
		userRepository.save(new Usuario("admin", "adminpass", "ROLE_USER", "ROLE_ADMIN"));
    }

}