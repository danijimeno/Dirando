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
import es.daw.dirando.repository.PublicidadRepository;
import es.daw.dirando.repository.UsuarioRepository;
import es.daw.dirando.model.Categoria;
import es.daw.dirando.model.Producto;
import es.daw.dirando.model.Publicidad;

@Controller
public class AdminController {
	
	private static final String FILES_FOLDER = "src\\main\\webapp\\img";
	private int i;

	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private CategoriaRepository categoryRepository;
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@Autowired
	private PublicidadRepository publicidadRepository;


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
   	
    @RequestMapping("/admin/advertising")
	public String advertising() {
		return "adminAdvertising";
	}
    
    @RequestMapping("/adming/advertising/upload")
	public String uploadAdvertising(Model model, @RequestParam("imagen") MultipartFile imagen) {
    	String fileName = i + ".jpg";
		i++;
		
		if (!imagen.isEmpty()) {
			try {

				File filesFolder = new File(FILES_FOLDER);
				if (!filesFolder.exists()) {
					filesFolder.mkdirs();
				}

				File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
				imagen.transferTo(uploadedFile);
				System.out.println(filesFolder.getAbsolutePath());
				fileName="img/"+fileName;
				Publicidad p = new Publicidad("",fileName);
				publicidadRepository.save(p);
				
				//model.addAttribute("imageTitles", imageTitles);
				
				return "adminAdvertising";

			} catch (Exception e) {
				
				model.addAttribute("fileName",fileName);
				model.addAttribute("error",
						e.getClass().getName() + ":" + e.getMessage());
				
				return "adminAdvertising";
			}
		} else {
			
			model.addAttribute("error",	"The file is empty");
			
			return "adminAdvertising";
		}
   
	}
	
}