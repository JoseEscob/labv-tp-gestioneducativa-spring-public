<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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

			<h3>Agregar categoria:</h3>

			<form action="altaCategoria.html" method="post">

				<table>
					<tr>
						<td>IdCategoria</td>
						<td>${IdCategoria}</td>
					</tr>
					<tr>
						<td>Nombre</td>
						<td><input type="text" name="txtNombre"></td>
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