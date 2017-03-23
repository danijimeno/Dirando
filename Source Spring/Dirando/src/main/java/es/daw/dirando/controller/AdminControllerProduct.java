package es.daw.dirando.controller;


import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.daw.dirando.repository.CategoriaRepository;
import es.daw.dirando.repository.ProductoRepository;
import es.daw.dirando.model.Categoria;
import es.daw.dirando.model.Producto;

@Controller
public class AdminControllerProduct {
	
	private static final String FILES_FOLDER = "src\\main\\webapp\\img";
	private int j;

	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private CategoriaRepository categoryRepository;

	@RequestMapping("/admin/addProduct")
	public String addProduct(Model model) {
		model.addAttribute("categorias",categoryRepository.findAll());
		return "adminAddProduct";
	}
	
	@RequestMapping("/admin/newProduct")
	public String newProduct(Model model, @RequestParam String nombre ,@RequestParam("imagen") MultipartFile imagen,
			@RequestParam String desProducto, @RequestParam String categoria, @RequestParam float precio,
			@RequestParam int stock, @RequestParam int theBest, @RequestParam int mustImprove, @RequestParam int bad) {
		
		model.addAttribute("categorias",categoryRepository.findAll());

		String fileName = "p" + j + ".jpg";
		j++;
		
		if (!imagen.isEmpty()) {
			try {

				File filesFolder = new File(FILES_FOLDER);
				if (!filesFolder.exists()) {
					filesFolder.mkdirs();
				}

				File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
				imagen.transferTo(uploadedFile);
				
				fileName="img/"+fileName;
				Producto product = new Producto(nombre, desProducto, precio, theBest, mustImprove, bad, fileName, stock, categoria);
				Categoria category = categoryRepository.findByName(categoria);
				
				productoRepository.save(product);
				category.getProductos().add(product);
				categoryRepository.save(category);
				
				//model.addAttribute("imageTitles", imageTitles);
				
				return "adminAddProduct";

			} catch (Exception e) {
				
				model.addAttribute("fileName",fileName);
				model.addAttribute("error",
						e.getClass().getName() + ":" + e.getMessage());
				
				return "adminAddProduct";
			}
		} else {
			
			model.addAttribute("error",	"The file is empty");
			
			return "adminAddProduct";
		}
		
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
	
}