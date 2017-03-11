package es.daw.dirando;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.daw.dirando.model.Comment;
import es.daw.dirando.model.Producto;
import es.daw.dirando.model.Publicidad;
import es.daw.dirando.model.Usuario;
import es.daw.dirando.repository.CommentRepository;
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
    
    @Autowired
    CommentRepository commentRepository;
    
    
    
    @PostConstruct
    private void initDatabase() {
    	
    	/****EXAMPLES USERS****/
    	Usuario u1 = new Usuario("user","Gominguez pruiz","julianL@gmail.com","img/usuario1.jpg","pass","695698365","Thomas Nolan Kaszas II,5322 Otter Lane,Middleberge FL 32068", "ROLE_USER");
    	Usuario u2 =new Usuario ("user2","Gominguez pruiz","julianL@gmail.com","img/usuario1.jpg","pass2","695698365","Thomas Nolan Kaszas II,5322 Otter Lane,Middleberge FL 32068", "ROLE_USER");
    	userRepository.save(u1);
    	userRepository.save(u2);
		userRepository.save(new Usuario("admin","Con honor","adminser@gmail.com","img/user.jpg", "adminpass","695698365","Thomas Nolan Kaszas II,5322 Otter Lane,Middleberge FL 32068", "ROLE_USER", "ROLE_ADMIN"));
		
		/*
		Categoria category1 = new Categoria("BikeRoad");
		Categoria category2 = new Categoria("BikeMtb");
		categoriaRepository.save(category1);
		categoriaRepository.save(category2);
		*/
		
		/****EXAMPLES COMMENTS****/
		
		Comment co0 = new Comment ("user", "0Lorem ipsum dolor sit amet, consectetur adipisicing elit. Velit omnis animi et iure laudantium vitae, praesentium optio, sapiente distinctio illo?", "XX");
		Comment co = new Comment ("user", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Velit omnis animi et iure laudantium vitae, praesentium optio, sapiente distinctio illo?", "XX");
		Comment co2 = new Comment ("user", "2Lorem ipsum dolor sit amet, consectetur adipisicing elit. Velit omnis animi et iure laudantium vitae, praesentium optio, sapiente distinctio illo?", "XX");
		Comment co3 = new Comment ("user", "3Lorem ipsum dolor sit amet, consectetur adipisicing elit. Velit omnis animi et iure laudantium vitae, praesentium optio, sapiente distinctio illo?", "XX");
		Comment co4 = new Comment ("user", "4Lorem ipsum dolor sit amet, consectetur adipisicing elit. Velit omnis animi et iure laudantium vitae, praesentium optio, sapiente distinctio illo?", "XX");
		Comment co5 = new Comment ("user", "5Lorem ipsum dolor sit amet, consectetur adipisicing elit. Velit omnis animi et iure laudantium vitae, praesentium optio, sapiente distinctio illo?", "XX");
		Comment co6 = new Comment ("user", "6Lorem ipsum dolor sit amet, consectetur adipisicing elit. Velit omnis animi et iure laudantium vitae, praesentium optio, sapiente distinctio illo?", "XX");
		Comment co7 = new Comment ("user", "7Lorem ipsum dolor sit amet, consectetur adipisicing elit. Velit omnis animi et iure laudantium vitae, praesentium optio, sapiente distinctio illo?", "XX");
		Comment co8 = new Comment ("user", "8Lorem ipsum dolor sit amet, consectetur adipisicing elit. Velit omnis animi et iure laudantium vitae, praesentium optio, sapiente distinctio illo?", "XX");
		Comment co9 = new Comment ("user", "9Lorem ipsum dolor sit amet, consectetur adipisicing elit. Velit omnis animi et iure laudantium vitae, praesentium optio, sapiente distinctio illo?", "XX");
		Comment co10 = new Comment ("user", "10Lorem ipsum dolor sit amet, consectetur adipisicing elit. Velit omnis animi et iure laudantium vitae, praesentium optio, sapiente distinctio illo?", "XX");
		Comment co11 = new Comment ("user", "11Lorem ipsum dolor sit amet, consectetur adipisicing elit. Velit omnis animi et iure laudantium vitae, praesentium optio, sapiente distinctio illo?", "XX");
		
		Comment co0_1 = new Comment ("user", "0_1Lorem ipsum dolor sit amet, consectetur adipisicing elit. Velit omnis animi et iure laudantium vitae, praesentium optio, sapiente distinctio illo?", "XX");
		
		
		/****EXAMPLES ITEMS****/
		Producto p = new Producto("Specialized Epic HT 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				3500,5,"img/ejemplo1.jpg",30,co0,"Sport");
		
		productoRepository.save(new Producto("Specialized shit WC 2028","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				4600,2,"img/ejemplo2.jpg",21,co,"Sport"));
		productoRepository.save(new Producto("Specialized Warning 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				2500,0,"img/ejemplo3.jpg",2,co2,"Sport"));
		productoRepository.save(new Producto("Orbea Alma 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				3500,5,"img/ejemplo1.jpg",30,co3,"Sport"));
		productoRepository.save(new Producto("Orbea Alma 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				4600,2,"img/ejemplo2.jpg",21,co4,"Sport"));
		productoRepository.save(new Producto("Orbea Alma 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				2500,0,"img/ejemplo3.jpg",2,co5,"Sport"));
		productoRepository.save(new Producto("Merida no se que 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				3500,5,"img/ejemplo1.jpg",30,co6,"Sport"));
		productoRepository.save(new Producto("Merida no se que 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				4600,2,"img/ejemplo2.jpg",21,co7,"Sport"));
		productoRepository.save(new Producto("Merida no se que 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				2500,0,"img/ejemplo3.jpg",2,co8,"Sport"));
		productoRepository.save(new Producto("bmc dopada 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				3500,5,"img/ejemplo1.jpg",30,co9,"Sport"));
		productoRepository.save(new Producto("bmc dopada 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				4600,2,"img/ejemplo2.jpg",21,co10,"Sport"));
		productoRepository.save(new Producto("bmc dopada 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				2500,0,"img/ejemplo3.jpg",2,co11,"Sport"));
		
		p.setComments(co0_1);
		productoRepository.save(p);
		
		/*Publi. init*/
		publicidadRepository.save(new Publicidad("Chania","img/carousel1.jpg"));
		publicidadRepository.save(new Publicidad("Chania","img/carousel2.jpg"));
		publicidadRepository.save(new Publicidad("Flower","img/carousel3.jpg"));
    }

}