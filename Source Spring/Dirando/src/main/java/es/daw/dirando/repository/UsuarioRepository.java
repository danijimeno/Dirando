package es.daw.dirando.repository;

import org.springframework.data.repository.CrudRepository;

import es.daw.dirando.model.Usuario;




public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	
	Usuario findUserByEmail(String email);
	Usuario findUserByName(String name);

}
