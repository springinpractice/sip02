/* 
 * Book web site   - http://www.manning.com/wheeler/
 * Book blog       - http://springinpractice.com/
 * Author web site - http://wheelersoftware.com/
 */
package com.springinpractice.ch02.dao.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.springinpractice.ch02.dao.ContactDao;
import com.springinpractice.ch02.model.Contact;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Repository
public class JpaContactDao implements ContactDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/* (non-Javadoc)
	 * @see com.springinpractice.dao.Dao#create(java.lang.Object)
	 */
	@Override
	public void create(Contact contact) {
		entityManager.persist(contact);
	}

	/* (non-Javadoc)
	 * @see com.springinpractice.dao.Dao#get(java.io.Serializable)
	 */
	@Override
	public Contact get(Serializable id) {
		// This returns null when the object doesn't exist
		return entityManager.find(Contact.class, id);
	}

	/* (non-Javadoc)
	 * @see com.springinpractice.dao.Dao#load(java.io.Serializable)
	 */
	@Override
	public Contact load(Serializable id) {
		
		// TODO Check whether there's a JPA equivalent to Hibernate load()
		Contact contact = get(id);
		if (contact == null) {
			throw new RuntimeException("No such contact: " + id);
		}
		return contact;
	}

	/* (non-Javadoc)
	 * @see com.springinpractice.dao.Dao#getAll()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Contact> getAll() {
		return (List<Contact>) entityManager.createQuery("from Contact").getResultList();
	}

	/* (non-Javadoc)
	 * @see com.springinpractice.dao.Dao#update(java.lang.Object)
	 */
	@Override
	public void update(Contact contact) {
		entityManager.merge(contact);
	}

	/* (non-Javadoc)
	 * @see com.springinpractice.dao.Dao#delete(java.lang.Object)
	 */
	@Override
	public void delete(Contact contact) {
		entityManager.remove(contact);
	}

	/* (non-Javadoc)
	 * @see com.springinpractice.dao.Dao#deleteById(java.io.Serializable)
	 */
	@Override
	public void deleteById(Serializable id) {
		entityManager
			.createQuery("delete from Contact where id = :id")
			.setParameter("id", id)
			.executeUpdate();
	}

	/* (non-Javadoc)
	 * @see com.springinpractice.dao.Dao#deleteAll()
	 */
	@Override
	public void deleteAll() {
		entityManager
			.createQuery("delete from Contact")
			.executeUpdate();
	}

	/* (non-Javadoc)
	 * @see com.springinpractice.dao.Dao#count()
	 */
	@Override
	public long count() {
		return (Long) entityManager
			.createQuery("select count(*) from Contact")
			.getSingleResult();
	}

	/* (non-Javadoc)
	 * @see com.springinpractice.dao.Dao#exists(java.io.Serializable)
	 */
	@Override
	public boolean exists(Serializable id) { return (get(id) != null); }
}
