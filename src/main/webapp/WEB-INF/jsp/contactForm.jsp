<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/jsp/urls.jspf" %>

<c:url var="formAction" value="${requestScope.action}" />

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Contact details</title>
	</head>
	<body>
		<ul id="breadcrumbs">
			<li><a href="${homeUrl}">Home</a></li>
			<li><a href="${contactListUrl}">Contacts</a></li>
		</ul>
			
		<h1 class="vcard icon">Contact details</h1>
		
		<form:form action="${formAction}" method="${requestScope.method}" modelAttribute="contact" cssClass="main">
			<form:errors>
				<div class="warning"><form:errors /></div>
			</form:errors>
			
			<div class="panel grid">
				<div class="gridRow yui-gf">
					<div class="fieldLabel yui-u first">First name:</div>
					<div class="yui-u">
						<div><form:input path="firstName" cssClass="short" /></div>
						<form:errors path="firstName">
							<div class="errorMessage"><form:errors path="firstName" /></div>
						</form:errors>
					</div>
				</div>
				<div class="gridRow yui-gf">
					<div class="fieldLabel yui-u first">Middle initial:</div>
					<div class="yui-u">
						<div><form:input path="middleInitial" cssClass="short" /></div>
						<form:errors path="middleInitial">
							<div class="errorMessage"><form:errors path="middleInitial" /></div>
						</form:errors>
					</div>
				</div>
				<div class="gridRow yui-gf">
					<div class="fieldLabel yui-u first">Last name:</div>
					<div class="yui-u">
						<div><form:input path="lastName" cssClass="short" /></div>
						<form:errors path="lastName">
							<div class="errorMessage"><form:errors path="lastName" /></div>
						</form:errors>
					</div>
				</div>
				<div class="gridRow yui-gf">
					<div class="fieldLabel yui-u first">E-mail:</div>
					<div class="yui-u">
						<div><form:input path="email" cssClass="medium" /></div>
						<form:errors path="email">
							<div class="errorMessage"><form:errors path="email" /></div>
						</form:errors>
					</div>
				</div>
				<div class="gridRow yui-gf">
					<div class="yui-u first"></div>
					<div class="yui-u">
						<input type="submit" value="Save" />
					</div>
				</div>
			</div>
		</form:form>
	</body>
</html>
