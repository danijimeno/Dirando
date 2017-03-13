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
	private Producto producto;
	
	/*************************************************/
	/* Simple Queries */
	/*************************************************/
	
		/* index Query */
	    @RequestMapping("/")
	    public String index(Model model,Authentication http) {
	    	model.addAttribute("productos",productoRepository.findAll());
	    	model.addAttribute("carrusel",publicidadRepository.findAll());
	    	int countItems = pedido.getPedidos().size();
	    	model.addAttribute("countItems", countItems );
	    	if(http != null){
	    		model.addAttribute("usuario",usuarioRepository.findUserByName(http.getName()));
	    	}
	        return "index";
	    }
	    
	    /*payMethods query*/
	    @RequestMapping("/payMethods")
	    public String payMethods(Model model,Authentication http) {
	    	int countItems = pedido.getPedidos().size();
	    	model.addAttribute("countItems", countItems );
	    	if(http != null){
	    		model.addAttribute("usuario",usuarioRepository.findUserByName(http.getName()));
	    		
	    	}
	    	return "paginaPaymentsMethods";
	    }
	    
	    /*Buy process*/
	    @RequestMapping("/buy")
	    public String buyButton(Model model,Authentication http) {
	    	int countItems = pedido.getPedidos().size();
	    	if(http != null && countItems>0){
	    		/*Add the current Pedido into the User logged*/
		    	usuarioRepository.findUserByName(http.getName()).setPedidos(pedido);
		    	/*Update the new data user*/
		    	countItems = pedido.getPedidos().size();
		    	pedido.getPedidos().clear();
		    	usuarioRepository.saveAndFlush(usuarioRepository.findUserByName(http.getName()));
		    	model.addAttribute("usuario",usuarioRepository.findUserByName(http.getName()));
		    	model.addAttribute("countItems", countItems );
	    		return "paginaPago";
	    	}else{
	    		return "/";
	    	}
	    }
	    
	    /* register Query */
	    @RequestMapping("/register")
	    public String register(Model model) {
	    	int countItems = pedido.getPedidos().size();
	    	model.addAttribute("countItems", countItems );
	    	return "paginaRegistro";
	    }
	    
	    /* add user Query */
	    @RequestMapping("/addUser")
	    public String addUser(Model model, @RequestParam(value = "phone") String phone, @RequestParam(value = "name") String name, @RequestParam(value = "pass") String pass, @RequestParam(value = "fullName") String fullName, @RequestParam(value = "address") String address, @RequestParam(value = "email") String email) {
	    	usuarioRepository.save(new Usuario (name,fullName,email,"img/usuario1.jpg",pass,phone,address,"ROLE_USER"));
	    	return "/";
	    }
	    
	    /* shoppingCart Query */
	    @RequestMapping("/shoppingCart")
	    public String shoppingCart(Model model,Authentication http) {
	    	float subtotal=0;
	    	for (int i = 0; i< pedido.getPedidos().size(); i++){
	    		 subtotal+= pedido.getPedidos().get(i).getPrecio();
	    	}
	    	
	    	int countItems = pedido.getPedidos().size();
	    	model.addAttribute("countItems", countItems );
	    	model.addAttribute("subtotal",subtotal);
	    	if(http != null){	    		
	    		model.addAttribute("usuario",usuarioRepository.findUserByName(http.getName()));
	    	}
	    	return "paginaCarrito";
	    }
	    
	    
	    /* product Query */
	    @RequestMapping("/Producto/{id}")
	    public String productoInfo(Model model,@PathVariable long id, Authentication http){
	    	int countItems = pedido.getPedidos().size();
	    	model.addAttribute("countItems", countItems );
	    	model.addAttribute("producto",productoRepository.findOne(id));
	    	if(http != null){
	    		model.addAttribute("usuario",usuarioRepository.findUserByName(http.getName()));
	    	}
	    	
	    	float total = productoRepository.findProductoById(id).getTheBest() + productoRepository.findProductoById(id).getMustImprove() + productoRepository.findProductoById(id).getBad();
	    	float best = productoRepository.findProductoById(id).getTheBest() / total * 100;
	    	float improve = productoRepository.findProductoById(id).getMustImprove() / total * 100;
	    	float bad = productoRepository.findProductoById(id).getBad() / total * 100;
	    	
	    	model.addAttribute("countItems", countItems );
	    	model.addAttribute("producto",productoRepository.findOne(id));
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
	    	model.addAttribute("productos",productoRepository.findAll());
	    	int countItems = pedido.getPedidos().size();
	    	model.addAttribute("countItems", countItems );
	    	if(http != null){
	    		model.addAttribute("usuario",usuarioRepository.findUserByName(http.getName()));
	    	}
	    	return "paginaListadoProductos";
	    }
	    
	    /* product category List Query */
	    @RequestMapping("/ProductoCategoria")
	    public @ResponseBody String listadoProductoCategoria (Model model, Authentication http, @RequestParam(value = "cat")String cat){
	    	model.addAttribute("resultSearch",cat);
	    	model.addAttribute("productos",productoRepository.findAll());
	    	int countItems = pedido.getPedidos().size();
	    	model.addAttribute("countItems", countItems );
	    	if(http != null){
	    		model.addAttribute("usuario",usuarioRepository.findUserByName(http.getName()));
	    	}
	    	return "paginaListadoProductos";
	    }
	    
	    /* user Query */
	    @RequestMapping("/usuario")
	    public String usuarioreg(Model model, HttpServletRequest request, Authentication auth) {
	    	if(request.isUserInRole("ADMIN")){
	    		model.addAttribute("admin", request.isUserInRole("ADMIN"));
	    		model.addAttribute("numProd",productoRepository.count());
		    	model.addAttribute("numUsers",usuarioRepository.count());
		    	model.addAttribute("numPed", pedidoRepository.count());
		    	model.addAttribute("numCom", comentarioRepository.count());
		    	model.addAttribute("numPunt",productoRepository.count());
		    	model.addAttribute("numCat", categoriaRepository.count());
	    		return "adminIndex";
	    	}else{
	    		int sizeOrders = 0;
	    		model.addAttribute("usuario", request.getAttribute("USER"));
	    		model.addAttribute("usuario",usuarioRepository.findUserByName(auth.getName()));
	    		if (usuarioRepository.findUserByName(auth.getName()).getPedidos().size()>0){
	    			sizeOrders=usuarioRepository.findUserByName(auth.getName()).getPedidos().size();
	    		}
	    		model.addAttribute("sizeOrders", sizeOrders);
	    		int countItems = pedido.getPedidos().size();
		    	model.addAttribute("countItems", countItems );
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
	    	model.addAttribute("numProd",productoRepository.count());
	    	model.addAttribute("numUsers",usuarioRepository.count());
	    	model.addAttribute("numPed", pedidoRepository.count());
	    	model.addAttribute("numCom", comentarioRepository.count());
	    	model.addAttribute("numPunt",productoRepository.count());
	    	model.addAttribute("numCat", categoriaRepository.count());
	    	return "adminIndex";
	    }
	    
	    /* addComment Query */
	    @RequestMapping("/addComment")
	    public String addComment (Model model, @RequestParam(value = "comment")String comment, @RequestParam(value = "id")String id, Authentication http, @RequestParam(value = "rating")String rating) {
	    	if (Integer.parseInt(rating)==3){
	    		Comment co = new Comment (usuarioRepository.findUserByName(http.getName()).getName(), comment, "The Best!");
	    		productoRepository.findProductoById(Long.parseLong(id)).setComments(co);
	    		productoRepository.findProductoById(Long.parseLong(id)).incrementTheBest();
	    	}else if(Integer.parseInt(rating)==2){
	    		Comment co = new Comment (usuarioRepository.findUserByName(http.getName()).getName(), comment, "Must Improve!");
	    		productoRepository.findProductoById(Long.parseLong(id)).setComments(co);
	    		productoRepository.findProductoById(Long.parseLong(id)).incrementMustImprove();
	    	}else if(Integer.parseInt(rating)==1){
	    		Comment co = new Comment (usuarioRepository.findUserByName(http.getName()).getName(), comment, "Bad!");
	    		productoRepository.findProductoById(Long.parseLong(id)).setComments(co);
	    		productoRepository.findProductoById(Long.parseLong(id)).incrementBad();
	    	}else{
	    		Comment co = new Comment (usuarioRepository.findUserByName(http.getName()).getName(), comment, null);
	    		productoRepository.findProductoById(Long.parseLong(id)).setComments(co);
	    	}

	    	/*Update the new data user*/
	    	productoRepository.saveAndFlush(productoRepository.findProductoById(Long.parseLong(id)));
	    	
	    	int countItems = pedido.getPedidos().size();
	    	float total = productoRepository.findProductoById(Long.parseLong(id)).getTheBest() + productoRepository.findProductoById(Long.parseLong(id)).getMustImprove() + productoRepository.findProductoById(Long.parseLong(id)).getBad();
	    	float best = productoRepository.findProductoById(Long.parseLong(id)).getTheBest() / total * 100;
	    	float improve = productoRepository.findProductoById(Long.parseLong(id)).getMustImprove() / total * 100;
	    	float bad = productoRepository.findProductoById(Long.parseLong(id)).getBad() / total * 100;
	    	if(http != null){
	    		model.addAttribute("usuario",usuarioRepository.findUserByName(http.getName()));
	    	}
	    	model.addAttribute("countItems", countItems );
	    	model.addAttribute("producto",productoRepository.findOne(Long.parseLong(id)));
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
	    		return productoRepository.findByCategoria(result, page);
	    	}else if ( result.equals("index") ){
	    		return productoRepository.findAll(page);
	    	}else{
	    		return productoRepository.findByNombre(result, page);
	    	}
	    }
	    
	    
	    
	    @RequestMapping("/shoppingCartAjax")
	    public @ResponseBody List<Producto> shoppingCartAjax() {
	    	try {
	    	    Thread.sleep(800);
	    	} catch(InterruptedException ex) {
	    	    Thread.currentThread().interrupt();
	    	}
	    	return pedido.getPedidos();
	    }
	    
	    @RequestMapping(value = "/ListadoProductoAjaxCarrito")
	    public @ResponseBody Integer addCardQuery(Model model, @RequestParam(value = "info") String info, @RequestParam(value = "name")String name, @RequestParam(value = "price")String price){
	    	producto = new Producto ( Long.valueOf(info),name, Float.valueOf(price) );
	    	pedido.setPedido(producto);
	    	int countItems = pedido.getPedidos().size();
	    	model.addAttribute("countItems", countItems);
	    	return pedido.getPedidos().size();
	    }
	    
	    /*It's a user logged? for shopping cart (SC)*/
	    @RequestMapping("/buy2")
	    public @ResponseBody String isLoggedSC(Authentication http) {
	    	if(http != null && pedido.getPedidos().size()>0){
	    		return "0";
	    	}else if(pedido.getPedidos().size()<1){
	    		return "2";
	    	}else{
	    		return "1";
	    	}
	    }
	    
	    @RequestMapping("/deleteCart")
	    public @ResponseBody String deleteCart() {
    		pedido.getPedidos().clear();
    		return "200OK";
	    }
	    
	    /*Get the comments about the product --> id...*/
	    @RequestMapping("/loadComments")
	    public @ResponseBody List<Comment> loadComments(Model model, @RequestParam String idProduct) {
    		return productoRepository.findProductoById(Long.parseLong(idProduct)).getComments();
	    }
	    
}