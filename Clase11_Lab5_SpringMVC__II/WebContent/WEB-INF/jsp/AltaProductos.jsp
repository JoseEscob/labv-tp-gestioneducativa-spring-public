<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title></title>
	<style type="text/css">
	<%@ include file ="StyleSheet.css"%>
	</style>
	</head>
<body>

	<br />
	<h1>
		<center>Empresa de venta de articulos</center>
	</h1>
	<br />

	<%@include file="Menu.jsp"%>

	<div class="div-derecho">
		<div class="form">

			<h3>Agregar producto:</h3>

			<form action="altaProducto.html" method="post">

				<table>
					<tr>
						<td>ID Producto</td>
						<td>${IdProducto}</td>
					</tr>
					<tr>
						<td>Nombre</td>
						<td><input type="text" name="txtNombre"></td>
					</tr>
					<tr>
						<td>Precio</td>
						<td><input type="text" name="txtPrecio"></td>
					</tr>
					<tr>
						<td>IdTipo</td>
						<td><select name="tipoA" id="tipoArticulos">
								<c:forEach items="${listaTiposProductos}" var="item">
									<option value="${item.getId()}">${item.getNombre()}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" name="btnAceptar" value="Aceptar"></td>
					</tr>
				</table>

				<br />
				<br />
			</form>

		</div>
	</div>

	<div class="footer">&nbsp;</div>
</body>
</html>