package es.daw.dirando.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import es.daw.dirando.repository.ProductoRepository;
import es.daw.dirando.repository.PublicidadRepository;

@Controller
public class WebController {
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private PublicidadRepository publicidadRepository;
	
    @RequestMapping("/")
    public String index(Model model) {
    	model.addAttribute("productos",productoRepository.findAll());
    	model.addAttribute("carrusel",publicidadRepository.findAll());
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
    	return "login";
    }
    
    @RequestMapping("/Producto/{id}")
    public String productoInfo(Model model,@PathVariable long id){
    	model.addAttribute("producto",productoRepository.findOne(id));
    	return "paginaDetalleProducto";
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
