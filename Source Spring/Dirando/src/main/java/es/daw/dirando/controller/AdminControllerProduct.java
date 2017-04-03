package es.daw.dirando.controller;


import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.daw.dirando.service.CategoryServices;
import es.daw.dirando.service.ProductServices;
import es.daw.dirando.model.Categoria;
import es.daw.dirando.model.Producto;

@Controller
public class AdminControllerProduct {
	
	private static final String FILES_FOLDER = "src\\main\\webapp\\img";
	private int j;

	@Autowired
	private ProductServices productService;
	
	@Autowired
	private CategoryServices categoryService;

	@RequestMapping("/admin/addProduct")
	public String addProduct(Model model) {
		model.addAttribute("categorias",categoryService.getAllCategories());
		return "adminAddProduct";
	}
	
	@RequestMapping("/admin/newProduct")
	public String newProduct(Model model, @RequestParam String nombre ,@RequestParam("imagen") MultipartFile imagen,
			@RequestParam String desProducto, @RequestParam String categoria, @RequestParam float precio,
			@RequestParam int stock, @RequestParam int theBest, @RequestParam int mustImprove, @RequestParam int bad) {
		
		model.addAttribute("categorias",categoryService.getAllCategories());

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
				Categoria category = categoryService.getSpecificCategory(categoria);
				
				productService.addProduct(product);
				category.getProductos().add(product);
				categoryService.saveCategory(category);
				
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
	public String listProduct(Model model, Pageable page) {
		Page<Producto> products = productService.getAllProducts(page);
		
		model.addAttribute("products",products);
		
		model.addAttribute("showNext",!products.isLast());
		model.addAttribute("showPrev",!products.isFirst());
		model.addAttribute("numPage",products.getNumber());
		model.addAttribute("nextPage",products.getNumber()+1);
		model.addAttribute("prevPage",products.getNumber()-1);
		
		return "adminListProduct";
	}
	
	@RequestMapping("/admin/product/{id}")
	public String productDetail(Model model, @PathVariable long id) {
		model.addAttribute("product",productService.getSpecificProduct(id));
		return "adminProductDetail";
	}
		
	@RequestMapping("/admin/deleteProduct/{id}")
	public String deleteProduct(Model model, @PathVariable long id, Pageable page) {
		Producto p = productService.getSpecificProduct(id);
		Categoria cat = categoryService.getSpecificCategory(p.getCategoria());
		List<Producto> list = cat.getProductos();
		list.remove(p);
		cat.setProductos(list);
		categoryService.saveCategory(cat);
		productService.deleteProduct(p);
		
		listProduct(model, page);
		return "adminListProduct";
	}
	
}