package es.daw.dirando.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.daw.dirando.service.CategoryServices;
import es.daw.dirando.service.ProductServices;
import es.daw.dirando.model.Producto;

@RestController
@RequestMapping("/rest/admin")
public class AdminRestController {
	
	@Autowired
	private ProductServices productService;
	
	@Autowired
	private CategoryServices categoryService;
	
	
		@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
		public ResponseEntity<Producto> getProduct(@PathVariable long id) {
			
			Producto product = productService.getSpecificProduct(id);
			
			if(product != null){
				return new ResponseEntity<>(product, HttpStatus.OK);
			}else{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		
		@RequestMapping(value = "/products", method = RequestMethod.POST)
		@ResponseStatus(HttpStatus.CREATED)
		public void addProduct(@RequestParam String nombre ,@RequestParam("imagen") String imagen,
				@RequestParam String desProducto, @RequestParam String categoria, @RequestParam float precio,
				@RequestParam int stock, @RequestParam int theBest, @RequestParam int mustImprove, @RequestParam int bad) {
			productService.addProduct(nombre, imagen, desProducto, categoria, precio, stock, theBest, mustImprove, bad);;
			//return product;
		}
		
		@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<Producto> deleteProduct(@PathVariable long id) {
			
			Producto product = productService.getSpecificProduct(id);
			
			if(product != null){
				categoryService.deleteProductFromCategoria(product);
				productService.deleteProduct(product);
				
				return new ResponseEntity<>(product, HttpStatus.OK);
			}else{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		
		@RequestMapping(value = "/products", method = RequestMethod.GET)
		public ResponseEntity<Page<Producto>> getProducts(Pageable page) {
			
			Page<Producto> p = productService.getAllProducts(page);
			
			if(p != null){
				return new ResponseEntity<>(p, HttpStatus.OK);
			}else{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}

		
}