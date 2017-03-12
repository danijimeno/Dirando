package es.daw.dirando;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.daw.dirando.model.Categoria;
import es.daw.dirando.model.Comment;
import es.daw.dirando.model.Producto;
import es.daw.dirando.model.Publicidad;
import es.daw.dirando.model.Usuario;
import es.daw.dirando.repository.CategoriaRepository;
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
    
    @Autowired
    CategoriaRepository categoriaRepository;
    
    
    
    @PostConstruct
    private void initDatabase() {
    	
    	/****EXAMPLES USERS****/
    	Usuario u1 = new Usuario("user","Gominguez pruiz","julianL@gmail.com","img/usuario1.jpg","pass","695698365","Thomas Nolan Kaszas II,5322 Otter Lane,Middleberge FL 32068", "ROLE_USER");
    	Usuario u2 =new Usuario ("user2","Gominguez pruiz","julianL@gmail.com","img/usuario1.jpg","pass2","695698365","Thomas Nolan Kaszas II,5322 Otter Lane,Middleberge FL 32068", "ROLE_USER");
    	userRepository.save(u1);
    	userRepository.save(u2);
		userRepository.save(new Usuario("admin","Con honor","adminser@gmail.com","img/user.jpg", "adminpass","695698365","Thomas Nolan Kaszas II,5322 Otter Lane,Middleberge FL 32068", "ROLE_USER", "ROLE_ADMIN"));
		
		
		Categoria category1 = new Categoria("BikeRoad");
		Categoria category2 = new Categoria("BikeMtb");
		
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
				3500,5,3,1,"img/ejemplo1.jpg",30,category1.getNombre());
		
		Producto p1 = new Producto("Specialized shit WC 2028","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				4600,5,3,1,"img/ejemplo2.jpg",21,category1.getNombre());
		Producto p2 = new Producto("Specialized Warning 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				2500,5,3,1,"img/ejemplo3.jpg",2,category1.getNombre());
		Producto p3 = new Producto("Orbea Alma 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				3500,5,3,1,"img/ejemplo1.jpg",30,category1.getNombre());
		Producto p4 = new Producto("Orbea Alma 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				4600,5,3,1,"img/ejemplo2.jpg",21,category1.getNombre());
		Producto p5 = new Producto("Orbea Alma 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				2500,5,3,1,"img/ejemplo3.jpg",2,category1.getNombre());
		Producto p6 = new Producto("Merida no se que 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				3500,5,3,1,"img/ejemplo1.jpg",30,category1.getNombre());
		Producto p7 = new Producto("Merida no se que 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				4600,5,3,1,"img/ejemplo2.jpg",21,category1.getNombre());
		Producto p8 = new Producto("Merida no se que 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				2500,5,3,1,"img/ejemplo3.jpg",2,category1.getNombre());
		Producto p9 = new Producto("bmc dopada 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				3500,5,3,1,"img/ejemplo1.jpg",30,category1.getNombre());
		Producto p10 = new Producto("bmc dopada 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				4600,5,3,1,"img/ejemplo2.jpg",21,category1.getNombre());
		Producto p11 = new Producto("bmc dopada 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				2500,5,3,1,"img/ejemplo3.jpg",2,category1.getNombre());
		
		p.setComments(co0_1);

		category1.getProductos().add(p);
		category1.getProductos().add(p1);
		category1.getProductos().add(p2);
		category1.getProductos().add(p3);
		category1.getProductos().add(p4);
		category1.getProductos().add(p5);
		category1.getProductos().add(p6);
		category1.getProductos().add(p7);
		category1.getProductos().add(p8);
		category1.getProductos().add(p9);
		category1.getProductos().add(p10);
		category1.getProductos().add(p11);
		
		productoRepository.saveAndFlush(p);
		productoRepository.saveAndFlush(p1);
		productoRepository.saveAndFlush(p2);
		productoRepository.saveAndFlush(p3);
		productoRepository.saveAndFlush(p4);
		productoRepository.saveAndFlush(p5);
		productoRepository.saveAndFlush(p6);
		productoRepository.saveAndFlush(p7);
		productoRepository.saveAndFlush(p8);
		productoRepository.saveAndFlush(p9);
		productoRepository.saveAndFlush(p10);
		productoRepository.saveAndFlush(p11);
		
		
		categoriaRepository.saveAndFlush(category1);
		categoriaRepository.saveAndFlush(category2);
		
		/*Publi. init*/
		publicidadRepository.save(new Publicidad("Chania","img/carousel1.jpg"));
		publicidadRepository.save(new Publicidad("Chania","img/carousel2.jpg"));
		publicidadRepository.save(new Publicidad("Flower","img/carousel3.jpg"));
    }

}