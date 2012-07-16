package com.springinpractice.ch02.service;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.springinpractice.ch02.dao.ContactDao;
import com.springinpractice.ch02.model.Contact;


/**
 * Contact service bean.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Service
public class ContactServiceImpl implements ContactService {
	@Inject private ContactDao contactDao;

	@Override
	public void createContact(Contact contact) {
		notNull(contact);
		contactDao.create(contact);
	}

	@Override
	public List<Contact> getContacts() { return contactDao.getAll(); }

	@Override
	public Contact getContact(long id) { return contactDao.get(id); }

	@Override
	public void updateContact(Contact contact) {
		notNull(contact);
		contactDao.update(contact);
	}

	@Override
	public void deleteContact(long id) { contactDao.deleteById(id); }
	
	@Override
	public List<Contact> findContactByEmail(String email) {
		String emailWithWildcards = "%" + email + "%";
		return contactDao.getByEmailLike(emailWithWildcards);
	}
}
