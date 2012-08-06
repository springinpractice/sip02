package com.springinpractice.ch02.dao;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.springinpractice.ch02.model.Contact;
import com.springinpractice.dao.hibernate.AbstractHbnDao;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Repository
public class HbnContactDao extends AbstractHbnDao<Contact> implements ContactDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<Contact> findByEmail(String email) {
		notNull(email, "email can't be null");
		return getSession()
			.getNamedQuery("findContactsByEmail")
			.setString("email", email)
			.list();
	}
}
