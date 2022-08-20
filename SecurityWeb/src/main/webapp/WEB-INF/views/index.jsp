<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!-- librería JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- libreria Spring Security -->
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>index</title>
</head>
<body>
	<h3>Hello World!</h3>
	
	<!-- si el usuario está autenticado (en sesión) -->
	<security:authorize access="isAuthenticated()">
	
		<!-- 
		<security:authentication property="principal.username" var="username"/> 
		<security:authentication property="principal.authorities" var="authorities"/> --> 
		
		Bienvenido <b>${var_username}</b> <br>
		Rol(es): <b>${var_authorities}</b> <br><br>
		
		<security:authorize access="hasRole('USER')">
			<a href="<c:url value='/user'/>">Go to USER</a> | 
		</security:authorize>
		
		<security:authorize access="hasRole('ADMIN')">
			<a href="<c:url value='/admin'/>">Go to ADMIN</a> | 
		</security:authorize>
		
		<security:authorize access="hasRole('DBA')">
			<a href="<c:url value='/dba'/>">Go to DBA</a> | 
		</security:authorize>
	
		<a href="<c:url value='/logout'/>">Logout</a>
	</security:authorize>
	
	<!-- si el usuario es anónimo (no está en sesión) -->
	<security:authorize access="isAnonymous()">
		<a href="<c:url value='/login'/>">Login</a>
	</security:authorize>
	
</body>
</html>








