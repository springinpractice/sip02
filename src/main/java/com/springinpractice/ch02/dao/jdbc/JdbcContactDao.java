package com.springinpractice.ch02.dao.jdbc;

import static org.springframework.util.Assert.notNull;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.springinpractice.ch02.dao.ContactDao;
import com.springinpractice.ch02.model.Contact;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Repository
public class JdbcContactDao implements ContactDao {
	private static final String CREATE_SQL =
			"insert into contact (last_name, first_name, mi, email) values (:lastName, :firstName, :mi, :email)";
	private static final String FIND_ALL_SQL =
			"select id, last_name, first_name, mi, email from contact";
	private static final String FIND_ALL_BY_EMAIL_LIKE_SQL =
			"select id, last_name, first_name, mi, email from contact where email like :email";
	
	@Inject private NamedParameterJdbcOperations jdbcTemplate;
	@Inject private ContactRowMapper contactRowMapper;

	@Override
	public void create(Contact contact) {
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
	public Contact get(Serializable id) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public Contact load(Serializable id) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public List<Contact> getAll() {
		return jdbcTemplate.query(FIND_ALL_SQL, new HashMap<String, Object>(), contactRowMapper);
	}

	@Override
	public void update(Contact contact) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public void delete(Contact contact) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public void deleteById(Serializable id) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public void deleteAll() {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public long count() {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public boolean exists(Serializable id) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public List<Contact> getByEmailLike(String email) {
		notNull(email, "email can't be null");
		SqlParameterSource params = new MapSqlParameterSource("email", "%" + email + "%");
		return jdbcTemplate.query(FIND_ALL_BY_EMAIL_LIKE_SQL, params, contactRowMapper);
	}
}
