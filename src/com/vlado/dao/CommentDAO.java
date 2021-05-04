package com.vlado.dao;

import java.util.List;

import com.vlado.entity.Comment;

public interface CommentDAO {

	List<Comment> getComments();
	
	List<Comment> getCommentsByPostId(int post_id);
	
	void saveComment(Comment comment);
	
	void deleteComment(int id);
	
	void toggleComment(int id);
	
}
