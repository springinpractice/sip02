/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch02.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.springinpractice.util.StringUtils;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Entity
@Table(name = "contact")
@NamedQuery(
	name = "findContactsByEmail",
	query = "from Contact where email like :email")
public class Contact {
	private Long id;
	private String lastName;
	private String firstName;
	private String middleInitial;
	private String email;
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() { return id; }
	
	public void setId(Long id) { this.id = id; }
	
	@NotNull
	@Length(min = 1, max = 40)
	@Column(name = "last_name")
	public String getLastName() { return lastName; }
	
	public void setLastName(String lastName) {
		this.lastName = StringUtils.cleanup(lastName);
	}
	
	@NotNull
	@Length(min = 1, max = 40)
	@Column(name = "first_name")
	public String getFirstName() { return firstName; }
	
	public void setFirstName(String firstName) {
		this.firstName = StringUtils.cleanup(firstName);
	}
	
	@Length(max = 1)
	@Column(name = "mi")
	public String getMiddleInitial() { return middleInitial; }
	
	public void setMiddleInitial(String mi) {
		this.middleInitial = StringUtils.cleanup(mi);
	}
	
	@Email
	@Column
	public String getEmail() { return email; }
	
	public void setEmail(String email) {
		this.email = StringUtils.cleanup(email);
	}
	
	@Transient
	public String getFullName() {
		String fullName = lastName + ", " + firstName;
		if (! (middleInitial == null || "".equals(middleInitial.trim()))) {
			fullName += " " + middleInitial + ".";
		}
		return fullName;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[Contact: id=" + id
			+ ", firstName=" + firstName
			+ ", middleInitial=" + middleInitial
			+ ", lastName=" + lastName
			+ ", email=" + email
			+ "]";
	}
}
