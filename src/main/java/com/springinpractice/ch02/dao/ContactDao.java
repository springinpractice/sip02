/* 
 * Book web site   - http://www.manning.com/wheeler/
 * Book blog       - http://springinpractice.com/
 * Author web site - http://wheelersoftware.com/
 */
package com.springinpractice.ch02.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springinpractice.ch02.model.Contact;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public interface ContactDao extends JpaRepository<Contact, Long> {
	
	List<Contact> findByEmailLike(String email);
}
