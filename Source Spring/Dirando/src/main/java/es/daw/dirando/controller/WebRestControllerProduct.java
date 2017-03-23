package es.daw.dirando.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.daw.dirando.service.CategoryServices;
import es.daw.dirando.service.ProductServices;
import es.daw.dirando.service.PublicServices;
import es.daw.dirando.model.Producto;
import es.daw.dirando.model.Publicidad;

@RestController
@RequestMapping("/rest")
public class WebRestControllerProduct {
	
	@Autowired
	private CategoryServices cas;
	
	@Autowired
	private ProductServices prs;
	
	@Autowired
	private PublicServices pus;
	
	
	/*START*************************************/
	/*REST METHODS FRONTEND*/
	/**************************************/
	
		@RequestMapping(value = "/", method = RequestMethod.GET)
		public Page<Producto> getIndexItems(Pageable page) {
	        return prs.get3Items(page);
		}

		@RequestMapping(value = "/carrusel", method = RequestMethod.GET)
		public Iterable<Publicidad> getCarrusel() {
			Iterable<Publicidad> publicity = pus.findAllPublicity();
			return publicity;
		}
				
		@RequestMapping(value = "/getItems", method = RequestMethod.GET)
		public Page<Producto> getItemsBySearch(String result, Pageable page) {
			if ( cas.getSpecificCategory(result)!=null ){
	    		return prs.getProductsByCategory(result, page);
	    	}else{
	    		
	    		return prs.getProductsByName(result,page);
	    	}
		}
							
		@RequestMapping(value = "/productDetail", method = RequestMethod.GET)
		public Producto productDetail(String id) {
			return prs.getSpecificProduct(Long.parseLong(id));
		}
		@RequestMapping(value = "/getRating", method = RequestMethod.GET)
		public float[] getRating(String id) {
			return prs.dataRating(Long.parseLong(id));
		}
							
	/*FINISH FRONTEND METHODS*************************************/
		
}