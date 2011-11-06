/* 
 * Book web site   - http://www.manning.com/wheeler/
 * Book blog       - http://springinpractice.com/
 * Author web site - http://wheelersoftware.com/
 */
package com.springinpractice.ch02.service;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springinpractice.ch02.dao.ContactDao;
import com.springinpractice.ch02.model.Contact;


/**
 * Contact service bean.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Service
@Transactional(
	propagation = Propagation.REQUIRED,
	isolation = Isolation.DEFAULT,
	readOnly = true)
public class ContactServiceImpl implements ContactService {
	@Inject private ContactDao contactDao;

	/* (non-Javadoc)
	 * @see com.springinpractice.ch10.service.ContactService#createContact(com.springinpractice.ch10.model.Contact)
	 */
	@Transactional(
		propagation = Propagation.REQUIRED,
		isolation = Isolation.DEFAULT,
		readOnly = false)
	public void createContact(Contact contact) {
		notNull(contact);
		contactDao.create(contact);
	}

	/* (non-Javadoc)
	 * @see com.springinpractice.ch10.service.ContactService#getContacts()
	 */
	public List<Contact> getContacts() { return contactDao.getAll(); }

	/* (non-Javadoc)
	 * @see com.springinpractice.ch10.service.ContactService#getContact(long)
	 */
	public Contact getContact(long id) { return contactDao.get(id); }

	/* (non-Javadoc)
	 * @see com.springinpractice.ch10.service.ContactService#updateContact(com.springinpractice.ch10.model.Contact)
	 */
	@Transactional(
		propagation = Propagation.REQUIRED,
		isolation = Isolation.DEFAULT,
		readOnly = false)
	public void updateContact(Contact contact) {
		notNull(contact);
		contactDao.update(contact);
	}

	/* (non-Javadoc)
	 * @see com.springinpractice.ch10.service.ContactService#deleteContact(long)
	 */
	@Transactional(
		propagation = Propagation.REQUIRED,
		isolation = Isolation.DEFAULT,
		readOnly = false)
	public void deleteContact(long id) { contactDao.deleteById(id); }
}
