package es.daw.dirando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.daw.dirando.service.CategoryServices;
import es.daw.dirando.model.Categoria;

@Controller
public class AdminControllerCategory {
	
	@Autowired
	private CategoryServices categoryService;
	
	@RequestMapping("/admin/addCategory")
    public String addCategory(Model model, @RequestParam String category) {
		model.addAttribute("categorias",categoryService.getAllCategories());
	    categoryService.saveCategory(new Categoria(category.trim()));
	    model.addAttribute("categorias",categoryService.getAllCategories());
    	return "adminCategories";
    }
   	
    @RequestMapping("/admin/categories")
    public String listCategories(Model model) {
    	model.addAttribute("categorias",categoryService.getAllCategories());
    	return "adminCategories";
    }
    
    @RequestMapping("/admin/deleteCategory/{id}")
    public String deleteCategory(Model model, @PathVariable long id) {
    	Categoria cat = categoryService.getSpecificCategoryId(id);
    	categoryService.deleteCategory(cat);
		model.addAttribute("categorias",categoryService.getAllCategories());
    	return "adminCategories";
    }
       	
}