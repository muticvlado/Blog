package com.vlado.dao;

import java.util.List;

import org.hibernate.Hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vlado.FriendlyUrl;
import com.vlado.entity.Post;
import com.vlado.entity.Role;
import com.vlado.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public List<User> getUserList() {
		Session session = sessionFactory.getCurrentSession();		
		return session.createQuery("from User", User.class).getResultList();
	}

	@Transactional
	@Override
	public User getUserByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		User user = session
				.createQuery("From User where username =: username", User.class)
				.setParameter("username", username)
				.getSingleResult();
		Hibernate.initialize(user.getAuthorities());
		return user;
	}
	
	@Transactional
	@Override	
	public User getAuthorWithPosts(String username) {
		
		User user = getUserByUsername(username);
		Hibernate.initialize(user.getPosts());
		for (Post post : user.getPosts()) {
			String url = FriendlyUrl.createFriendlyUrl(post.getTitle());
			post.setUser_friendly_url(url);
		}
		return user;
	}

	@Transactional
	@Override
	public void saveUser(User user) {		
		Session session = sessionFactory.getCurrentSession();	
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String bcPassword = "{bcrypt}" + encoder.encode(user.getPassword());
		user.setPassword(bcPassword);
		session.saveOrUpdate(user);		
	}

	@Transactional
	@Override
	public List<Role> getRoles() {
		Session session = sessionFactory.getCurrentSession();
		Query<Role> query = session.createQuery("from Role", Role.class);
		return query.getResultList();
	}

	@Transactional
	@Override
	public void toggleUser(String username) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.createQuery("from User where username =: username", User.class)
				.setParameter("username", username).getSingleResult();
		user.setEnabled(!user.getEnabled());		
	}

	@Transactional
	@Override
	public void changeUserDetails(String username, User user) {
		Session session = sessionFactory.getCurrentSession();
		User userOld = session
				.createQuery("from User where username =: username", User.class)
				.setParameter("username", username)
				.getSingleResult();
		
		userOld.setEmail(user.getEmail());
		userOld.setImage(user.getImage());
		userOld.setName(user.getName());
		userOld.setPhone(user.getPhone());
		userOld.setSurname(user.getSurname());	
	}

	@Transactional
	@Override
	public boolean changePassword(String oldPass, String newPass, String repeatPass, String username) {
		Session session = sessionFactory.getCurrentSession();
		User user = session
				.createQuery("from User where username =: username", User.class)
				.setParameter("username", username)
				.getSingleResult();
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String dbPassword = user.getPassword().substring(8);
		
		if(encoder.matches(oldPass, dbPassword)
				&& newPass.equalsIgnoreCase(repeatPass)) {
			String password = "{bcrypt}" + encoder.encode(newPass);
			user.setPassword(password);
			return true;
		}
		
		return false;		
	}

	@Transactional
	@Override
	public void deleteUser(String username) {
		Session session = sessionFactory.getCurrentSession();
		User user = getUserByUsername(username);
		session.delete(user);
	}
}
