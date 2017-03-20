package es.daw.dirando.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import es.daw.dirando.model.Comment;
import es.daw.dirando.model.Pedido;
import es.daw.dirando.model.Producto;
import es.daw.dirando.model.Usuario;
import es.daw.dirando.model.Categoria;
import es.daw.dirando.model.Publicidad;

import es.daw.dirando.repository.CategoriaRepository;
import es.daw.dirando.repository.CommentRepository;
import es.daw.dirando.repository.PedidoRepository;
import es.daw.dirando.repository.ProductoRepository;
import es.daw.dirando.repository.PublicidadRepository;
import es.daw.dirando.repository.UsuarioRepository;

@Service
public class ToolsServices {

	@Autowired
	private Pedido pedido; //For the cart session...
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PublicidadRepository publicityRepository;
	
		/****************************/
		/*Tools for the session cart*/
		/****************************/
			//Add in a session products in cart
			public void addCartSession(String info, String name, String price){
				pedido.setPedido(new Producto ( Long.valueOf(info),name, Float.valueOf(price) ));
			}
			//Calculate cart size
			public int cartSize(){
				return pedido.getPedidos().size();
			}
			//Return the session shopping Cart
			public List<Producto> getShoppingCart(){
				return pedido.getPedidos();
			}
			//Clear the shopping cart
			public void clearCart(){
				pedido.getPedidos().clear();
			}
		/****************************/
		
		//Return the cart size
		public int sizeOrderUser(String name){
			return usuarioRepository.findUserByName(name).getPedidos().size();
		}
		
		//Add new User in to the System (Generic User)
		public void addUser(String phone, String name, String pass, String fullName, String address, String email) {
	    	usuarioRepository.save(new Usuario (name,fullName,email,"img/logoNEW.jpg",pass,phone,address,"ROLE_USER"));
	    }
		
		//Update user data
		public void updateUser(String name, String phone, String pass, String fullName, String address, String email) {
	    	usuarioRepository.findUserByName(name).setPhone(Long.valueOf(phone));
	    	usuarioRepository.findUserByName(name).setPassword(pass);
	    	usuarioRepository.findUserByName(name).setFullName(fullName);
	    	usuarioRepository.findUserByName(name).setAddress(address);
	    	usuarioRepository.findUserByName(name).setEmail(email);
	    	saveUser(name);
		}
		
		//Add the cart into the user account
		public void makeOrderfromSessionCart (Authentication http){
			usuarioRepository.findUserByName(http.getName()).setPedidos(pedido);
		}
		
		//Add comment into a product
		public void addCommentIntoProduct (String nameHttp, String id, String comment, String rating){
			if (Integer.parseInt(rating)==3){
	    		Comment co = new Comment (usuarioRepository.findUserByName(nameHttp).getName(), comment, "The Best!");
	    		productoRepository.findProductoById(Long.parseLong(id)).setComments(co);
	    		productoRepository.findProductoById(Long.parseLong(id)).incrementTheBest();
	    	}else if(Integer.parseInt(rating)==2){
	    		Comment co = new Comment (usuarioRepository.findUserByName(nameHttp).getName(), comment, "Must Improve!");
	    		productoRepository.findProductoById(Long.parseLong(id)).setComments(co);
	    		productoRepository.findProductoById(Long.parseLong(id)).incrementMustImprove();
	    	}else if(Integer.parseInt(rating)==1){
	    		Comment co = new Comment (usuarioRepository.findUserByName(nameHttp).getName(), comment, "Bad!");
	    		productoRepository.findProductoById(Long.parseLong(id)).setComments(co);
	    		productoRepository.findProductoById(Long.parseLong(id)).incrementBad();
	    	}else{
	    		Comment co = new Comment (usuarioRepository.findUserByName(nameHttp).getName(), comment, null);
	    		productoRepository.findProductoById(Long.parseLong(id)).setComments(co);
	    	}
			/*Update the new data product*/
			saveProduct(productoRepository.findProductoById(Long.parseLong(id)));
		}
		
		public void saveProduct(Producto pro){
			productoRepository.saveAndFlush(pro);
		}
		
		//Get a User (type Usuario) by name
		public Usuario getUser (String nameHttp){
			return usuarioRepository.findUserByName(nameHttp);
		}
		
		//Save new user into database
		public void saveUser(String httpName){
			usuarioRepository.saveAndFlush(getUser(httpName));
		}
		
		//Count the elements into the database
		public long getUserNumber(){
			return usuarioRepository.count();	
		}
		
		/*Count items numbers about product, order, category and comment*/
		public long getProductsNumber(){
			return productoRepository.count();	
		}		
		public long getPedidoNumber(){
			return pedidoRepository.count();	
		}		
		public long getCategoryNumber(){
			return categoriaRepository.count();	
		}
		public long getCommentsNumber(){
			return commentRepository.count();	
		}
		
		//To get the total orders from specific user
		public int getTotalOrders(String nameHttp){
			return usuarioRepository.findUserByName(nameHttp).getPedidos().size();
		}
		
		//The user are logged and the cart isn't empty?
		public String isLoggedANDThereAreProducts (Authentication http){
			if(http != null && pedido.getPedidos().size()>0){
	    		return "0";
	    	}else if(pedido.getPedidos().size()<1){
	    		return "2";
	    	}else{
	    		return "1";
	    	}
		}
		
		//Return all comments about specific product
		public List<Comment> getComments(String idProduct){
			return productoRepository.findProductoById(Long.parseLong(idProduct)).getComments();
		}
		
		//Return the total Cart price 
		public float calculateTotalPrice (){
			float subtotal=0;
			for (int i = 0; i< cartSize(); i++){
	    		 subtotal+= pedido.getPedidos().get(i).getPrecio();
	    	}
			return subtotal;
		}
		
		//IN WORK...
		public float[] dataRating (long id){
			float [] data= new float [3];
			float total = productoRepository.findProductoById(id).getTheBest() + productoRepository.findProductoById(id).getMustImprove() + productoRepository.findProductoById(id).getBad();
	    	data[0] = productoRepository.findProductoById(id).getTheBest() / total * 100;
	    	data[1] = productoRepository.findProductoById(id).getMustImprove() / total * 100;
	    	data[2] = productoRepository.findProductoById(id).getBad() / total * 100;
	    	return data;
		}
		
		//Search TOOLS 
		public Page<Producto> getProductsByCategory(String category, Pageable page){
			return productoRepository.findByCategoria(category, page);
		}
		public Page<Producto> getProductsByName(String name, Pageable page){
			return productoRepository.findByNombre(name, page);
		}
		public Page<Producto> getAllProducts(Pageable page){
			return productoRepository.findAll(page);
		}
		
		//Get specific items and full contents about entities
		public Producto getSpecificProduct(long id){
			return productoRepository.findOne(id);
		}
		public Page<Producto> get3Items(Pageable page){
			return productoRepository.findAll(page);
		}
		public Iterable<Publicidad> findAllPublicity(){
			return publicityRepository.findAll();
		}
		public Categoria getSpecificCategory(String category){
			return categoriaRepository.findByName(category);
		}
		
}