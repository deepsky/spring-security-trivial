<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<html>
<head></head>

<body>
	<h1>This is the homepage for Moderator</h1>

    <p>Your principal object is....: <%= request.getUserPrincipal() %></p>

    <br/>
	<a href="<c:url value="/homepage" />">Shared Page</a>
    <br/>
	<a href="<c:url value="/j_spring_security_logout" />">Logout</a>

</body>
</html>