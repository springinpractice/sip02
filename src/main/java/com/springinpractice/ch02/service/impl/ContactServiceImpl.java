/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch02.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.springinpractice.ch02.model.Contact;
import com.springinpractice.ch02.service.ContactService;


/**
 * Contact service bean.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Service
public class ContactServiceImpl implements ContactService {
	private static final String CREATE_SQL =
			"insert into contact (last_name, first_name, mi, email) values (:lastName, :firstName, :mi, :email)";
	private static final String FIND_ALL_SQL =
			"select id, last_name, first_name, mi, email from contact";
	private static final String FIND_ALL_BY_EMAIL_LIKE_SQL =
			"select id, last_name, first_name, mi, email from contact where email like :email";
	private static final String FIND_ONE_SQL =
			"select id, last_name, first_name, mi, email from contact where id = :id";
	private static final String UPDATE_SQL =
			"update contact set last_name = :lastName, first_name = :firstName, mi = :mi, email = :email " +
			"where id = :id";
	private static final String DELETE_SQL =
			"delete from contact where id = :id";
	
	@Inject private NamedParameterJdbcOperations jdbcTemplate;
	@Inject private ContactRowMapper contactRowMapper;

	@Override
	public void createContact(Contact contact) {
		notNull(contact, "contact can't be null");
		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("lastName", contact.getLastName())
			.addValue("firstName", contact.getFirstName())
			.addValue("mi", contact.getMiddleInitial())
			.addValue("email", contact.getEmail());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(CREATE_SQL, params, keyHolder);
		contact.setId(keyHolder.getKey().longValue());
	}

	@Override
	public List<Contact> getContacts() {
		return jdbcTemplate.query(FIND_ALL_SQL, new HashMap<String, Object>(), contactRowMapper);
	}
	
	@Override
	public List<Contact> getContactsByEmail(String email) {
		notNull(email, "email can't be null");
		SqlParameterSource params = new MapSqlParameterSource("email", "%" + email + "%");
		return jdbcTemplate.query(FIND_ALL_BY_EMAIL_LIKE_SQL, params, contactRowMapper);
	}

	@Override
	public Contact getContact(Long id) {
		notNull(id, "id can't be null");
		SqlParameterSource params = new MapSqlParameterSource("id", id);
		return jdbcTemplate.queryForObject(FIND_ONE_SQL, params, contactRowMapper);
	}

	@Override
	public void updateContact(Contact contact) {
		notNull(contact, "contact can't be null");
		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("id", contact.getId())
			.addValue("lastName", contact.getLastName())
			.addValue("firstName", contact.getFirstName())
			.addValue("mi", contact.getMiddleInitial())
			.addValue("email", contact.getEmail());
		jdbcTemplate.update(UPDATE_SQL, params);
	}

	@Override
	public void deleteContact(Long id) {
		notNull(id, "id can't be null");
		jdbcTemplate.update(DELETE_SQL, new MapSqlParameterSource("id", id));
	}
}
