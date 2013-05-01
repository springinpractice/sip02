/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch02.service;

import java.util.List;

import com.springinpractice.ch02.model.Contact;


/**
 * Contact service interface.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public interface ContactService {

	/**
	 * Creates the given contact in the persistent store.
	 * 
	 * @param contact
	 *            contact to create
	 * @throws IllegalArgumentException
	 *             if <code>contact</code> is <code>null</code>
	 */
	void createContact(Contact contact);

	/**
	 * Returns a list containing all contacts. Returns an empty list if there aren't any contacts.
	 * 
	 * @return list of all contacts
	 */
	List<Contact> getContacts();
	
	List<Contact> getContactsByEmail(String email);

	/**
	 * Returns the contact having the given ID, or <code>null</code> if no such contact exists.
	 * 
	 * @param id
	 *            contact ID
	 * @return contact having the given ID
	 */
	Contact getContact(Long id);
	
	void updateContact(Contact contact);

	/**
	 * Deletes the contact having the given ID.
	 * 
	 * @param id
	 *            contact ID
	 */
	void deleteContact(Long id);
}
