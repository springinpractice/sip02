package com.springinpractice.ch02.dao.jdbc;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.springinpractice.ch02.dao.ContactDao;
import com.springinpractice.ch02.model.Contact;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Repository
public class JdbcContactDao implements ContactDao {
	private static final String FIND_ALL_QUERY = "select id, last_name, first_name, mi, email from contact";
	
	@Inject private DataSource dataSource;

	@Override
	public void create(Contact t) {
		throw new UnsupportedOperationException("Not implemented");
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
		try {
			Connection c = dataSource.getConnection();
			try {
				Statement s = c.createStatement();
				try {
					ResultSet rs = s.executeQuery(FIND_ALL_QUERY);
					try {
						List<Contact> contacts = new ArrayList<Contact>();
						while (rs.next()) {
							Contact contact = new Contact();
							contact.setId(rs.getLong(1));
							contact.setLastName(rs.getString(2));
							contact.setFirstName(rs.getString(3));
							contact.setMiddleInitial(rs.getString(4));
							contact.setEmail(rs.getString(5));
							contacts.add(contact);
						}
						return contacts;
					} finally { rs.close(); }
				} finally { s.close(); }
			} finally { c.close(); }
		} catch (SQLException e) {
			// Don't know here what type of DataAccessException this would be, so just throw RuntimeException.
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Contact t) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public void delete(Contact t) {
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
		throw new UnsupportedOperationException("Not implemented");
	}
}
