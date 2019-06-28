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
	
	<h3>Listado de Productos</h3>

	<table border="1px">
		<thead>
			<tr>
				<th>Id Producto</th>
				<th>Nombre</th>
				<th>Precio</th>
				<th>Id Tipo</th>
				<th>Descripcion Tipo</th>
			</tr>
		</thead>
		
			<c:forEach items="${listaProductos}" var="item">
				
				<tr>
			
				<td> ${item.getIdProducto()}</label> </td>
				<td>${item.getNombre()}</td>
				<td>${item.getPrecio()}</td>
				<td>${item.getTipo().getId()}</td>
				<td>${item.getTipo().getNombre()}</td>

				</tr>
				
			</c:forEach>
	
	</table>
	</div>
	</div>
	


</body>
</html>