package es.daw.dirando.controller;

import java.util.List;
import java.text.DecimalFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.daw.dirando.service.CommentServices;
import es.daw.dirando.service.OrderServices;
import es.daw.dirando.service.ProductServices;
import es.daw.dirando.service.UserServices;
import es.daw.dirando.model.Comment;

@Controller
public class WebControllerComment {
	
	@Autowired
	private CommentServices cos;
	
	@Autowired
	private OrderServices os;
	
	@Autowired
	private ProductServices prs;
	
	@Autowired
	private UserServices us;
	
	/*************************************************/
	/* Simple Queries */
	/*************************************************/

	/* addComment Query */
	    @RequestMapping("/addComment")
	    public String addComment (Model model, @RequestParam(value = "comment")String comment, @RequestParam(value = "id")String id, Authentication http, @RequestParam(value = "rating")String rating) {
	    	if(http != null){
	    		model.addAttribute("usuario",us.getUser(http.getName()));
	    	}
	    	cos.addCommentIntoProduct(http.getName(), Long.parseLong(id), comment, rating);
	    	model.addAttribute("countItems", os.cartSize() );
	    	model.addAttribute("producto",prs.getSpecificProduct(Long.parseLong(id)));
	    	DecimalFormat decimals = new DecimalFormat("0");
	    	model.addAttribute("best", decimals.format(prs.dataRating(Long.parseLong(id))[0]));
	    	model.addAttribute("improve", decimals.format(prs.dataRating(Long.parseLong(id))[1]));
	    	model.addAttribute("worst", decimals.format(prs.dataRating(Long.parseLong(id))[2]));
	    	return "paginaDetalleProducto";
	    }
	    
    
    /*************************************************/
	/* Ajax Queries */
	/*************************************************/
	    
	    /*Get the comments about the product --> id...*/
	    @RequestMapping("/loadComments")
	    public @ResponseBody List<Comment> loadComments(Model model, @RequestParam String idProduct) {
	    	return cos.getComments(idProduct);
	    }
	    
}