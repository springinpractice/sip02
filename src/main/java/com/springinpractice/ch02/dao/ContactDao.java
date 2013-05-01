/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch02.dao;

import java.util.List;

import com.springinpractice.ch02.model.Contact;
import com.springinpractice.dao.Dao;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public interface ContactDao extends Dao<Contact> {
	
	List<Contact> findByEmail(String email);
}
