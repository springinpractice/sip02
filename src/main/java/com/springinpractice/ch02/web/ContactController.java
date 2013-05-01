/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch02.web;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springinpractice.ch02.model.Contact;
import com.springinpractice.ch02.service.ContactService;
import com.springinpractice.web.ResourceNotFoundException;

/**
 * Spring Web MVC controller exposing a RESTful interface for contact-related operations.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Controller
@RequestMapping(value = "/contacts")
public class ContactController {
	private static final Logger log = LoggerFactory.getLogger(ContactController.class);
	
	@Inject private ContactService contactService;
	
	@Value("#{viewNames.contactList}")
	private String contactListViewName;
	
	@Value("#{viewNames.contactForm}")
	private String contactFormViewName;
	
	@Value("#{viewNames.createContactSuccess}")
	private String createContactSuccessViewName;
	
	@Value("#{viewNames.updateContactSuccess}")
	private String updateContactSuccessViewName;
	
	@Value("#{viewNames.deleteContactSuccess}")
	private String deleteContactSuccessViewName;
	
	@Value("#{viewNames.contactSerp}")
	private String contactSerpViewName;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] {
			"firstName", "middleInitial", "lastName", "email"
		});
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String createContactForm(HttpServletRequest req, Model model) {
		prepareNewContactForm(req);
		model.addAttribute(new Contact());
		return contactFormViewName;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String createContact(
			HttpServletRequest req,
			HttpServletResponse res,
			@ModelAttribute @Valid Contact contact,
			BindingResult result) {
		
		if (!result.hasErrors()) {
			contactService.createContact(contact);
			
			// Correct RESTful semantics involves setting the status and location, but these will be overridden if the
			// createContactSuccessViewName issues a redirect.
			res.setStatus(HttpServletResponse.SC_CREATED);
			String location = req.getRequestURL() + "/" + contact.getId();
			log.debug("Setting Location={}", location);
			res.setHeader("Location", location);
			
			return createContactSuccessViewName;
		} else {
			prepareNewContactForm(req);
			result.reject("global.error");
			return contactFormViewName;
		}
	}

	/**
	 * Places a list containing all contacts on the passed model, and returns the logical view name for displaying the
	 * contact list.
	 * 
	 * @param model
	 *            model
	 * @return contact list view name
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getContacts(Model model) {
		model.addAttribute(contactService.getContacts());
		return contactListViewName;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchByEmail(@RequestParam("email") String email, Model model) {
		model.addAttribute(contactService.getContactsByEmail(email));
		return contactSerpViewName;
	}

	/**
	 * Places the requested contact on the passed model if it exists, and returns the logical view name for displaying
	 * the contact. Throws a {@link ResourceNotFoundException} and returns an HTTP 404 (NOT_FOUND) to the client if the
	 * request contact doesn't exist.
	 * 
	 * @param id
	 *            contact ID
	 * @param model
	 *            model
	 * @return contact view name
	 * @throws ResourceNotFoundException
	 *             if no such contact exists
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getContact(
			HttpServletRequest req,
			@PathVariable("id") long id,
			Model model) {
		
		Contact contact = contactService.getContact(id);
		if (contact != null) {
			prepareExistingContactForm(req, id);
			model.addAttribute(contactService.getContact(id));
			return contactFormViewName;
		} else {
			throw new ResourceNotFoundException("No such contact: " + id);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String updateContact(
			HttpServletRequest req,
			@PathVariable("id") Long id,
			@ModelAttribute @Valid Contact contact,
			BindingResult result) {
		
		contact.setId(id);
		
		if (!result.hasErrors()) {
			contactService.updateContact(contact);
			return updateContactSuccessViewName;
		} else {
			prepareExistingContactForm(req, id);
			result.reject("global.error");
			return contactFormViewName;
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteContact(@PathVariable("id") long id) {
		contactService.deleteContact(id);
		return deleteContactSuccessViewName;
	}
	
	
	// =================================================================================================================
	// Helper methods
	// =================================================================================================================
	
	private void prepareNewContactForm(HttpServletRequest req) {
		setActionAndMethod(req, "/contacts.html", "POST");
	}

	private void prepareExistingContactForm(HttpServletRequest req, long id) {
		setActionAndMethod(req, "/contacts/" + id + ".html", "PUT");
	}
	
	private void setActionAndMethod(HttpServletRequest req, String action, String method) {
		req.setAttribute("action", action);
		req.setAttribute("method", method);
	}
}
