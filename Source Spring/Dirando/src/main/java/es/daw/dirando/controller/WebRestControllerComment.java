package es.daw.dirando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.daw.dirando.model.Comment;
import es.daw.dirando.model.CommentComponent;
import es.daw.dirando.service.CommentServices;

@RestController
@RequestMapping("/rest")
public class WebRestControllerComment {
	
	@Autowired
	private CommentServices cos;
		
		//Para angular
		@RequestMapping(value = "/commentary2", method = RequestMethod.PUT)
		public ResponseEntity<String> addComment2(@RequestBody CommentComponent comment) {
			cos.addCommentIntoProduct(comment.getName(), Long.parseLong(comment.getIdProduct()), comment.getComment(), comment.getRating());
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		
		@RequestMapping(value = "/commentary", method = RequestMethod.PUT)
		public ResponseEntity<String> addComment(Authentication http, @RequestBody Comment comment) {
			System.out.println(comment.getId() +" "+comment.getContent()+" "+ comment.getRating());
			if(http != null){
				cos.addCommentIntoProduct(http.getName(), comment.getId(), comment.getContent(), comment.getRating());
				return new ResponseEntity<>(HttpStatus.CREATED);
			}else{
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		}
}