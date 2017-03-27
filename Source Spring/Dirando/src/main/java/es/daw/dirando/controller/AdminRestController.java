package es.daw.dirando.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.daw.dirando.service.CategoryServices;
import es.daw.dirando.service.ProductServices;
import es.daw.dirando.service.UserServices;
import es.daw.dirando.model.Categoria;
import es.daw.dirando.model.Producto;
import es.daw.dirando.model.Usuario;

@RestController
@RequestMapping("/rest/admin")
public class AdminRestController {
	
	@Autowired
	private ProductServices productService;
	
	@Autowired
	private CategoryServices categoryService;
	
	@Autowired
	private UserServices userService;
	
	
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
		public Producto newProduct(@RequestBody Producto product) {
			productService.addProduct(product);
			return product;
		}
		
		@RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
		public ResponseEntity<Producto> updateProduct(@PathVariable long id, @RequestBody Producto productUpdated) {
			
			Producto product = productService.getSpecificProduct(id);
			
			if(product != null){
				productService.updateProduct(id, productUpdated);
				return new ResponseEntity<>(productUpdated, HttpStatus.OK);
			}else{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
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
		
		/*
		 ********** CATEGORIAS***********************
		 * 
		 */

		
		@RequestMapping(value = "/categories", method = RequestMethod.GET)
		public ResponseEntity<List<Categoria>> getCategories() {
			
			List<Categoria> c = categoryService.getAllCategories();
			
			if(c != null){
				return new ResponseEntity<>(c, HttpStatus.OK);
			}else{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		
		@RequestMapping(value = "/categories", method = RequestMethod.POST)
		@ResponseStatus(HttpStatus.CREATED)
		public Categoria newCategory(@RequestBody Categoria category) {
			Categoria c = categoryService.addCategory(category);
			return c;
		}
		
		@RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
		public ResponseEntity<Categoria> getCategory(@PathVariable long id) {
			
			Categoria category = categoryService.getSpecificCategoryId(id);
			
			if(category != null){
				return new ResponseEntity<>(category, HttpStatus.OK);
			}else{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		
		@RequestMapping(value = "/categories/{id}", method = RequestMethod.PUT)
		public ResponseEntity<Categoria> updateCategory(@PathVariable long id, @RequestBody Categoria categoryUpdated) {
			
			Categoria category = categoryService.getSpecificCategoryId(id);
			
			if(category != null){
				categoryService.updateCategory(id, categoryUpdated);
				return new ResponseEntity<>(categoryUpdated, HttpStatus.OK);
			}else{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		
		@RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<Categoria> deleteCategory(@PathVariable long id) {
			
			Categoria category = categoryService.getSpecificCategoryId(id);
			
			if(category != null){
				categoryService.deleteCategory(category);
				
				return new ResponseEntity<>(category, HttpStatus.OK);
			}else{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		
		/*
		 ********** USUARIOS ***********************
		 * 
		 */
		
		@RequestMapping(value = "/users", method = RequestMethod.GET)
		public ResponseEntity<List<Usuario>> getUsers() {
			
			List<Usuario> u = userService.getUsers();
			
			if(u != null){
				return new ResponseEntity<>(u, HttpStatus.OK);
			}else{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		
		/*
		@RequestMapping(value = "/users", method = RequestMethod.POST)
		@ResponseStatus(HttpStatus.CREATED)
		public Usuario newUserAdmin(@RequestBody Usuario user) {
			
			return user;
		}
		*/
		
}