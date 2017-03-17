package es.daw.dirando.controller;

import java.util.List;
import java.text.DecimalFormat;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.daw.dirando.repository.CategoriaRepository;
import es.daw.dirando.repository.CommentRepository;
import es.daw.dirando.repository.PedidoRepository;
import es.daw.dirando.repository.ProductoRepository;
import es.daw.dirando.repository.PublicidadRepository;
import es.daw.dirando.repository.UsuarioRepository;
import es.daw.dirando.service.FrontServices;
import es.daw.dirando.model.Comment;
import es.daw.dirando.model.Pedido;
import es.daw.dirando.model.Producto;
import es.daw.dirando.model.Usuario;


@Controller
public class WebController {
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private PublicidadRepository publicidadRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private CommentRepository comentarioRepository;
	
	@Autowired
	private Pedido pedido;
	
	@Autowired
	private FrontServices fs;
	
	@Autowired
	private Producto producto;
	
	/*************************************************/
	/* Simple Queries */
	/*************************************************/
	
		/* index Query */
	    @RequestMapping("/")
	    public String index(Model model,Authentication http) {
	    	model.addAttribute("productos",fs.findAllProducts());
	    	model.addAttribute("carrusel",publicidadRepository.findAll());
	    	model.addAttribute("countItems", fs.cartSize() );
	    	if(http != null){
	    		model.addAttribute("usuario",fs.getUser(http.getName()));
	    	}
	        return "index";
	    }
	    
	    /*payMethods query*/
	    @RequestMapping("/payMethods")
	    public String payMethods(Model model,Authentication http) {
	    	model.addAttribute("countItems", fs.cartSize() );
	    	if(http != null){
	    		model.addAttribute("usuario",fs.getUser(http.getName()));
	    	}
	    	return "paginaPaymentsMethods";
	    }
	    
	    /*Buy process*/
	    @RequestMapping("/buy")
	    public String buyButton(Model model,Authentication http) {
	    	if(http != null && fs.cartSize()>0){
	    		/*Add the current Order into the User logged*/
	    		fs.makeOrderfromSessionCart(http);
	    		fs.clearCart();
	    		fs.saveUser(http.getName());
		    	model.addAttribute("usuario",fs.getUser(http.getName()));
		    	model.addAttribute("countItems", fs.cartSize() );
	    		return "paginaPago";
	    	}else{
	    		return "/";
	    	}
	    }
	    
	    /* register Query */
	    @RequestMapping("/register")
	    public String register(Model model) {
	    	model.addAttribute("countItems", fs.cartSize() );
	    	return "paginaRegistro";
	    }
	    
	    /* add user Query */
	    @RequestMapping("/addUser")
	    public String addUser(Model model, @RequestParam(value = "phone") String phone, @RequestParam(value = "name") String name, @RequestParam(value = "pass") String pass, @RequestParam(value = "fullName") String fullName, @RequestParam(value = "address") String address, @RequestParam(value = "email") String email) {
	    	fs.addUser(phone, name, pass, fullName, address, email);
	    	return "/";
	    }
	    
	    /* shoppingCart Query */
	    @RequestMapping("/shoppingCart")
	    public String shoppingCart(Model model,Authentication http) {
	    	model.addAttribute("countItems", fs.cartSize() );
	    	model.addAttribute("subtotal",fs.calculateTotalPrice());
	    	if(http != null){	    		
	    		model.addAttribute("usuario",fs.getUser(http.getName()));
	    	}
	    	return "paginaCarrito";
	    }
	    
	    
	    /* product Query */
	    @RequestMapping("/Producto/{id}")
	    public String productoInfo(Model model,@PathVariable long id, Authentication http){
	    	if(http != null){
	    		model.addAttribute("usuario",fs.getUser(http.getName()));
	    	}
	    	/*IN WORK...*/
	    	float total = productoRepository.findProductoById(id).getTheBest() + productoRepository.findProductoById(id).getMustImprove() + productoRepository.findProductoById(id).getBad();
	    	float best = productoRepository.findProductoById(id).getTheBest() / total * 100;
	    	float improve = productoRepository.findProductoById(id).getMustImprove() / total * 100;
	    	float bad = productoRepository.findProductoById(id).getBad() / total * 100;
	
	    	model.addAttribute("countItems", fs.cartSize() );
	    	model.addAttribute("producto",fs.getSpecificProduct(id));
	    	
	    	DecimalFormat decimals = new DecimalFormat("0");
	    	model.addAttribute("best", decimals.format(best));
	    	model.addAttribute("improve", decimals.format(improve));
	    	model.addAttribute("worst", decimals.format(bad));
	    	
	    	return "paginaDetalleProducto";
	    }
	    
	    /* product List Query */
	    @RequestMapping("/ListadoProductoSearch")
	    public String listadoProductos(Model model,Authentication http, @RequestParam(value = "inp-search")String search){
	    	model.addAttribute("resultSearch",search);
	    	model.addAttribute("productos",fs.findAllProducts());
	    	model.addAttribute("countItems", fs.cartSize() );
	    	if(http != null){
	    		model.addAttribute("usuario",fs.getUser(http.getName()));
	    	}
	    	return "paginaListadoProductos";
	    }
	    
	    /* product category List Query */
	    @RequestMapping("/ProductoCategoria")
	    public @ResponseBody String listadoProductoCategoria (Model model, Authentication http, @RequestParam(value = "cat")String cat){
	    	model.addAttribute("resultSearch",cat);
	    	model.addAttribute("productos",fs.findAllProducts());
	    	//int countItems = pedido.getPedidos().size();
	    	model.addAttribute("countItems", fs.cartSize() );
	    	if(http != null){
	    		model.addAttribute("usuario",fs.getUser(http.getName()));
	    	}
	    	return "paginaListadoProductos";
	    }
	    
	    /* user Query */
	    @RequestMapping("/usuario")
	    public String usuarioreg(Model model, HttpServletRequest request, Authentication auth) {
	    	if(request.isUserInRole("ADMIN")){
	    		long numProducts = fs.getProductsNumber();
	    		model.addAttribute("admin", request.isUserInRole("ADMIN"));
	    		model.addAttribute("numProd",numProducts);
		    	model.addAttribute("numUsers",fs.getUserNumber());
		    	model.addAttribute("numPed", fs.getPedidoNumber());
		    	model.addAttribute("numCom", fs.getCommentsNumber());
		    	model.addAttribute("numPunt",numProducts);
		    	model.addAttribute("numCat", fs.getCategoryNumber());
	    		return "adminIndex";
	    	}else{
	    		model.addAttribute("usuario", request.getAttribute("USER"));
	    		model.addAttribute("usuario",fs.getUser(auth.getName()));
	    		model.addAttribute("sizeOrders", fs.sizeOrderUser(auth.getName()));
		    	model.addAttribute("countItems", fs.cartSize() );
	    		return "paginaUsuario";
	    	}
	    }    
	    
	    /* register Query previous error log */
	    @RequestMapping("/registro")
	    public String loginerror() {
	    	return "paginaRegistro";
	    }
	    
	    /* logout Query */
	    @RequestMapping("/logout")
	    public String logOut(HttpServletRequest http,Authentication auth){
	    	try {
	    		http.logout();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	Model model = null;
	    	return index(model,auth);
	    }
	
	    /* admin Query */
	    @RequestMapping("/admin")
	    public String admin(Model model) {
	    	long numProducts = fs.getProductsNumber();
    		model.addAttribute("numProd",numProducts);
	    	model.addAttribute("numUsers",fs.getUserNumber());
	    	model.addAttribute("numPed", fs.getPedidoNumber());
	    	model.addAttribute("numCom", fs.getCommentsNumber());
	    	model.addAttribute("numPunt",numProducts);
	    	model.addAttribute("numCat", fs.getCategoryNumber());
	    	return "adminIndex";
	    }
	    
	    /* addComment Query */
	    @RequestMapping("/addComment")
	    public String addComment (Model model, @RequestParam(value = "comment")String comment, @RequestParam(value = "id")String id, Authentication http, @RequestParam(value = "rating")String rating) {
	    	fs.addCommentIntoProduct(http.getName(), id, comment, rating);
	    	/*IN WORK...*****************************/
	    	float total = productoRepository.findProductoById(Long.parseLong(id)).getTheBest() + productoRepository.findProductoById(Long.parseLong(id)).getMustImprove() + productoRepository.findProductoById(Long.parseLong(id)).getBad();
	    	float best = productoRepository.findProductoById(Long.parseLong(id)).getTheBest() / total * 100;
	    	float improve = productoRepository.findProductoById(Long.parseLong(id)).getMustImprove() / total * 100;
	    	float bad = productoRepository.findProductoById(Long.parseLong(id)).getBad() / total * 100;
	    	if(http != null){
	    		model.addAttribute("usuario",fs.getUser(http.getName()));
	    	}
	    	model.addAttribute("countItems", fs.cartSize() );
	    	model.addAttribute("producto",fs.getSpecificProduct(Long.parseLong(id)));
	    	DecimalFormat decimals = new DecimalFormat("0");
	    	model.addAttribute("best", decimals.format(best));
	    	model.addAttribute("improve", decimals.format(improve));
	    	model.addAttribute("worst", decimals.format(bad));
	    	
	    	return "paginaDetalleProducto";
	    }
	    
    
    /*************************************************/
	/* Ajax Queries */
	/*************************************************/
    
	  
	    @RequestMapping(value = "/ListadoProductoAjax/")
	    public @ResponseBody Page<Producto> listadoProductosAjax(Model model, Pageable page, @RequestParam(value = "result") String result){
	    	try {
	    	    Thread.sleep(800);
	    	} catch(InterruptedException ex) {
	    	    Thread.currentThread().interrupt();
	    	}
	    	
	    	if ( categoriaRepository.findByName(result)!=null ){
	    		return fs.getProductsByCategory(result, page);
	    	}else if ( result.equals("index") ){
	    		return fs.getAllProducts(page);
	    	}else{
	    		return fs.getProductsByName(result,page);
	    	}
	    }
	    
	    
	    
	    @RequestMapping("/shoppingCartAjax")
	    public @ResponseBody List<Producto> shoppingCartAjax() {
	    	try {
	    	    Thread.sleep(800);
	    	} catch(InterruptedException ex) {
	    	    Thread.currentThread().interrupt();
	    	}
	    	return fs.getShoppingCart();
	    }
	    
	    @RequestMapping(value = "/ListadoProductoAjaxCarrito")
	    public @ResponseBody Integer addCardQuery(Model model, @RequestParam(value = "info") String info, @RequestParam(value = "name")String name, @RequestParam(value = "price")String price){
	    	fs.addCartSession(info, name, price);
	    	model.addAttribute("countItems", fs.cartSize());
	    	return fs.cartSize();
	    }
	    
	    /*It's a user logged? for shopping cart (SC)*/
	    @RequestMapping("/buy2")
	    public @ResponseBody String isLoggedSC(Authentication http) {
	    	return fs.isLoggedANDThereAreProducts(http);
	    }
	    
	    @RequestMapping("/deleteCart")
	    public @ResponseBody String deleteCart() {
    		fs.clearCart();
    		return "200OK";
	    }
	    
	    /*Get the comments about the product --> id...*/
	    @RequestMapping("/loadComments")
	    public @ResponseBody List<Comment> loadComments(Model model, @RequestParam String idProduct) {
	    	return fs.getComments(idProduct);
	    }
	    
}