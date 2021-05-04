package com.vlado.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column
	private String title;
	@Column
	private String description;
	@Column
	private String content;
	@Column
	private Date date;
	@Column
	private boolean important;
	@Column
	private int views_number;
	@Column
	private String image;
	@Column
	private String image_add;
	@Column
	private boolean enable;
	
	@Transient
	private String user_friendly_url;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="category_id")
	private Category category;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "username")
	private User user;
	
	@OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
	private List<Comment> comments;
	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "post_tag", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private List<Tag> tags;
	
	public Post() {
		
	}	
	
	public Post(String title, String description, String content, Date date, boolean important, String image,
			String image_add, boolean enable, Category category, User user, List<Tag> tags) {
		
		this.title = title;
		this.description = description;
		this.content = content;
		this.date = date;
		this.important = important;
		this.image = image;
		this.image_add = image_add;
		this.enable = enable;
		this.category = category;
		this.user = user;
		this.tags = tags;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean getImportant() {
		return important;
	}

	public void setImportant(boolean important) {
		this.important = important;
	}

	public int getViews_number() {
		return views_number;
	}

	public void setViews_number(int views_number) {
		this.views_number = views_number;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public String getImageAdd() {
		return image;
	}

	public void setImageAdd(String image) {
		this.image = image;
	}
	
	public String getUser_friendly_url() {
		return user_friendly_url;
	}

	public void setUser_friendly_url(String user_friendly_url) {
		this.user_friendly_url = user_friendly_url;
	}

	public String getImage_add() {
		return image_add;
	}

	public void setImage_add(String image_add) {
		this.image_add = image_add;
	}

	public boolean getEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}
}
