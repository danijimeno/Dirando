package es.daw.dirando.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.daw.dirando.service.CommentServices;

@RestController
@RequestMapping("/rest")
public class WebRestControllerComment {
	
	@Autowired
	private CommentServices cos;
	
	/*START*************************************/
	/*REST METHODS FRONTEND*/
	/**************************************/
			
		@RequestMapping(value = "/addComment", method = RequestMethod.PUT)
		public void addComment(Authentication http, String id, String comment, String rating) {
			if(http != null){
				cos.addCommentIntoProduct(http.getName(), id, comment, rating);
			}
		}
		
	/*FINISH FRONTEND METHODS*************************************/
		
}