package es.daw.dirando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.daw.dirando.repository.CategoriaRepository;
import es.daw.dirando.model.Categoria;
import es.daw.dirando.model.Producto;

@Controller
public class AdminControllerCategory {
	
	@Autowired
	private CategoriaRepository categoryRepository;
	
	@RequestMapping("/admin/addCategory")
    public String addCategory(Model model, @RequestParam String category) {
		model.addAttribute("categorias",categoryRepository.findAll());
	    categoryRepository.save(new Categoria(category.trim()));
	    model.addAttribute("categorias",categoryRepository.findAll());
    	return "adminCategories";
    }
   	
    @RequestMapping("/admin/categories")
    public String listCategories(Model model) {
    	model.addAttribute("categorias",categoryRepository.findAll());
    	return "adminCategories";
    }
    
    @RequestMapping("/admin/deleteCategory/{id}")
    public String deleteCategory(Model model, @PathVariable long id) {
    	Categoria cat = categoryRepository.findOne(id);
    	for(Producto p :cat.getProductos()){
    		p.setCategoria(null);
    	}
    	categoryRepository.delete(cat);
    	
		model.addAttribute("categorias",categoryRepository.findAll());
    	return "adminCategories";
    }
       	
}