package com.vlado.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

@Entity
@Table
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column
	@Size(min=2, max=50, message = "Min 2, max 50 characters.")
	private String name;
	@Column
	private String description;
	@Column
	private int order_number;
	
	@OneToMany(mappedBy = "category")
	private List<Post> posts;
	
	@Transient
	private int numberOfPosts;
	
	@Transient
	private String user_friendly_url;
	
	public Category() {
		
	}

	public Category(String name, String description, int order_number) {
		super();
		this.name = name;
		this.description = description;
		this.order_number = order_number;
	}
	
	public Category(String name, String description, int order_number, int numberOfPosts) {
		super();
		this.name = name;
		this.description = description;
		this.order_number = order_number;
		this.numberOfPosts = numberOfPosts;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getOrder_number() {
		return order_number;
	}

	public void setOrder_number(int order_number) {
		this.order_number = order_number;
	}

	public String getUser_friendly_url() {
		return user_friendly_url;
	}

	public void setUser_friendly_url(String user_friendly_url) {
		this.user_friendly_url = user_friendly_url;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public int getNumberOfPosts() {
		return numberOfPosts;
	}

	public void setNumberOfPosts(int numberOfPosts) {
		this.numberOfPosts = numberOfPosts;
	}	
	
}
