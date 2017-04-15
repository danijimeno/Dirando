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
    	Usuario u1 = new Usuario("user","Dominguez Pruiz","userDomPru@gmail.com","img/logoNEW.jpg","pass","695698365","Thomas Nolan Kaszas II,5322 Otter Lane,Middleberge FL 32068", "ROLE_USER");
    	Usuario u2 =new Usuario ("user2","Dominguez Pruiz","userDomPru@gmail.com","img/logoNEW.jpg","pass2","695698365","Thomas Nolan Kaszas II,5322 Otter Lane,Middleberge FL 32068", "ROLE_USER");
    	userRepository.save(u1); userRepository.save(u2);
		userRepository.save(new Usuario("admin","Con honor","adminser@dirando.com","img/logoNEW.jpg", "adminpass","695698365","Thomas Nolan Kaszas II,5322 Otter Lane,Middleberge FL 32068", "ROLE_USER", "ROLE_ADMIN"));
		
		/****EXAMPLES CATEGORIES****/
		Categoria category1 = new Categoria("xco");
		Categoria category2 = new Categoria("downhill");
		Categoria category3 = new Categoria("road");
		Categoria category4 = new Categoria("cyclocross");
		Categoria category5 = new Categoria("helmet");
		
		/****EXAMPLES COMMENTS****/
		Comment co0_1 = new Comment ("user", "0_1Lorem ipsum dolor sit amet, consectetur adipisicing elit. Velit omnis animi et iure laudantium vitae, praesentium optio, sapiente distinctio illo?", "XX");
		
		/****EXAMPLES ITEMS****/
		Producto p = new Producto("Specialized Epic XCO 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				3500,5,3,1,"img/ejemplo1.jpg",30,category1.getNombre());
		Producto p1 = new Producto("Specialized XL 2017 2028","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				4600,5,3,1,"img/ejemplo2.jpg",50,category1.getNombre());
		Producto p2 = new Producto("Specialized Warning 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				2500,5,3,1,"img/ejemplo3.jpg",60,category1.getNombre());
		Producto p3 = new Producto("Orbea Alma Carbon 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				3500,5,3,1,"img/ejemplo1.jpg",90,category1.getNombre());
		
		Producto p_1 = new Producto("Specialized Epic XCO 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				3500,5,3,1,"img/ejemplo1.jpg",30,category1.getNombre());
		Producto p1_2 = new Producto("Specialized XL 2017 2028","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				4600,5,3,1,"img/ejemplo2.jpg",21,category1.getNombre());
		Producto p2_3 = new Producto("Specialized Warning 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				2500,5,3,1,"img/ejemplo3.jpg",75,category1.getNombre());
		Producto p3_4 = new Producto("Orbea Alma Carbon 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				3500,5,3,1,"img/ejemplo1.jpg",95,category1.getNombre());
		
		Producto p4 = new Producto("Orbea Oiz Doble 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				4600,5,3,1,"img/ejemplo2.jpg",60,category2.getNombre());
		Producto p5 = new Producto("Orbea Oiz Junior LX 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				2500,5,3,1,"img/ejemplo3.jpg",82,category2.getNombre());
		Producto p6 = new Producto("Merida FX 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				3500,5,3,1,"img/ejemplo1.jpg",55,category2.getNombre());
		Producto p7 = new Producto("Merida RALLY 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				4600,5,3,1,"img/ejemplo2.jpg",60,category2.getNombre());
		
		Producto p4_1 = new Producto("Orbea Oiz Doble 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				4600,5,3,1,"img/ejemplo2.jpg",38,category2.getNombre());
		Producto p5_2 = new Producto("Orbea Oiz Junior LX 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				2500,5,3,1,"img/ejemplo3.jpg",70,category2.getNombre());
		Producto p6_3 = new Producto("Merida FX 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				3500,5,3,1,"img/ejemplo1.jpg",52,category2.getNombre());
		Producto p7_4 = new Producto("Merida RALLY 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				4600,5,3,1,"img/ejemplo2.jpg",86,category2.getNombre());
		
		Producto p8 = new Producto("Merida ProS 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				2500,5,3,1,"img/ejemplo3.jpg",99,category3.getNombre());
		Producto p9 = new Producto("bmc SPORTELITE","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				3500,5,3,1,"img/ejemplo1.jpg",93,category3.getNombre());
		Producto p10 = new Producto("bmc SPORTELITE 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				4600,5,3,1,"img/ejemplo2.jpg",40,category3.getNombre());
		Producto p11 = new Producto("bmc ELITE WC 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				2500,5,3,1,"img/ejemplo3.jpg",22,category3.getNombre());
		
		Producto p8_1 = new Producto("Merida ProS 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				2500,5,3,1,"img/ejemplo3.jpg",22,category3.getNombre());
		Producto p9_2 = new Producto("bmc SPORTELITE","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				3500,5,3,1,"img/ejemplo1.jpg",30,category3.getNombre());
		Producto p10_3 = new Producto("bmc SPORTELITE 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				4600,5,3,1,"img/ejemplo2.jpg",21,category3.getNombre());
		Producto p11_4 = new Producto("bmc ELITE WC 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				2500,5,3,1,"img/ejemplo3.jpg",52,category3.getNombre());
		
		Producto p12 = new Producto("Specialized TopFuel 2014","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				3500,5,3,1,"img/ejemplo1.jpg",30,category4.getNombre());
		Producto p13 = new Producto("Specialized MGH Junior","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				4600,5,3,1,"img/ejemplo2.jpg",80,category4.getNombre());
		Producto p14 = new Producto("Specialized GarminProTeam","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				2500,5,3,1,"img/ejemplo3.jpg",70,category4.getNombre());
		Producto p15 = new Producto("Orbea Orca 2016","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				3500,5,3,1,"img/ejemplo1.jpg",60,category4.getNombre());
		Producto p16 = new Producto("Orbea Orca 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				4600,5,3,1,"img/ejemplo2.jpg",21,category1.getNombre());
		Producto p17 = new Producto("Orbea ORCA WC","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				2500,5,3,1,"img/ejemplo3.jpg",40,category1.getNombre());
		Producto p18= new Producto("Merida REACTO 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				3500,5,3,1,"img/ejemplo1.jpg",30,category1.getNombre());
		Producto p19 = new Producto("Merida REACTO carbon 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				4600,5,3,1,"img/ejemplo2.jpg",21,category1.getNombre());
		Producto p20 = new Producto("Merida SCULTURA","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				2500,5,3,1,"img/ejemplo3.jpg",92,category2.getNombre());
		Producto p21 = new Producto("bmc XTR 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				3500,5,3,1,"img/ejemplo1.jpg",30,category2.getNombre());
		Producto p22 = new Producto("bmc XTR 2016","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				4600,5,3,1,"img/ejemplo2.jpg",21,category2.getNombre());
		Producto p23 = new Producto("bmc JUNIOR Carbon 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				2500,5,3,1,"img/ejemplo3.jpg",92,category2.getNombre());
		
		Producto p24 = new Producto("Merida ProS 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				2500,5,3,1,"img/ejemplo3.jpg",52,category3.getNombre());
		Producto p25 = new Producto("bmc SPORTELITE","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				3500,5,3,1,"img/ejemplo1.jpg",30,category3.getNombre());
		Producto p26 = new Producto("bmc SPORTELITE 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				4600,5,3,1,"img/ejemplo2.jpg",21,category3.getNombre());
		Producto p27 = new Producto("bmc ELITE WC 2017","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				2500,5,3,1,"img/ejemplo3.jpg",62,category3.getNombre());
		
		Producto p28 = new Producto("Specialized TopFuel 2014","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				3500,5,3,1,"img/ejemplo1.jpg",30,category4.getNombre());
		Producto p29 = new Producto("Specialized MGH Junior","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				4600,5,3,1,"img/ejemplo2.jpg",80,category4.getNombre());
		Producto p30 = new Producto("Specialized GarminProTeam","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				2500,5,3,1,"img/ejemplo3.jpg",52,category4.getNombre());
		Producto p31 = new Producto("Orbea Orca 2016","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				3500,5,3,1,"img/ejemplo1.jpg",30,category4.getNombre());
		
		Producto p32 = new Producto("Giro ProSeries","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				35,5,3,1,"img/ejemplo5.jpg",30,category5.getNombre());
		Producto p33 = new Producto("FOX Endura","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				46,5,3,1,"img/ejemplo6.jpg",80,category5.getNombre());
		Producto p34 = new Producto("Giro XL","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				86,5,3,1,"img/ejemplo7.jpg",52,category5.getNombre());
		Producto p35 = new Producto("Specialed XC GAMMA","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				205,5,3,1,"img/ejemplo8.jpg",30,category5.getNombre());
		Producto p36 = new Producto("FOX Endura","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				300,5,3,1,"img/ejemplo5.jpg",80,category5.getNombre());
		Producto p37 = new Producto("Giro ProSeries","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				105,5,3,1,"img/ejemplo6.jpg",52,category5.getNombre());
		Producto p38 = new Producto("Giro XL","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				95,5,3,1,"img/ejemplo7.jpg",30,category5.getNombre());
		Producto p39 = new Producto("Specialed XC GAMMA","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				62,5,3,1,"img/ejemplo8.jpg",80,category5.getNombre());
		Producto p40= new Producto("Specialized GarminProTeam","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				90,5,3,1,"img/ejemplo5.jpg",52,category5.getNombre());
		Producto p41 = new Producto("Giro ProSeries","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				200,5,3,1,"img/ejemplo6.jpg",30,category5.getNombre());
		Producto p42 = new Producto("Specialed XC GAMMA","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				100,5,3,1,"img/ejemplo7.jpg",80,category5.getNombre());
		Producto p43 = new Producto("Specialized GarminProTeam","If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight",
				150,5,3,1,"img/ejemplo8.jpg",52,category5.getNombre());
		
		p.setComments(co0_1);
		
		category1.getProductos().add(p);
		category1.getProductos().add(p1);
		category1.getProductos().add(p2);
		category1.getProductos().add(p3);
		
		category1.getProductos().add(p_1);
		category1.getProductos().add(p1_2);
		category1.getProductos().add(p2_3);
		category1.getProductos().add(p3_4);
		
		
		category2.getProductos().add(p4);
		category2.getProductos().add(p5);
		category2.getProductos().add(p6);
		category2.getProductos().add(p7);
		
		category2.getProductos().add(p4_1);
		category2.getProductos().add(p5_2);
		category2.getProductos().add(p6_3);
		category2.getProductos().add(p7_4);
		
		category3.getProductos().add(p8);
		category3.getProductos().add(p9);
		category3.getProductos().add(p10);
		category3.getProductos().add(p11);
		
		category3.getProductos().add(p8_1);
		category3.getProductos().add(p9_2);
		category3.getProductos().add(p10_3);
		category3.getProductos().add(p11_4);
		
		category3.getProductos().add(p24);
		category3.getProductos().add(p25);
		category3.getProductos().add(p26);
		category3.getProductos().add(p27);
		
		category4.getProductos().add(p12);
		category4.getProductos().add(p13);
		category4.getProductos().add(p14);
		category4.getProductos().add(p15);
		category1.getProductos().add(p16);
		category1.getProductos().add(p17);
		category1.getProductos().add(p18);
		category1.getProductos().add(p19);
		category2.getProductos().add(p20);
		category2.getProductos().add(p21);
		category2.getProductos().add(p22);
		category2.getProductos().add(p23);
		
		category4.getProductos().add(p28);
		category4.getProductos().add(p29);
		category4.getProductos().add(p30);
		category4.getProductos().add(p31);
		
		category5.getProductos().add(p32);
		category5.getProductos().add(p33);
		category5.getProductos().add(p34);
		category5.getProductos().add(p35);
		category5.getProductos().add(p36);
		category5.getProductos().add(p37);
		category5.getProductos().add(p38);
		category5.getProductos().add(p39);
		category5.getProductos().add(p40);
		category5.getProductos().add(p41);
		category5.getProductos().add(p42);
		category5.getProductos().add(p43);
		
		
		
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
		
		productoRepository.saveAndFlush(p_1);
		productoRepository.saveAndFlush(p1_2);
		productoRepository.saveAndFlush(p2_3);
		productoRepository.saveAndFlush(p3_4);
		
		productoRepository.saveAndFlush(p4_1);
		productoRepository.saveAndFlush(p5_2);
		productoRepository.saveAndFlush(p6_3);
		productoRepository.saveAndFlush(p7_4);
		
		productoRepository.saveAndFlush(p8_1);
		productoRepository.saveAndFlush(p9_2);
		productoRepository.saveAndFlush(p10_3);
		productoRepository.saveAndFlush(p11_4);
		
		
		productoRepository.saveAndFlush(p12);
		productoRepository.saveAndFlush(p13);
		productoRepository.saveAndFlush(p14);
		productoRepository.saveAndFlush(p15);
		productoRepository.saveAndFlush(p16);
		productoRepository.saveAndFlush(p17);
		productoRepository.saveAndFlush(p18);
		productoRepository.saveAndFlush(p19);
		productoRepository.saveAndFlush(p20);
		productoRepository.saveAndFlush(p21);
		productoRepository.saveAndFlush(p22);
		productoRepository.saveAndFlush(p23);
		
		productoRepository.saveAndFlush(p24);
		productoRepository.saveAndFlush(p25);
		productoRepository.saveAndFlush(p26);
		productoRepository.saveAndFlush(p27);
		
		productoRepository.saveAndFlush(p28);
		productoRepository.saveAndFlush(p29);
		productoRepository.saveAndFlush(p30);
		productoRepository.saveAndFlush(p31);
		
		productoRepository.saveAndFlush(p31);
		productoRepository.saveAndFlush(p32);
		productoRepository.saveAndFlush(p33);
		productoRepository.saveAndFlush(p34);
		productoRepository.saveAndFlush(p35);
		productoRepository.saveAndFlush(p36);
		productoRepository.saveAndFlush(p37);
		productoRepository.saveAndFlush(p38);
		productoRepository.saveAndFlush(p39);
		productoRepository.saveAndFlush(p40);
		productoRepository.saveAndFlush(p41);
		productoRepository.saveAndFlush(p42);
		productoRepository.saveAndFlush(p43);
		
		
		
		categoriaRepository.saveAndFlush(category1);
		categoriaRepository.saveAndFlush(category2);
		categoriaRepository.saveAndFlush(category3);
		categoriaRepository.saveAndFlush(category4);
		categoriaRepository.saveAndFlush(category5);
		
		
		/*Publi. init*/
		publicidadRepository.save(new Publicidad("Chania","img/carousel1.jpg"));
		publicidadRepository.save(new Publicidad("Chania","img/carousel2.jpg"));
		publicidadRepository.save(new Publicidad("Flower","img/carousel3.jpg"));
    }

}