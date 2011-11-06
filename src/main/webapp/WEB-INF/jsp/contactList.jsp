<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/jsp/urls.jspf" %>

<c:url var="contactsJsUrl" value="/scripts/contacts.js" />

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>My Contacts</title>
		<script type="text/javascript" src="${contactsJsUrl}"></script>
	</head>
	<body>
		<ul id="breadcrumbs">
			<li><a href="${homeUrl}">Home</a></li>
		</ul>
	
		<h1>My Contacts</h1>
		
		<c:if test="${param.saved}">
			<div class="info alert">Contact saved.</div>
		</c:if>
		<c:if test="${param.deleted}">
			<div class="info alert">Contact deleted.</div>
		</c:if>
		
		<c:choose>
			<c:when test="${empty contactList}">
				<p>Your contact list is empty. <a href="${newContactUrl}">Create a new contact.</a></p>
			</c:when>
			<c:otherwise>
				<div class="tableActionBar">
					${fn:length(contactList)} contacts |
					<span class="vcardAdd icon"><a href="${newContactUrl}">Create new contact</a></span>
				</div>
				<table id="contactList" class="sortable">
					<thead>
						<tr>
							<th>Name</th>
							<th>E-mail</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="contact" items="${contactList}">
							<c:url var="contactUrl" value="/contacts/${contact.id}.html" />
							<tr id="contact-${contact.id}">
								<td><span class="vcard icon"><a href="${contactUrl}">${contact.fullName}</a></span></td>
								<td><c:if test="${not empty contact.email}"><span class="email icon"><a href="mailto:${contact.email}">${contact.email} </a></span></c:if></td>
								<td>
									<span class="vcardDelete icon"><a class="deleteContact" href="#">Delete</a></span>
									<form class="deleteForm" action="${contactUrl}" method="POST">
										<input type="hidden" name="_method" value="DELETE" />
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</body>
</html>
