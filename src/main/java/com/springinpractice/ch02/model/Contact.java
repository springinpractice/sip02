package com.springinpractice.ch02.model;

import com.springinpractice.util.StringUtils;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class Contact {
	private Long id;
	private String lastName;
	private String firstName;
	private String middleInitial;
	private String email;
	
	public Long getId() { return id; }
	
	public void setId(Long id) { this.id = id; }
	
	public String getLastName() { return lastName; }
	
	public void setLastName(String lastName) {
		this.lastName = StringUtils.cleanup(lastName);
	}
	
	public String getFirstName() { return firstName; }
	
	public void setFirstName(String firstName) {
		this.firstName = StringUtils.cleanup(firstName);
	}
	
	public String getMiddleInitial() { return middleInitial; }
	
	public void setMiddleInitial(String mi) {
		this.middleInitial = StringUtils.cleanup(mi);
	}
	
	public String getEmail() { return email; }
	
	public void setEmail(String email) {
		this.email = StringUtils.cleanup(email);
	}
	
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
