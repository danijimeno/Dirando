package es.daw.dirando.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.daw.dirando.repository.ProductoRepository;
import es.daw.dirando.repository.PublicidadRepository;
import es.daw.dirando.repository.UsuarioRepository;
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
	    
	    /*Buy process*/
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
	    
	    /*****************WORKING...WITH SOME VARS But its succesful ******************/
	    /* add user Query */
	    @RequestMapping("/addUser")
	    public String addUser(Model model, @RequestParam(value = "name") String name, @RequestParam(value = "pass") String pass, @RequestParam(value = "fullName") String fullName, @RequestParam(value = "address") String address, @RequestParam(value = "email") String email) {
	    	Usuario usuario = new Usuario (name,fullName,email,"",pass,address,"ROLE_USER");
	    	usuarioRepository.save(usuario);
	    	return "/";
	    }
	    
	    /* shoppingCart Query */
	    @RequestMapping("/shoppingCart")
	    public String shoppingCart(Model model,Authentication http) {
	    	int countItems = pedido.getPedidos().size();
	    	model.addAttribute("countItems", countItems );
	    	if(http != null){	    		
	    		model.addAttribute("usuario",usuarioRepository.findUserByName(http.getName()));
	    	}
	    	return "paginaCarrito";
	    }
	    
	    /* login Query */
	    @RequestMapping("/login")
	    public String login() {
	    	return "login";
	    }
	    
	    /* product Query */
	    @RequestMapping("/Producto/{id}")
	    public String productoInfo(Model model,@PathVariable long id){
	    	model.addAttribute("producto",productoRepository.findOne(id));
	    	return "paginaDetalleProducto";
	    }
	    
	    /* product List Query */
	    @RequestMapping("/ListadoProducto")
	    public String listadoProductos(Model model,Authentication http){
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
	    public String usuarioreg(Model model, HttpServletRequest request,Authentication auth) {
	    	System.out.println(request.isUserInRole("ADMIN")+"---------------------here");
	    	if(request.isUserInRole("ADMIN")){
	    		model.addAttribute("admin", request.isUserInRole("ADMIN"));
	    		return "adminIndex";
	    	}else{
	    		model.addAttribute("usuario", request.getAttribute("USER"));
	    		model.addAttribute("usuario",usuarioRepository.findUserByName(auth.getName()));
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
	    public String admin() {
	    	return "paginaUsuario";
	    }
    
    /*************************************************/
	/* Ajax Queries */
	/*************************************************/
    
	    /*@Uso de @ResponseBody para evitar el fallo "Circular view path (...)" Excepción "javax.servlet.ServletException"*/
	    @RequestMapping(value = "/ListadoProductoAjax/")
	    public @ResponseBody Page<Producto> listadoProductosAjax(Model model, Pageable page){
	    	try {
	    	    Thread.sleep(800);
	    	} catch(InterruptedException ex) {
	    	    Thread.currentThread().interrupt();
	    	}
	    	return productoRepository.findAll(page);
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
	    public @ResponseBody Integer addCardQuery(Model model, @RequestParam String info){
	    	producto =new Producto (Long.valueOf(info));
	    	pedido.setPedido(producto);
	    	int countItems = pedido.getPedidos().size();
	    	model.addAttribute("countItems", countItems );
	    	return pedido.getPedidos().size();
	    }
	    
	    /*It's a user logged?*/
	    @RequestMapping("/buy2")
	    public @ResponseBody String isLogged(Authentication http) {
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
}