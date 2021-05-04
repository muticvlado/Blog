package com.vlado.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vlado.entity.Slide;

@Repository
public class SlideDAOImpl implements SlideDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public List<Slide> getSlideList() {
		Session session = sessionFactory.getCurrentSession();
		Query<Slide> query = session.createQuery("from Slide", Slide.class);
		return query.getResultList();
	}
	
	@Transactional
	@Override
	public List<Slide> getSlider() {
		Session session = sessionFactory.getCurrentSession();
		Query<Slide> query = session.createQuery("from Slide s where s.enable = true", Slide.class);
		return query.getResultList();
	}

	@Transactional
	@Override
	public void saveSlide(Slide slide) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(slide);		
	}

	@Transactional
	@Override
	public void deleteSlide(int id) {
		Session session = sessionFactory.getCurrentSession();
		session.delete("delete Slide", id);
	}

	@Transactional
	@Override
	public Slide getSlideById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Slide.class, id);
	}	
}
