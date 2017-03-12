package es.daw.dirando.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.daw.dirando.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findUserByEmail(String email);
	Usuario findUserByName(String name);

}
