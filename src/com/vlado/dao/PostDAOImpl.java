package com.vlado.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vlado.FriendlyUrl;
import com.vlado.entity.Post;

@Repository
public class PostDAOImpl implements PostDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	@Override
	public List<Post> getPostList() {		
		Session session = sessionFactory.getCurrentSession();
		List<Post> posts = session.createQuery("from Post", Post.class).getResultList(); 
		for (Post post : posts) {
			post.setUser_friendly_url(FriendlyUrl.createFriendlyUrl(post.getTitle()));
		}
		return posts;
	}

	@Transactional
	@Override
	public void savePost(Post post) {		
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(post);
	}

	@Transactional
	@Override
	public Post getPost(int id) {
		Session session = sessionFactory.getCurrentSession();		
		return session.get(Post.class, id);
	}

	@Transactional
	@Override
	public void deletePost(int id) {
		Session session = sessionFactory.getCurrentSession();
		Post post = this.getPost(id);
		session.delete(post);		
	}

	@Transactional
	@Override
	public Post getPostWithTags(int id) {
		Session session = sessionFactory.getCurrentSession();
		Post post = session.get(Post.class, id);
		Hibernate.initialize(post.getTags());
		
		return post;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Post> getImportantPost() {
		Session session = sessionFactory.getCurrentSession();
		Query<Post> query = session.createQuery("from Post p where p.important = true and p.enable = true order by p.id desc");
		List<Post> posts = query.getResultList();
		for (Post post : posts) {
			post.setUser_friendly_url(FriendlyUrl.createFriendlyUrl(post.getTitle()));
		}
		return posts;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Post> getLastNumberOfPost(int number) {
		Session session = sessionFactory.getCurrentSession();
		Query<Post> query = session.createQuery("from Post p where p.enable = true order by p.id desc").setMaxResults(number);
		List<Post> posts = query.getResultList();
		for (Post post : posts) {
			post.setUser_friendly_url(FriendlyUrl.createFriendlyUrl(post.getTitle()));
		}
		return posts;
	}

	@Transactional
	@Override
	public List<Post> search(String keywords) {
		Session session = sessionFactory.getCurrentSession();
		String q = "from Post p where p.title like :keywords or p.description like :keywords or p.content like :keywords";
		Query<Post> query = session.createQuery(q, Post.class);
		query.setParameter("keywords", "%" + keywords + "%");
		List<Post> posts = query.getResultList();
		for (Post post : posts) {
			post.setUser_friendly_url(FriendlyUrl.createFriendlyUrl(post.getTitle()));
		}
		
		return posts;
	}

	@Transactional
	@Override
	public List<Post> getPostByTagId(int id) {		
		Session session = sessionFactory.getCurrentSession();		
		Query<Post> query = 
				session.createQuery("from Post p inner join p.tags t where p.tags.id = t.id and p.enable = true and t.id = :id", Post.class);
		query.setParameter("id", id);		
		return query.getResultList();
	}

	@Transactional
	@Override
	public void incrementViews(int id) {
		Session session = sessionFactory.getCurrentSession();
		Post post = session.get(Post.class, id);
		post.setViews_number(post.getViews_number()+1);
		session.update(post);		
	}

	@Transactional
	@Override
	public List<Post> searchPost(String title, int id, String author) {
		Session session = sessionFactory.getCurrentSession();
		Query<Post> query;
		
		if(id>0) {
			query = session.createQuery("from Post p where p.title like :title and p.category.id = :id and p.user.name like :author", Post.class);
			query.setParameter("id", id);
		}
		else {
			query = session.createQuery("from Post p where p.title like :title and p.user.name like :author", Post.class);			
		}
		
		query.setParameter("title", "%"+title+"%");
		query.setParameter("author", "%"+author+"%");
		
		return query.getResultList();
	}
	
	@Transactional
	@Override
	public Post getPreviousPost(int current_id) {
		
		Session session = sessionFactory.getCurrentSession();
		Query<Post> query = session.createQuery("from Post p where p.enable = true", Post.class);
		
		int current_index = -1;
		
		List<Post> posts = query.getResultList();
		
		for(int i=0; i<posts.size(); i++) {
			if(posts.get(i).getId() == current_id) {
				current_index = i;
				break;
			}			
		}
		
		if(current_index > 0) {
			Post p = posts.get(current_index-1);
			String url = FriendlyUrl.createFriendlyUrl(p.getTitle());
			p.setUser_friendly_url(url);
			return p;
		}
		
		return null;
	}

	@Transactional
	@Override
	public Post getNextPost(int current_id) {
		
		Session session = sessionFactory.getCurrentSession();
		Query<Post> query = session.createQuery("from Post p where p.enable = true", Post.class);
		
		int current_index = -1;
		
		List<Post> posts = query.getResultList();
		
		for(int i=0; i<posts.size(); i++) {
			if(posts.get(i).getId() == current_id) {
				current_index = i;
				break;
			}			
		}
		
		if(current_index < posts.size()-1) {
			Post p = posts.get(current_index+1);
			String url = FriendlyUrl.createFriendlyUrl(p.getTitle());
			p.setUser_friendly_url(url);
			return p;			
		}
		
		return null;
	}
	
	@Transactional
	@Override
	public int getCountOfPages() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("select count(id) from Post");
		return (int)query.getSingleResult();
	}

	@Transactional
	@Override
	public List<Post> getPostsByPage(int page) {
		Session session = sessionFactory.getCurrentSession();
		Query<Post> query = session.createQuery("from Post p where p.enable = true order by id desc", Post.class);
		query.setFirstResult((page-1)*4);
		query.setMaxResults(4);
				
		List<Post> posts = query.getResultList();
		for (Post post : posts) {
			post.setUser_friendly_url(FriendlyUrl.createFriendlyUrl(post.getTitle()));
		}
		
		return posts;
	}

	@Transactional
	@Override
	public List<Post> getPostsByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		List<Post> posts = session
				.createQuery("from Post p where p.user.username =: username", Post.class)
				.setParameter("username", username)
				.getResultList();
		return posts;
	}
}
