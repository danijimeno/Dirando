package es.daw.dirando.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
		/*Pay Process Methods*/
			/*Check if you are logged in and you have items in the cart to place the order*/
			@RequestMapping(value = "/pay", method = RequestMethod.GET)
			public ResponseEntity<String> payProcess(Authentication http) {
				switch (us.isLoggedANDThereAreProducts(http)){
				case "0":
					return new ResponseEntity<>(HttpStatus.OK );
				case "2":
					return new ResponseEntity<>("The cart is empty!",HttpStatus.PARTIAL_CONTENT);
				default:
					return new ResponseEntity<>("Logged required!",HttpStatus.UNAUTHORIZED);
				}
			}
			/*Makes effective the order*/
			@RequestMapping(value = "/pay", method = RequestMethod.PUT)
			public ResponseEntity<String> payProcess2(Authentication http) {
				if(http != null){
					os.makeOrderfromSessionCart(http);
					os.clearCart();
					us.saveUser(http.getName());
					return new ResponseEntity<>(HttpStatus.OK);
				}else{
					return new ResponseEntity<>(HttpStatus.LOCKED);
				}
			}
		/*End Pay Process Methods*/
		
		/*Cart Methods*/
			@RequestMapping(value = "/cartSize", method = RequestMethod.GET)
			public ResponseEntity<Integer> getCartSize() {
				return new ResponseEntity<>(os.cartSize(),HttpStatus.OK);
			}
			@RequestMapping(value = "/cart", method = RequestMethod.DELETE)
			public ResponseEntity<String> deleteCart() {
				os.clearCart();
				return new ResponseEntity<>(HttpStatus.OK);
			}		
			@RequestMapping(value = "/cart", method = RequestMethod.GET)
			public ResponseEntity< List<Producto> > getCart(Authentication http) {
				List<Producto> cart = os.getShoppingCart();
				if (cart.isEmpty()){
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}else{
					return new ResponseEntity<>(os.getShoppingCart(),HttpStatus.OK);
				}
				
			}
			@RequestMapping(value = "/cart", method = RequestMethod.PUT)
			public ResponseEntity<String> addToCart(String info, String name, String price) {
				os.addCartSession(info, name, price);
				return new ResponseEntity<>(HttpStatus.OK);
				
			}
		/*End Cart Methods*/
}