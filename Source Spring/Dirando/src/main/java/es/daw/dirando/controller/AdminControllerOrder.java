package es.daw.dirando.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.daw.dirando.service.UserServices;

@Controller
public class AdminControllerOrder {
	
	@Autowired
	private UserServices userService;
	    
    @RequestMapping("/admin/orders")
	public String listOrders(Model model) {
    	
		model.addAttribute("users",userService.getUsers());
		
		return "adminOrders";
	}	
}