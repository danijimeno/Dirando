package es.daw.dirando.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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

		@RequestMapping(value = "/category/{cat}", method = RequestMethod.GET)
		public ResponseEntity< Page<Producto> > getItemsByCategory(@PathVariable String cat, Pageable page) {
			Page<Producto> products = prs.getProductsByCategory (cat, page);
			if (products.getContent().isEmpty()){
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}else{
				return new ResponseEntity<>( prs.getProductsByCategory (cat, page), HttpStatus.OK );
			}
		}		
}