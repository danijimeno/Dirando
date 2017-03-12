package es.daw.dirando.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="COD_Comment")
	private long id;
	
	@Column(name="User")
	private String user;
	
	@Column(name="Content")
	private String content;
	
	@Column(name="Rating")
	private String rating;
	
	public Comment (){}
	
	public Comment(String user, String content, String rating) {
		this.user=user;
		this.content=content;
		this.rating=rating;
	}

	
	
	/*Getters & setters*/
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getRating() {
		return rating;
	}


	public void setRating(String rating) {
		this.rating = rating;
	}
	
	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.rating = user;
	}

}
