<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>login</title>
</head>
<body>
	<h3>Hello World!</h3>
	
	<c:url value="/login" var="loginURL"/>
	
	<form name="" method="post" action="${loginURL}">
		<!-- token de seguridad -->
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		
		<!-- si se accede con credenciales erróneas -->
		<c:if test="${param.error != null}">
			<p style="color:red;">¡Error, acceso denegado!</p>
		</c:if>
		
		<!-- si se cierra sesión -->
		<c:if test="${param.logout != null}">
			<p style="color:green;">¡Usted se ha desconectado!</p>
		</c:if>
		
		Username: <input type="text" name="username"/> <br>
		Password: <input type="password" name="password"/> <br><br>
		
		<button type="submit">Iniciar sesión</button> | 
		<a href="<c:url value='/index'/>">Index</a>
	</form>
</body>
</html>





