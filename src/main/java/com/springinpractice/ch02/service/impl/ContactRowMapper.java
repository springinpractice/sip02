/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch02.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.springinpractice.ch02.model.Contact;

/**
 * @version $Id$
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Component
public class ContactRowMapper implements RowMapper<Contact> {

	@Override
	public Contact mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Contact contact = new Contact();
		contact.setId(resultSet.getLong(1));
		contact.setLastName(resultSet.getString(2));
		contact.setFirstName(resultSet.getString(3));
		contact.setMiddleInitial(resultSet.getString(4));
		contact.setEmail(resultSet.getString(5));
		return contact;
	}
}
