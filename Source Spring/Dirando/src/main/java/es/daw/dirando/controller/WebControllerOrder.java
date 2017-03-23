package es.daw.dirando.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.daw.dirando.service.OrderServices;
import es.daw.dirando.service.UserServices;
import es.daw.dirando.model.Producto;


@Controller
public class WebControllerOrder {
		
	@Autowired
	private OrderServices os;
		
	@Autowired
	private UserServices us;
	
	/*************************************************/
	/* Simple Queries */
	/*************************************************/
		    
	    /*payMethods query*/
	    @RequestMapping("/payMethods")
	    public String payMethods(Model model, Authentication http) {
	    	model.addAttribute("countItems", os.cartSize() );
	    	if(http != null){
	    		model.addAttribute("usuario",us.getUser(http.getName()));
	    	}
	    	return "paginaPaymentsMethods";
	    }
	    
	    /*Buy process*/
	    @RequestMapping("/buy")
	    public String buyButton(Model model,Authentication http) {
	    	if(http != null && os.cartSize()>0){
	    		/*Add the current Order into the User logged*/
	    		os.makeOrderfromSessionCart(http);
	    		os.clearCart();
	    		us.saveUser(http.getName());
		    	model.addAttribute("usuario",us.getUser(http.getName()));
		    	model.addAttribute("countItems", os.cartSize() );
	    		return "paginaPago";
	    	}else{
	    		return "/";
	    	}
	    }
	    	    	    	    
	    /* shoppingCart Query */
	    @RequestMapping("/shoppingCart")
	    public String shoppingCart(Model model,Authentication http) {
	    	model.addAttribute("countItems", os.cartSize() );
	    	model.addAttribute("subtotal",os.calculateTotalPrice());
	    	if(http != null){	    		
	    		model.addAttribute("usuario",us.getUser(http.getName()));
	    	}
	    	return "paginaCarrito";
	    }
	     
    
    /*************************************************/
	/* Ajax Queries */
	/*************************************************/
    	    
	    @RequestMapping("/shoppingCartAjax")
	    public @ResponseBody List<Producto> shoppingCartAjax() {
	    	try {
	    	    Thread.sleep(800);
	    	} catch(InterruptedException ex) {
	    	    Thread.currentThread().interrupt();
	    	}
	    	return os.getShoppingCart();
	    }
	    
	    @RequestMapping(value = "/ListadoProductoAjaxCarrito")
	    public @ResponseBody Integer addCardQuery(Model model, @RequestParam(value = "info") String info, @RequestParam(value = "name")String name, @RequestParam(value = "price")String price){
	    	os.addCartSession(info, name, price);
	    	model.addAttribute("countItems", os.cartSize());
	    	return os.cartSize();
	    }
	    
	    /*It's a user logged? for shopping cart (SC)*/
	    @RequestMapping("/buy2")
	    public @ResponseBody String isLoggedSC(Authentication http) {
	    	return us.isLoggedANDThereAreProducts(http);
	    }
	    
	    @RequestMapping("/deleteCart")
	    public @ResponseBody String deleteCart() {
    		os.clearCart();
    		return "200OK";
	    }
	    	    
}