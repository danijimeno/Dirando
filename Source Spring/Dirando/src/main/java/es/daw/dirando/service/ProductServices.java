package es.daw.dirando.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.daw.dirando.model.Producto;

import es.daw.dirando.repository.ProductoRepository;

@Service
public class ProductServices {
	
	@Autowired
	private ProductoRepository productoRepository;
				
		/*Count items numbers about product*/
		public long getProductsNumber(){
			return productoRepository.count();	
		}		
				
		//IN WORK...
		public float[] dataRating (long id){
			float [] data= new float [3];
			float total = productoRepository.findProductoById(id).getTheBest() + productoRepository.findProductoById(id).getMustImprove() + productoRepository.findProductoById(id).getBad();
	    	data[0] = productoRepository.findProductoById(id).getTheBest() / total * 100;
	    	data[1] = productoRepository.findProductoById(id).getMustImprove() / total * 100;
	    	data[2] = productoRepository.findProductoById(id).getBad() / total * 100;
	    	return data;
		}
		
		//Search TOOLS 
		public Page<Producto> getProductsByName(String name, Pageable page){
			return productoRepository.findByNombre(name, page);
		}
		public Page<Producto> getAllProducts(Pageable page){
			return productoRepository.findAll(page);
		}
		public Page<Producto> getProductsByCategory(String category, Pageable page){
			return productoRepository.findByCategoria(category, page);
				}
		
		//Get specific items and full contents about entities
		public Producto getSpecificProduct(long id){
			return productoRepository.findOne(id);
		}
		public Page<Producto> get3Items(Pageable page){
			return productoRepository.findAll(page);
		}
		
		public void deleteProduct(Producto product){
			productoRepository.delete(product);
		}
		
		public Producto addProduct(Producto product){
			productoRepository.save(product);
			return product;
		}
		
		public void updateProduct(long id, Producto product){
			Producto oldProd = productoRepository.findOne(id);
			product.setId(id);
			if(product.getNombre()== null){
				product.setNombre(oldProd.getNombre());
			}
			if(product.getDesProducto()==null){
				product.setDesProducto(oldProd.getDesProducto());
			}
			if(product.getImage()==null){
				product.setImage(oldProd.getImage());
			}
			if(product.getPrecio()==0){
				product.setPrecio(oldProd.getPrecio());
			}
			if(product.getCategoria()==null){
				product.setCategoria(oldProd.getCategoria());
			}
			productoRepository.save(product);
		}
		
}