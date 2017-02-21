package es.daw.dirando.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
    	return "login";
    }
    
    @RequestMapping("/usuario")
    public String usuarioreg(Model model, HttpServletRequest request) {
    	
    	if(request.isUserInRole("ADMIN")){
    		model.addAttribute("admin", request.isUserInRole("ADMIN"));
    		return "adminIndex";
    	}else{
    		model.addAttribute("usuario", request.getAttribute("USER"));
    		return "paginaUsuario";
    	}
    }    
    
    @RequestMapping("/registro")
    public String loginerror() {
    	return "paginaRegistro";
    }


    
    @RequestMapping("/admin")
    public String admin() {
    	return "paginaUsuario";
    }
}
