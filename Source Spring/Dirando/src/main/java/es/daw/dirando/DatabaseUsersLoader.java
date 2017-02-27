package es.daw.dirando;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.daw.dirando.model.Producto;
import es.daw.dirando.model.Publicidad;
import es.daw.dirando.model.Usuario;
import es.daw.dirando.repository.ProductoRepository;
import es.daw.dirando.repository.PublicidadRepository;
import es.daw.dirando.repository.UsuarioRepository;


@Component
public class DatabaseUsersLoader {

    @Autowired
    private UsuarioRepository userRepository;
    
    @Autowired 
    ProductoRepository productoRepository;
    
    @Autowired
    PublicidadRepository publicidadRepository;

    @PostConstruct
    private void initDatabase() {
    	
    	userRepository.save(new Usuario("user", "pass", "ROLE_USER"));
		userRepository.save(new Usuario("admin", "adminpass", "ROLE_USER", "ROLE_ADMIN"));
		
		productoRepository.save(new Producto("Specialized Epic HT 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				3500,5,"img/ejemplo1.jpg",30,"Sport"));
		productoRepository.save(new Producto("Specialized shit WC 2028","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				4600,2,"img/ejemplo2.jpg",21,"Sport"));
		productoRepository.save(new Producto("Specialized Warning 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				2500,0,"img/ejemplo3.jpg",2,"Sport"));
		
		publicidadRepository.save(new Publicidad("Chania","img/carousel1.jpg"));
		publicidadRepository.save(new Publicidad("Chania","img/carousel2.jpg"));
		publicidadRepository.save(new Publicidad("Flower","img/carousel3.jpg"));
    }

}