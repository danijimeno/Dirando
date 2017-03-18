package es.daw.dirando.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.daw.dirando.model.Producto;
import es.daw.dirando.model.Publicidad;
import es.daw.dirando.model.Usuario;
import es.daw.dirando.service.ToolsServices;



@RestController
@RequestMapping("/rest")
public class WebRestController {
	
	@Autowired
	private ToolsServices fs;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Producto> getIndexItems() {
		//How can i return just 3 items??
		List<Producto> product = fs.findAllProducts();
        return product;
	}
	
	@RequestMapping(value = "/isLogged", method = RequestMethod.GET)
	public Usuario isLogged(Authentication http) {
		Usuario user = new Usuario();
		if(http != null){
    		user =fs.getUser(http.getName());
    	}
        return user;
	}
	
	@RequestMapping(value = "/carrusel", method = RequestMethod.GET)
	public Iterable<Publicidad> getCarrusel() {
		Iterable<Publicidad> publicity = fs.findAllPublicity();
		return publicity;
	}
	
	@RequestMapping(value = "/getCategory", method = RequestMethod.GET)
	public Page<Producto> getItemsByCategory(String category, Pageable page) {
		return fs.getProductsByCategory(category, page);
	}
	
	@RequestMapping(value = "/getItems", method = RequestMethod.GET)
	public Page<Producto> getItemsBySearch(String result, Pageable page) {
		if ( fs.getSpecificCategory(result)!=null ){
    		return fs.getProductsByCategory(result, page);
    	}else{
    		return fs.getProductsByName(result,page);
    	}
	}
	
	@RequestMapping(value = "/getCartSize", method = RequestMethod.GET)
	public int getCartSize() {
		return fs.cartSize();
	}
	
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public Usuario getUser(String nameId) {
		return fs.getUser(nameId);
	}
	
	@RequestMapping(value = "/payProcess", method = RequestMethod.GET)
	public String payProcess(Authentication http) {
		return fs.isLoggedANDThereAreProducts(http);
	}
	@RequestMapping(value = "/payProcess", method = RequestMethod.PUT)
	public void payProcess2(Authentication http) {
		fs.makeOrderfromSessionCart(http);
		fs.clearCart();
		fs.saveUser(http.getName());
	}
	
	@RequestMapping(value = "/deleteCart", method = RequestMethod.DELETE)
	public void deleteCart() {
		fs.clearCart();
	}
	
	@RequestMapping(value = "/productDetail", method = RequestMethod.GET)
	public Producto productDetail(String id) {
		return fs.getSpecificProduct(Long.parseLong(id));
	}
	@RequestMapping(value = "/getRating", method = RequestMethod.GET)
	public float[] getRating(String id) {
		return fs.dataRating(Long.parseLong(id));
	}
		
	@RequestMapping(value = "/addComment", method = RequestMethod.PUT)
	public void addComment(Authentication http, String id, String comment, String rating) {
		fs.addCommentIntoProduct(http.getName(), id, comment, rating);
	}
	
	@RequestMapping(value = "/addToCart", method = RequestMethod.PUT)
	public void addToCart(String info, String name, String price) {
		fs.addCartSession(info, name, price);
	}
	
	@RequestMapping(value = "/addNewUser", method = RequestMethod.POST)
	public void addNewUser(String phone, String name, String pass, String fullName, String address, String email) {
		fs.addUser(phone, name, pass, fullName, address, email);
	}
	
	@RequestMapping(value = "/logOut", method = RequestMethod.PUT)
	public String logOut(@RequestBody String item) {
		return "in work...";
	}
	
	@RequestMapping(value = "/logIn", method = RequestMethod.PUT)
	public String logIn(@RequestBody String item) {
		return "in work...";
	}
	
	
	
	
	
	
	
	
	
	
   
 
}