/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch02.dao.jpa;

import static org.springframework.util.Assert.notNull;

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
	
	@Override
	public void create(Contact contact) {
		notNull(contact, "contact can't be null");
		entityManager.persist(contact);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Contact> getAll() {
		return entityManager
			.createQuery("from Contact")
			.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Contact> findByEmail(String email) {
		notNull(email, "email can't be null");
		return entityManager
			.createNamedQuery("findContactsByEmail")
			.setParameter("email", "%" + email + "%")
			.getResultList();
	}

	@Override
	public Contact get(Serializable id) {
		notNull(id, "id can't be null");
		
		// This returns null when the object doesn't exist
		return entityManager.find(Contact.class, id);
	}

	@Override
	public Contact load(Serializable id) {
		notNull(id, "id can't be null");
		
		// TODO Check whether there's a JPA equivalent to Hibernate load()
		Contact contact = get(id);
		if (contact == null) {
			throw new RuntimeException("No such contact: " + id);
		}
		return contact;
	}

	@Override
	public void update(Contact contact) {
		notNull(contact, "contact can't be null");
		entityManager.merge(contact);
	}

	@Override
	public void delete(Contact contact) {
		notNull(contact, "contact can't be null");
		entityManager.remove(contact);
	}

	@Override
	public void deleteById(Serializable id) {
		notNull(id, "id can't be null");
		entityManager
			.createQuery("delete from Contact where id = :id")
			.setParameter("id", id)
			.executeUpdate();
	}

	@Override
	public void deleteAll() {
		entityManager
			.createQuery("delete from Contact")
			.executeUpdate();
	}

	@Override
	public long count() {
		return (Long) entityManager
			.createQuery("select count(*) from Contact")
			.getSingleResult();
	}

	@Override
	public boolean exists(Serializable id) {
		notNull(id, "id can't be null");
		return (get(id) != null);
	}
}
