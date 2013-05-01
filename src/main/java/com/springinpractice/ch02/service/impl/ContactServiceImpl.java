/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch02.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springinpractice.ch02.model.Contact;
import com.springinpractice.ch02.service.ContactService;


/**
 * Contact service bean.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Service
@Transactional
public class ContactServiceImpl implements ContactService {
	@Inject private SessionFactory sessionFactory;

	@Override
	public void createContact(Contact contact) {
		notNull(contact, "contact can't be null");
		getSession().save(contact);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Contact> getContacts() {
		return getSession()
			.createQuery("from Contact")
			.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Contact> getContactsByEmail(String email) {
		notNull(email, "email can't be null");
		return getSession()
			.getNamedQuery("findContactsByEmail")
			.setString("email", "%" + email + "%")
			.list();
	}

	@Override
	public Contact getContact(Long id) {
		notNull(id, "id can't be null");
		return (Contact) getSession().get(Contact.class, id);
	}

	@Override
	public void updateContact(Contact contact) {
		notNull(contact, "contact can't be null");
		getSession().update(contact);
	}

	@Override
	public void deleteContact(Long id) {
		notNull(id, "id can't be null");
		getSession().delete(getContact(id));
	}
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}
