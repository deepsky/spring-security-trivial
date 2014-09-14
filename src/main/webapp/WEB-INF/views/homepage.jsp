<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<html>
<head></head>

<body>
	<h1>SHARED PAGE</h1>

<p>
Your principal object is....: <%= request.getUserPrincipal() %>
</p>

	<security:authorize access="hasRole('ROLE_USER')">
		This text is only visible to a user
		<br />
	</security:authorize>

	<security:authorize access="hasRole('ROLE_ADMIN')">
		This text is only visible to an admin
		<br />
	    <a href="<c:url value="/admin/console" />">Go to Home Page</a>
		<br />
	</security:authorize>

	<security:authorize access="hasRole('ROLE_MODERATOR')">
		This text is only visible to a moderator
		<br />
	    <a href="<c:url value="/mod/moderator" />">Go to Home Page</a>
		<br />
	</security:authorize>

	<a href="<c:url value="/j_spring_security_logout" />">Logout</a>

</body>
</html>