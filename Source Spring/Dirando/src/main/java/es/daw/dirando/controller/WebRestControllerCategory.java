package es.daw.dirando.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.daw.dirando.service.ProductServices;
import es.daw.dirando.model.Producto;

@RestController
@RequestMapping("/rest")
public class WebRestControllerCategory {
		
	@Autowired
	private ProductServices prs;
		
	/*START*************************************/
	/*REST METHODS FRONTEND*/
	/**************************************/
			
		@RequestMapping(value = "/getCategory", method = RequestMethod.GET)
		public Page<Producto> getItemsByCategory(String category, Pageable page) {
			return prs.getProductsByCategory(category, page);
		}
		
	/*FINISH FRONTEND METHODS*************************************/
		
}