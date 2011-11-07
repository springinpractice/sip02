<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/jsp/urls.jspf" %>

<c:url var="contactsJsUrl" value="/scripts/contacts.js" />
<c:url var="searchByEmailUrl" value="/contacts/search.html" />

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Search results for e-mail like "<c:out value="${param.email}" />"</title>
		<script type="text/javascript" src="${contactsJsUrl}"></script>
	</head>
	<body>
		<ul id="breadcrumbs">
			<li><a href="${homeUrl}">Home</a></li>
			<li><a href="${contactListUrl}">Contacts</a></li>
		</ul>
	
		<h1>Search results for e-mail like "<c:out value="${param.email}" />"</h1>
		
		<c:choose>
			<c:when test="${empty contactList}">
				<p>No contacts found.</p>
			</c:when>
			<c:otherwise>
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
		
		<p>&laquo; Back to <a href="${contactListUrl}">contacts</a></p>
	</body>
</html>
