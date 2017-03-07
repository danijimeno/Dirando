package es.daw.dirando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import es.daw.dirando.repository.ProductoRepository;
import es.daw.dirando.model.Producto;

@Controller
public class AdminController {

	@Autowired
	private ProductoRepository productoRepository;


	@RequestMapping("/admin/addProduct")
	public String addProduct(Model model) {
		return "adminAddProduct";
	}
	
	@RequestMapping("/admin/newProduct")
	public String newProduct(Model model, Producto producto) {
		productoRepository.save(producto);
		return "adminAddProduct";
	}
	
	@RequestMapping("/admin/listProduct")
	public String listProduct(Model model) {
		model.addAttribute("products",productoRepository.findAll());
		return "adminListProduct";
	}

	
}