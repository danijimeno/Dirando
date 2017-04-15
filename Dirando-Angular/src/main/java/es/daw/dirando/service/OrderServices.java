package es.daw.dirando.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import es.daw.dirando.model.Pedido;
import es.daw.dirando.model.Producto;

import es.daw.dirando.repository.PedidoRepository;
import es.daw.dirando.repository.UsuarioRepository;

@Service
public class OrderServices {

	@Autowired
	private Pedido pedido; //For the cart session...
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
		/****************************/
		/*Tools for the session cart*/
		/****************************/
			//Add in a session products in cart
			public void addCartSession(long info, String name, float price){
				pedido.setPedido(new Producto ( info, name, price ));
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
		
		//Add the cart into the user account
		public void makeOrderfromSessionCart (Authentication http){
			usuarioRepository.findUserByName(http.getName()).setPedidos(pedido);
		}
		
		/*Count items numbers about order*/
		public long getPedidoNumber(){
			return pedidoRepository.count();	
		}
		
		//Return the total Cart price 
		public float calculateTotalPrice (){
			float subtotal=0;
			for (int i = 0; i< cartSize(); i++){
	    		 subtotal+= pedido.getPedidos().get(i).getPrecio();
	    	}
			return subtotal;
		}	
		
		public Iterable<Pedido> getAllOrders(){
			return pedidoRepository.findAll();
		}
}