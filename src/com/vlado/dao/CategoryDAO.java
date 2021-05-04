package com.vlado.dao;

import java.util.List;

import com.vlado.entity.Category;

public interface CategoryDAO {

	List<Category> getCategoryList();
	
	List<Category> getCategoryListPriority();
	
	void saveCategory(Category category);
	
	Category getCategory(int id);
	
	Category getCategoryWithPosts(int id);
	
	void deleteCategory(int id);

}
