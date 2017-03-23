package es.daw.dirando.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.daw.dirando.repository.PublicidadRepository;
import es.daw.dirando.model.Publicidad;

@Controller
public class AdminControllerAdvertising {
	
	private static final String FILES_FOLDER = "src\\main\\webapp\\img";
	private int i;
	
	@Autowired
	private PublicidadRepository publicidadRepository;
   	
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