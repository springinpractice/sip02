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

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springinpractice.ch02.dao.ContactDao;
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
	@Inject private ContactDao contactDao;

	@Override
	public void createContact(Contact contact) {
		notNull(contact, "contact can't be null");
		contactDao.create(contact);
	}

	@Override
	public List<Contact> getContacts() {
		return contactDao.getAll();
	}
	
	@Override
	public List<Contact> getContactsByEmail(String email) {
		notNull(email, "email can't be null");
		return contactDao.findByEmail(email);
	}

	@Override
	public Contact getContact(Long id) {
		notNull(id, "id can't be null");
		return contactDao.get(id);
	}

	@Override
	public void updateContact(Contact contact) {
		notNull(contact, "contact can't be null");
		contactDao.update(contact);
	}

	@Override
	public void deleteContact(Long id) {
		notNull(id, "id can't be null");
		contactDao.deleteById(id);
	}
}
