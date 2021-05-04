package com.vlado.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vlado.FriendlyUrl;
import com.vlado.entity.Category;
import com.vlado.entity.Post;

@SuppressWarnings("deprecation")
@Repository
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public List<Category> getCategoryList() {
		
		Session session = sessionFactory.getCurrentSession();
		Query<Category> query = session.createQuery("from Category order by order_number", Category.class); 
		
		return query.getResultList();
	}
	
	@Transactional
	@Override
	public void saveCategory(Category category) {
		
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(category);		
	}

	@Transactional
	@Override
	public Category getCategory(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		Category category = session.get(Category.class, id);
		
		return category;
	}

	@Transactional
	@Override
	public void deleteCategory(int id) {

		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("delete from Category c where c.id = :id");
		query.setParameter("id", id);
		query.executeUpdate();			
	}	
	  
	@Transactional
	@Override
	public List<Category> getCategoryListPriority() {

		Session session = sessionFactory.getCurrentSession();

		String queryString = "select category.id, category.name, count(post.id) from category " + "inner join post "
				+ "on category.id = post.category_id " + "group by category.id " + "order by order_number;";

		@SuppressWarnings("unchecked")
		NativeQuery<Object[]> query = session.createSQLQuery(queryString);
		List<Object[]> rows = query.list();
		List<Category> catList = new ArrayList<>();

		for (Object[] object : rows) {
			Category category = new Category();
			category.setId(Integer.parseInt(object[0].toString()));
			category.setName(object[1].toString());
			category.setNumberOfPosts(Integer.parseInt(object[2].toString()));	
			catList.add(category);
		}

		return catList;
	}

	@Transactional
	@Override
	public Category getCategoryWithPosts(int id) {
		Session session = sessionFactory.getCurrentSession();
		Category category = session.get(Category.class, id);
		Hibernate.initialize(category.getPosts());
		for (Post post : category.getPosts()) {
			String url = FriendlyUrl.createFriendlyUrl(post.getTitle());
			post.setUser_friendly_url(url);
		}
		return category;
	}

}
