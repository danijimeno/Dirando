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
		
		/*Index Content Methods*/
			@RequestMapping(value = "/indexItems", method = RequestMethod.GET)
			public ResponseEntity< Page<Producto> > getIndexItems(Pageable page) {
				return new ResponseEntity<>(prs.get3Items(page),HttpStatus.OK);
			}
			@RequestMapping(value = "/carrusel", method = RequestMethod.GET)
			public ResponseEntity< Iterable<Publicidad> > getCarrusel() {
				return new ResponseEntity<>(pus.findAllPublicity(),HttpStatus.OK);
			}
		/*End Index Content Methods*/
		
		/*Search Methods*/
			@RequestMapping(value = "/items/{result}", method = RequestMethod.GET)
			public ResponseEntity< Page<Producto> > getItemsBySearch(@PathVariable String result, Pageable page) {
				if ( cas.getSpecificCategory(result) != null ){
					Page<Producto> pc = prs.getProductsByCategory(result, page);
		    		if (!pc.getContent().isEmpty()){
		    			return new ResponseEntity<>(pc,HttpStatus.OK);
		    		}else{
		    			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    		}
		    	}else{
		    		Page<Producto> pn = prs.getProductsByName(result,page);
		    		if (!pn.getContent().isEmpty()){
		    			return new ResponseEntity<>(pn,HttpStatus.OK);
		    		}else{
		    			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    		}
		    	}
			}
		/*Search Methods*/
		
		/*Page Product Methods*/
			@RequestMapping(value = "/productDetail/{id}", method = RequestMethod.GET)
			public ResponseEntity< Producto > productDetail(@PathVariable String id) {
				Producto product = prs.getSpecificProduct(Long.parseLong(id));
				if (product != null){
					return new ResponseEntity<>(product,HttpStatus.OK);
				}else{
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}	
			}
			@RequestMapping(value = "/productRating/{id}", method = RequestMethod.GET)
			public ResponseEntity<float[]> getRating(@PathVariable String id) {
				if ( prs.getSpecificProduct(Long.parseLong(id)) != null ){
					float[] ra = prs.dataRating(Long.parseLong(id));
					if (ra != null){
						return new ResponseEntity<>(ra,HttpStatus.OK);
					}else{
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					}
				}else{
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
			}
		/*End Page Product Methods*/
		
}