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
		
		public Categoria getSpecificCategoryId(long id){
			return categoriaRepository.findOne(id);
		}
		
		public List<Categoria> getAllCategories(){
			return categoriaRepository.findAll();
		}
		
		public void deleteProductFromCategoria(Producto product){
			Categoria cat = categoriaRepository.findByName(product.getCategoria());
			List<Producto> list = cat.getProductos();
			list.remove(product);
			cat.setProductos(list);
			categoriaRepository.save(cat);
		}
		
		public void deleteCategory(Categoria category){
			for(Producto p :category.getProductos()){
	    		p.setCategoria(null);
	    	}
			category.setPublicidad(null);
	    	categoriaRepository.delete(category);
		}
		
		public Categoria addCategory(Categoria category){
			categoriaRepository.save(category);
			return category;
		}
		
		public void updateCategory(long id, Categoria category){
			Categoria oldCateg = categoriaRepository.findOne(id);
			category.setId(id);
			category.setProductos(oldCateg.getProductos());
			for(Producto p :category.getProductos()){
	    		p.setCategoria(category.getNombre());
	    	}
			category.setPublicidad(oldCateg.getPublicidad());
			categoriaRepository.save(category);
		}
}