package es.daw.dirando.controller;

import java.text.DecimalFormat;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.daw.dirando.service.CategoryServices;
import es.daw.dirando.service.OrderServices;
import es.daw.dirando.service.ProductServices;
import es.daw.dirando.service.UserServices;
import es.daw.dirando.model.Producto;


@Controller
public class WebControllerProduct {
	
	@Autowired
	private CategoryServices cas;
	
	@Autowired
	private OrderServices os;
	
	@Autowired
	private ProductServices prs;
	
	@Autowired
	private UserServices us;
	
	/*************************************************/
	/* Simple Queries */
	/*************************************************/
		    	    
	/* product Query */
	    @RequestMapping("/Producto/{id}")
	    public String productoInfo(Model model,@PathVariable long id, Authentication http){
	    	if(http != null){
	    		model.addAttribute("usuario",us.getUser(http.getName()));
	    	}
	    	model.addAttribute("countItems", os.cartSize() );
	    	model.addAttribute("producto",prs.getSpecificProduct(id));
	    	DecimalFormat decimals = new DecimalFormat("0");
	    	model.addAttribute("best", decimals.format(prs.dataRating(id)[0]));
	    	model.addAttribute("improve", decimals.format(prs.dataRating(id)[1]));
	    	model.addAttribute("worst", decimals.format(prs.dataRating(id)[2]));
	    	return "paginaDetalleProducto";
	    }
	    	    
	    /* product List Query */
	    @RequestMapping("/ListadoProductoSearch")
	    public String listadoProductos(Model model,Authentication http, @RequestParam(value = "inp-search")String search){
	    	model.addAttribute("resultSearch",search);
	    	model.addAttribute("countItems", os.cartSize() );
	    	if(http != null){
	    		model.addAttribute("usuario",us.getUser(http.getName()));
	    	}
	    	return "paginaListadoProductos";
	    }
    
    /*************************************************/
	/* Ajax Queries */
	/*************************************************/
    
	    @RequestMapping(value = "/ListadoProductoAjax/")
	    public @ResponseBody Page<Producto> listadoProductosAjax(Model model, Pageable page, @RequestParam(value = "result") String result){
	    	try {
	    	    Thread.sleep(800);
	    	} catch(InterruptedException ex) {
	    	    Thread.currentThread().interrupt();
	    	}
	    	if ( cas.getSpecificCategory(result)!=null ){
	    		return prs.getProductsByCategory(result, page);
	    	}else if ( result.equals("index") ){
	    		return prs.getAllProducts(page);
	    	}else{
	    		return prs.getProductsByName(result,page);
	    	}
	    } 
}