package com.vlado.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vlado.FriendlyUrl;
import com.vlado.entity.Comment;
import com.vlado.entity.Post;

@Repository
public class CommentDAOImpl implements CommentDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public List<Comment> getComments() {
		Session session = sessionFactory.getCurrentSession();		
		@SuppressWarnings("unchecked")
		Query<Comment> query = session.createQuery("from Comment c order by c.id desc");
		List<Comment> comments = query.getResultList();
		return comments;
	}

	@Transactional
	@Override
	public void saveComment(Comment comment) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(comment);		
	}

	@Transactional
	@Override
	public List<Comment> getCommentsByPostId(int post_id) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		Query<Comment> query = session.createQuery("from Comment c where c.post.id = :post_id and c.enable = true");
		query.setParameter("post_id", post_id);
		List<Comment> comments = query.getResultList();
		Post post = session.get(Post.class, post_id);
		post.setUser_friendly_url(FriendlyUrl.createFriendlyUrl(post.getTitle()));
		return comments;
	}

	@Transactional
	@Override
	public void deleteComment(int id) {
		Session session = sessionFactory.getCurrentSession();
		Comment comment = session.get(Comment.class, id);
		session.delete(comment);
	}

	@Transactional
	@Override
	public void toggleComment(int id) {
		Session session = sessionFactory.getCurrentSession();
		Comment comment = session.get(Comment.class, id);
		comment.setEnable(!comment.getEnable());		
	}
}
