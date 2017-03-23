package es.daw.dirando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.daw.dirando.service.OrderServices;
import es.daw.dirando.service.UserServices;

@Controller
public class WebControllerCategory {
	
	@Autowired
	private OrderServices os;
		
	@Autowired
	private UserServices us;
	
	/*************************************************/
	/* Simple Queries */
	/*************************************************/
	
	/* product category List Query */
	    @RequestMapping("/ProductoCategoria")
	    public @ResponseBody String listadoProductoCategoria (Model model, Authentication http, @RequestParam(value = "cat")String cat){
	    	model.addAttribute("resultSearch",cat);
	    	model.addAttribute("countItems", os.cartSize() );
	    	if(http != null){
	    		model.addAttribute("usuario",us.getUser(http.getName()));
	    	}
	    	return "paginaListadoProductos";
	    }
  }