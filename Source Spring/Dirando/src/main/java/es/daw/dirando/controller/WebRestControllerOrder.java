package es.daw.dirando.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.daw.dirando.service.OrderServices;
import es.daw.dirando.service.UserServices;
import es.daw.dirando.model.Producto;

@RestController
@RequestMapping("/rest")
public class WebRestControllerOrder {
	
	@Autowired
	private OrderServices os;
	
	@Autowired
	private UserServices us;
	
	/*START*************************************/
	/*REST METHODS FRONTEND*/
	/**************************************/
				
		@RequestMapping(value = "/getCartSize", method = RequestMethod.GET)
		public int getCartSize() {
			return os.cartSize();
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
				
		@RequestMapping(value = "/getCart", method = RequestMethod.GET)
		public List<Producto> getCart(Authentication http, String id, String comment, String ratin) {
			return os.getShoppingCart();
		}
		
		@RequestMapping(value = "/addToCart", method = RequestMethod.PUT)
		public void addToCart(String info, String name, String price) {
			os.addCartSession(info, name, price);
		}

		
	/*FINISH FRONTEND METHODS*************************************/
		
}