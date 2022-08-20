<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!-- librería JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!-- Libreria Spring Form -->
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>registration</title>
</head>
<body>
	<h3>Registration - User!!</h3>
	
	<form:form name="" method="post" modelAtrribute="userVo">
		USERNAME:<form:input type="text" path="username"/>
		<form:errors path="username"/>
		<br>
				
		PASSWORD<form:input type="password" path="password"/>
		<form:errors path="password"/>
		<br>
		
		NOMBRE:<form:input type="text" path="nombre"/>
		<form:errors path="nombre"/>
		<br>
		
		APELLIDO:<form:input type="text" path="apellido"/>
		<form:errors path="apellido"/>
		<br>
		
		EMAIL:<form:input type="text" path="email"/>
		<form:errors path="email"/>
		<br>
			
		CELULAR<form:input type="text" path="celular"/>
		<form:errors path="celular"/>
		<br>
		
		ROLES:
		<form:select path="itemsRole" items="${bRoles}" itemValue="roleId" itemLabel="type" mutiple="true">
		</form:select>
		<form:errors path="itemsRole"/>
		<br><br>
		
		<button type="submit">Guardar</button>
		<button type="button" onclick="location.href='<c:url value="/admin"/>'">Cancelar</button>
		
	
	</form:form>
	
</body>
</html>