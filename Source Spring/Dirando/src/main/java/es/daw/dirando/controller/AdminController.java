package es.daw.dirando.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.daw.dirando.repository.CategoriaRepository;
import es.daw.dirando.repository.ProductoRepository;
import es.daw.dirando.repository.UsuarioRepository;
import es.daw.dirando.model.Categoria;
import es.daw.dirando.model.Producto;

@Controller
public class AdminController {

	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private CategoriaRepository categoryRepository;
	
	@Autowired
	private UsuarioRepository userRepository;


	@RequestMapping("/admin/addProduct")
	public String addProduct(Model model) {
		model.addAttribute("categorias",categoryRepository.findAll());
		return "adminAddProduct";
	}
	
	@RequestMapping("/admin/newProduct")
	public String newProduct(Model model, @RequestParam String nombre ,@RequestParam String imagen,
			@RequestParam String desProducto, @RequestParam String categoria, @RequestParam float precio,
			@RequestParam int stock, @RequestParam int theBest, @RequestParam int mustImprove, @RequestParam int bad) {
		model.addAttribute("categorias",categoryRepository.findAll());
		Categoria category = categoryRepository.findByName(categoria);
		Producto product = new Producto(nombre, desProducto, precio, theBest, mustImprove, bad, imagen, stock, categoria);
		//product.setCategoria(category);
		productoRepository.save(product);
		category.getProductos().add(product);
		categoryRepository.save(category);
		return "adminAddProduct";
	}
	
	@RequestMapping("/admin/listProduct")
	public String listProduct(Model model) {
		model.addAttribute("products",productoRepository.findAll());
		return "adminListProduct";
	}
	
	@RequestMapping("/admin/product/{id}")
	public String productDetail(Model model, @PathVariable long id) {
		model.addAttribute("product",productoRepository.findOne(id));
		return "adminProductDetail";
	}
		
	@RequestMapping("/admin/deleteProduct/{id}")
	public String deleteProduct(Model model, @PathVariable long id) {
		Producto p = productoRepository.findOne(id);
		Categoria cat = categoryRepository.findByName(p.getCategoria());
		List<Producto> list = cat.getProductos();
		list.remove(p);
		cat.setProductos(list);
		categoryRepository.save(cat);
		productoRepository.delete(productoRepository.findOne(id));
		model.addAttribute("products",productoRepository.findAll());
		return "adminListProduct";
	}
	
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
    
    @RequestMapping("/admin/users")
	public String listUsers(Model model) {
		model.addAttribute("users",userRepository.findAll());
		return "adminUsers";
	}
   	
	
}