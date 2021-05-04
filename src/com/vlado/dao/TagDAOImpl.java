package com.vlado.dao;

import java.util.ArrayList;
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
import com.vlado.entity.Tag;

@Repository
public class TagDAOImpl implements TagDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public List<Tag> getTagList() {
		Session session = sessionFactory.getCurrentSession();		
		return session.createQuery("from Tag t order by t.name", Tag.class).getResultList();
	}

	@Transactional
	@Override
	public List<Tag> getTagsByIds(List<Integer> ids) {
		Session session = sessionFactory.getCurrentSession();	
		@SuppressWarnings("unchecked")
		Query<Tag> query = session.createQuery("select t from Tag t where t.id in (:ids)");
		query.setParameter("ids", ids);
		
		return query.getResultList();
	}

	@Transactional
	@Override
	public void saveTag(Tag tag) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(tag);		
	}

	@Transactional
	@Override
	public void deleteTag(int id) {
		Session session = sessionFactory.getCurrentSession();
		Tag tag = session.get(Tag.class, id);
		session.delete(tag);		
	}

	@Transactional
	@Override
	public Tag getTag(int id) {
		Session session = sessionFactory.getCurrentSession();
		Tag tag = session.get(Tag.class, id);
		return tag;
	}

	@Transactional
	@Override
	public Tag getTagWithPosts(int id) {
		Session session = sessionFactory.getCurrentSession();
		Tag tag = session.get(Tag.class, id);
		Hibernate.initialize(tag.getPosts());
		for (Post post : tag.getPosts()) {
			String url = FriendlyUrl.createFriendlyUrl(post.getTitle());
			post.setUser_friendly_url(url);
		}
		return tag;
	}

	@Transactional
	@Override
	public List<Tag> getTheMostUseTags() {
		
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select tag.id, tag.name, count(tag.id) from tag " +
				"inner join post_tag on tag.id = post_tag.tag_id " +
				"group by tag.id " +
				"order by count(tag.id) desc";
		
		@SuppressWarnings("unchecked")
		Query<Object[]> query = session.createSQLQuery(queryString);
		List<Object[]> results = query.getResultList();
		List<Tag> tags = new ArrayList<>();
		
		for(Object[] x : results) {
			Tag t = new Tag();
			t.setId((Integer.parseInt(x[0].toString())));
			t.setName(x[1].toString());
			t.setUse_number((Integer.parseInt(x[2].toString())));
			tags.add(t);
		}
		
		return tags;
	}

}
