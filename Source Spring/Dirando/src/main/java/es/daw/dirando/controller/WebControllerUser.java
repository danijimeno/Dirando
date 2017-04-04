package es.daw.dirando.controller;


import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.daw.dirando.service.CategoryServices;
import es.daw.dirando.service.CommentServices;
import es.daw.dirando.service.OrderServices;
import es.daw.dirando.service.ProductServices;
import es.daw.dirando.service.UserServices;

@Controller
public class WebControllerUser {
	
	@Autowired
	private CategoryServices cas;
	
	@Autowired
	private CommentServices cos;
	
	@Autowired
	private OrderServices os;
	
	@Autowired
	private ProductServices prs;
	
	@Autowired
	private UserServices us;
	
	/*************************************************/
	/* Simple Queries */
	/*************************************************/
	    
	    /* register Query */
	    @RequestMapping("/register")
	    public String register(Model model) {
	    	model.addAttribute("countItems", os.cartSize() );
	    	return "paginaRegistro";
	    }
	    
	    /* modify account Query */
	    @RequestMapping("/modifyAccount")
	    public String modifyAccount(Model model,Authentication http) {
	    	if(http != null){
	    		model.addAttribute("usuario",us.getUser(http.getName()));
	    	}
	    	model.addAttribute("countItems", os.cartSize() );
	    	return "paginaModifyAccount";
	    }
	    
	    /* add user Query */
	    @RequestMapping("/updateUser")
	    public String updateUser(Model model,  Authentication http, @RequestParam(value = "phone") String phone, @RequestParam(value = "pass") String pass, @RequestParam(value = "fullName") String fullName, @RequestParam(value = "address") String address, @RequestParam(value = "email") String email) {
	    	if((http != null)){
	    		us.updateUser(http.getName(), Long.parseLong(phone), new BCryptPasswordEncoder().encode(pass), fullName, address, email);
	    	}
	    	return "/";
	    }
	    
	    
	    /* add user Query */
	    @RequestMapping("/addUser")
	    public String addUser(Model model, @RequestParam(value = "phone") String phone, @RequestParam(value = "name") String name, @RequestParam(value = "pass") String pass, @RequestParam(value = "fullName") String fullName, @RequestParam(value = "address") String address, @RequestParam(value = "email") String email) {
	    	if(us.isNameRepeat(name)){
	    		us.addUser(phone, name, pass, fullName, address, email);
	    		return "/";
	    	}
	    	model.addAttribute("alertNameIsUsed", true);
	    	return register(model);
	    }
	    
	    
	    	    	    	    
	    /* user Query */
	    @RequestMapping("/usuario")
	    public String usuarioreg(Model model, HttpServletRequest request, Authentication auth) {
	    	if(request.isUserInRole("ADMIN")){
	    		long numProducts = prs.getProductsNumber();
	    		model.addAttribute("admin", request.isUserInRole("ADMIN"));
	    		model.addAttribute("numProd",numProducts);
		    	model.addAttribute("numUsers",us.getUserNumber());
		    	model.addAttribute("numPed", os.getPedidoNumber());
		    	model.addAttribute("numCom", cos.getCommentsNumber());
		    	model.addAttribute("numPunt",numProducts);
		    	model.addAttribute("numCat", cas.getCategoryNumber());
	    		return "adminIndex";
	    	}else{
	    		model.addAttribute("usuario", request.getAttribute("USER"));
	    		model.addAttribute("usuario",us.getUser(auth.getName()));
	    		model.addAttribute("sizeOrders", os.sizeOrderUser(auth.getName()));
		    	model.addAttribute("countItems", os.cartSize() );
	    		return "paginaUsuario";
	    	}
	    }  
	    
	    /* admin Query */
	    @RequestMapping("/admin")
	    public String admin(Model model) {
	    	long numProducts = prs.getProductsNumber();
    		model.addAttribute("numProd",numProducts);
	    	model.addAttribute("numUsers",us.getUserNumber());
	    	model.addAttribute("numPed", os.getPedidoNumber());
	    	model.addAttribute("numCom", cos.getCommentsNumber());
	    	model.addAttribute("numPunt",numProducts);
	    	model.addAttribute("numCat", cas.getCategoryNumber());
	    	return "adminIndex";
	    }
}