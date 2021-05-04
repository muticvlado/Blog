package com.vlado.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table
public class Comment {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private Date date;
	@Column
	@NotBlank(message = "Comment can not be null!")
	private String text;
	@Column
	@NotBlank(message = "Author can not be null!")
	private String author;
	@Column
	@NotBlank(message = "Email can not be null!")
	@Email(message = "Email should be valid")
	private String email;
	@Column
	private boolean enable = true;	
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "post_id")
	private Post post;	 
	
	public Comment() {
		this.date = new Date();
	}

	public Comment(Date date, String text, String author, String email, boolean enable, Post post) {
		this.date = new Date();
		this.text = text;
		this.author = author;
		this.email = email;
		this.enable = enable;
		this.post = post;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean getEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {		
		this.enable = enable;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
}
