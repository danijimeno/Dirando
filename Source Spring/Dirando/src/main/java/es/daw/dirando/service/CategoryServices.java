package es.daw.dirando.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.daw.dirando.model.Categoria;
import es.daw.dirando.model.Producto;
import es.daw.dirando.repository.CategoriaRepository;

@Service
public class CategoryServices {
			
	@Autowired
	private CategoriaRepository categoriaRepository;
		
		/*Count items numbers about category*/		
		public long getCategoryNumber(){
			return categoriaRepository.count();	
		}
				
		//Get specific items and full contents about entities
		public Categoria getSpecificCategory(String category){
			return categoriaRepository.findByName(category);
		}
		
		public void deleteProductFromCategoria(Producto product){
			Categoria cat = categoriaRepository.findByName(product.getCategoria());
			List<Producto> list = cat.getProductos();
			list.remove(product);
			cat.setProductos(list);
			categoriaRepository.save(cat);
		}
}