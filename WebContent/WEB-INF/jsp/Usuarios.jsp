<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	<%@include file="StyleSheet.css" %>   
</style>
</head>
<body>

<br/>
<h1><center> Empresa de venta de articulos </center></h1>
<br/>

<%@include file="Menu.jsp"%> 

<div class="div-derecho">
<div class="form">

<h3>Agregar usuarios:</h3>


<form action="altaUsuario.html" method="post" >
	<table>
	<tr> <td> Usuario:  </td> <td> <input name="nombreU"/> </td></tr>
	<tr>  <td> Contraseña: </td> <td>  <input name="passU"/> </td></tr>
	<tr>  <td> </td> <td> <input type="submit" name="btnAceptar" value="Aceptar"> </td></tr>
	</table>
</form>


${Mensaje}

<br/><br/><br/>


 <a href="recargaGrillaUsuarios.html">Recargar Grillas</a>


<h3>Listado de Usuarios</h3>

	<table border="1px">
		<thead>
			<tr>
				<th>Id Usuario</th>
				<th>Usuario</th>
				<th>Contraseña</th>
			</tr>
		</thead>
		
			<c:forEach items="${listaUsuarios}" var="item">
				
				<tr>
			
				<td> ${item.id}</label> </td>
				<td>${item.nombreU}</td>
				<td>${item.passU}</td>
				<td><a href="<c:url value='/delete-user-${item.id}' />"  >delete</a></td>
				</tr>
				
			</c:forEach>
	
	</table>
	</div>
	</div>
</body>
</html>