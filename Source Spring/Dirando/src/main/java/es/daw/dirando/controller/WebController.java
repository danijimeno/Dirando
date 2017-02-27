package es.daw.dirando.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import es.daw.dirando.repository.ProductoRepository;
import es.daw.dirando.repository.PublicidadRepository;
import es.daw.dirando.repository.UsuarioRepository;

@Controller
public class WebController {
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private PublicidadRepository publicidadRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
    @RequestMapping("/")
    public String index(Model model,Authentication http) {
    	model.addAttribute("productos",productoRepository.findAll());
    	model.addAttribute("carrusel",publicidadRepository.findAll());
    	if(http != null){
    		model.addAttribute("usuario",usuarioRepository.findUserByName(http.getName()));
    		}
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
    public String usuarioreg(Model model, HttpServletRequest request,Authentication auth) {
    	System.out.println(request.isUserInRole("ADMIN")+"---------------------aki");
    	if(request.isUserInRole("ADMIN")){
    		model.addAttribute("admin", request.isUserInRole("ADMIN"));
    		return "adminIndex";
    	}else{
    		model.addAttribute("usuario", request.getAttribute("USER"));
    		model.addAttribute("usuario",usuarioRepository.findUserByName(auth.getName()));
    		return "paginaUsuario";
    	}
    }    
    
    @RequestMapping("/registro")
    public String loginerror() {
    	return "paginaRegistro";
    }
    
    @RequestMapping("/logout")
    public String logOut(HttpServletRequest http,Authentication auth){
    	try {
    		http.logout();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Model model = null;
    	return index(model,auth);
    }


    
    @RequestMapping("/admin")
    public String admin() {
    	return "paginaUsuario";
    }
}
