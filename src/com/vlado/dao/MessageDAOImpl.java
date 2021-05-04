package com.vlado.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vlado.entity.Message;

@Repository
public class MessageDAOImpl implements MessageDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public List<Message> getMessages() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unused")
		Query<Message> query = session.createQuery("from Message", Message.class);
		return query.getResultList();
	}

	@Transactional
	@Override
	public Message getMessageById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Message message = session.get(Message.class, id);
		return message;
	}

	@Transactional
	@Override
	public void saveMessage(Message message) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(message);
	}

	@Transactional
	@Override
	public void deleteMessage(int id) {
		Session session = sessionFactory.getCurrentSession();
		Message message = session.get(Message.class, id);
		session.delete(message);
	}

	@Transactional
	@Override
	public void toggleMessage(int id) {
		Session session = sessionFactory.getCurrentSession();
		Message message = session.get(Message.class, id);
		message.setChecked(!message.getChecked());		
	}
}
