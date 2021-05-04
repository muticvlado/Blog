package com.vlado.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Slide {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column
	private String title;	
	@Column
	private String image;
	@Column
	private String button_label;
	@Column
	private String button_url;
	@Column
	private int order_number;
	@Column
	private boolean enable;
		
	public Slide() {
		
	}

	public Slide(String title, String image, String button_label, String button_url, int order_number, boolean enable) {
		this.title = title;
		this.image = image;
		this.button_label = button_label;
		this.button_url = button_url;
		this.order_number = order_number;
		this.enable = enable;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getButton_label() {
		return button_label;
	}

	public void setButton_label(String button_label) {
		this.button_label = button_label;
	}

	public String getButton_url() {
		return button_url;
	}

	public void setButton_url(String button_url) {
		this.button_url = button_url;
	}

	public int getOrder_number() {
		return order_number;
	}

	public void setOrder_number(int order_number) {
		this.order_number = order_number;
	}

	public boolean getEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
