package com.vlado.dao;

import java.util.List;

import com.vlado.entity.Post;

public interface PostDAO {
	
	List<Post> getPostList();
	
	List<Post> getImportantPost();
	
	List<Post> getLastNumberOfPost(int number);
	
	void savePost(Post post);	
	
	void deletePost(int id);
	
	Post getPost(int id);
	
	Post getPostWithTags(int id);
	
	List<Post> getPostByTagId(int id);
	
	List<Post> search(String keywords);
	
	void incrementViews(int id);
	
	List<Post> searchPost(String title, int categoryId, String authorName);
		
	Post getPreviousPost(int current_id);
	
	Post getNextPost(int current_id);
	
	List<Post> getPostsByUsername(String username);
	
	int getCountOfPages();
	
	List<Post> getPostsByPage(int page);
}
