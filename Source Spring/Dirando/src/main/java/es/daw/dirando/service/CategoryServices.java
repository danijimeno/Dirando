package es.daw.dirando.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.daw.dirando.model.Categoria;

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
		
}