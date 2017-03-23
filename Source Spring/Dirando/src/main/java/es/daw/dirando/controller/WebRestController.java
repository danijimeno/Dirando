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

import es.daw.dirando.service.CategoryServices;
import es.daw.dirando.service.CommentServices;
import es.daw.dirando.service.OrderServices;
import es.daw.dirando.service.ProductServices;
import es.daw.dirando.service.PublicServices;
import es.daw.dirando.service.UserServices;
import es.daw.dirando.model.Producto;
import es.daw.dirando.model.Publicidad;
import es.daw.dirando.model.Usuario;


@RestController
@RequestMapping("/rest")
public class WebRestController {
	
	@Autowired
	private CategoryServices cas;
	
	@Autowired
	private CommentServices cos;
	
	@Autowired
	private OrderServices os;
	
	@Autowired
	private ProductServices prs;
	
	@Autowired
	private PublicServices pus;
	
	@Autowired
	private UserServices us;
	
	/*START*************************************/
	/*REST METHODS FRONTEND*/
	/**************************************/
	
		@RequestMapping(value = "/", method = RequestMethod.GET)
		public Page<Producto> getIndexItems(Pageable page) {
	        return prs.get3Items(page);
		}
		
		@RequestMapping(value = "/updateAccount", method = RequestMethod.PUT)
		public void updateAccount(Authentication http, String phone, String pass, String fullName, String address, String email) {
			if(http != null){
				us.updateUser(http.getName(), phone, pass, fullName, address, email);
			}
		}
		
		@RequestMapping(value = "/isLogged", method = RequestMethod.GET)
		public Usuario isLogged(Authentication http) {
			Usuario user = new Usuario();
			if(http != null){
	    		user =us.getUser(http.getName());
	    	}
	        return user;
		}
		
		@RequestMapping(value = "/carrusel", method = RequestMethod.GET)
		public Iterable<Publicidad> getCarrusel() {
			Iterable<Publicidad> publicity = pus.findAllPublicity();
			return publicity;
		}
		
		@RequestMapping(value = "/getCategory", method = RequestMethod.GET)
		public Page<Producto> getItemsByCategory(String category, Pageable page) {
			return prs.getProductsByCategory(category, page);
		}
		
		@RequestMapping(value = "/getItems", method = RequestMethod.GET)
		public Page<Producto> getItemsBySearch(String result, Pageable page) {
			if ( cas.getSpecificCategory(result)!=null ){
	    		return prs.getProductsByCategory(result, page);
	    	}else{
	    		
	    		return prs.getProductsByName(result,page);
	    	}
		}
		
		@RequestMapping(value = "/getCartSize", method = RequestMethod.GET)
		public int getCartSize() {
			return os.cartSize();
		}
		
		@RequestMapping(value = "/getUser", method = RequestMethod.GET)
		public Usuario getUser(Authentication http) {
			if(http != null){
				return us.getUser(http.getName());
			}
			return new Usuario();
		}
		
		@RequestMapping(value = "/payProcess", method = RequestMethod.GET)
		public String payProcess(Authentication http) {
			return us.isLoggedANDThereAreProducts(http);
		}
		@RequestMapping(value = "/payProcess", method = RequestMethod.PUT)
		public void payProcess2(Authentication http) {
			if(http != null){
				os.makeOrderfromSessionCart(http);
				os.clearCart();
				us.saveUser(http.getName());
			}
		}
		
		@RequestMapping(value = "/deleteCart", method = RequestMethod.DELETE)
		public void deleteCart() {
			os.clearCart();
		}
		
		@RequestMapping(value = "/productDetail", method = RequestMethod.GET)
		public Producto productDetail(String id) {
			return prs.getSpecificProduct(Long.parseLong(id));
		}
		@RequestMapping(value = "/getRating", method = RequestMethod.GET)
		public float[] getRating(String id) {
			return prs.dataRating(Long.parseLong(id));
		}
			
		@RequestMapping(value = "/addComment", method = RequestMethod.PUT)
		public void addComment(Authentication http, String id, String comment, String rating) {
			if(http != null){
				cos.addCommentIntoProduct(http.getName(), id, comment, rating);
			}
		}
		
		@RequestMapping(value = "/getCart", method = RequestMethod.GET)
		public List<Producto> getCart(Authentication http, String id, String comment, String ratin) {
			return os.getShoppingCart();
		}
		
		@RequestMapping(value = "/addToCart", method = RequestMethod.PUT)
		public void addToCart(String info, String name, String price) {
			os.addCartSession(info, name, price);
		}
		
		@RequestMapping(value = "/addNewUser", method = RequestMethod.POST)
		public void addNewUser(String phone, String name, String pass, String fullName, String address, String email) {
			us.addUser(phone, name, pass, fullName, address, email);
		}
		
		/********************************/
		@RequestMapping(value = "/logOut", method = RequestMethod.PUT)
		public String logOut(@RequestBody String data) {
			return "in work...";
		}
		@RequestMapping(value = "/logIn", method = RequestMethod.PUT)
		public String logIn(@RequestBody String data) {
			return "in work...";
		}
		/******************************/
		
	/*FINISH FRONTEND METHODS*************************************/
		
}