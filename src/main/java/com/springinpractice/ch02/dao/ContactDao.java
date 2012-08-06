package com.springinpractice.ch02.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.springinpractice.ch02.model.Contact;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public interface ContactDao extends CrudRepository<Contact, Long> {
	
	List<Contact> findByEmailLike(String email);
}
