package es.daw.dirando.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.daw.dirando.service.OrderServices;
import es.daw.dirando.service.UserServices;
import es.daw.dirando.model.Producto;
import es.daw.dirando.repository.ProductoRepository;
import es.daw.dirando.security.LoginControllerRest;

@RestController
@RequestMapping("/rest")
public class WebRestControllerOrder {
	
	private static final Logger log = LoggerFactory.getLogger(WebRestControllerOrder.class);
	
	@Autowired
	private OrderServices os;
	
	@Autowired
	private ProductoRepository pr;
	
	@Autowired
	private UserServices us;
	
		/*Pay Process Methods*/
			/*Makes effective the order*/
			@RequestMapping(value = "/pay", method = RequestMethod.PUT)
			public ResponseEntity<String> payProcess2(Authentication http) {
				switch (us.isLoggedANDThereAreProducts(http)){
					case "0":
						os.makeOrderfromSessionCart(http);
						os.clearCart();
						us.saveUser(http.getName());
						return new ResponseEntity<>("Finished payment", HttpStatus.OK);
					case "2":
						return new ResponseEntity<>("The cart is empty!",HttpStatus.PARTIAL_CONTENT);
					default:
						return new ResponseEntity<>("Logged required!",HttpStatus.UNAUTHORIZED);
				}
				
			}
		/*End Pay Process Methods*/
		
		/*Cart Methods*/
			@RequestMapping(value = "/cartSize", method = RequestMethod.GET)
			public ResponseEntity<Integer> getCartSize() {
				return new ResponseEntity<>(os.cartSize(),HttpStatus.OK);
			}
			
			@RequestMapping(value = "/clearCart", method = RequestMethod.DELETE)
			public ResponseEntity<String> deleteCart() {
				os.clearCart();
				log.info(String.valueOf(os.cartSize()));
				return new ResponseEntity<>("The Cart is empty!", HttpStatus.OK);
			}		
			
			
			//Delete select product
			@RequestMapping(value = "/delProductCart", method = RequestMethod.DELETE)
			public ResponseEntity<String> deleteProductCart(@RequestParam int id) {
				os.clearCart();
				return new ResponseEntity<>("The Cart is empty!", HttpStatus.OK);
			}		
			
			@RequestMapping(value = "/cart", method = RequestMethod.GET)
			public ResponseEntity< List<Producto> > getCart() {
				List<Producto> cart = os.getShoppingCart();
				if (cart.isEmpty()){
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}else{
					return new ResponseEntity<>(os.getShoppingCart(),HttpStatus.OK);
				}
				
			}
			@RequestMapping(value = "/addCart/{id}", method = RequestMethod.PUT)
			public ResponseEntity<Producto> addToCart(@PathVariable long id,@RequestBody Producto product) {
				Producto producto = pr.findProductoById(id);
				log.info(product.getNombre()+"--------------------"+os.cartSize());
				
				os.addCartSession(producto.getId(),producto.getNombre(),producto.getPrecio());
				return new ResponseEntity<>(product, HttpStatus.OK);
				
			}
		/*End Cart Methods*/
}