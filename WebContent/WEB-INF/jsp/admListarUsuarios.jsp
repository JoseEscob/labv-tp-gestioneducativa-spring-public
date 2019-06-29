<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>

<title>Lista de Usuarios</title>
</head>
<body>
	<%@ include file="Menu.jsp"%>

	<!-- 	<div class="container"> -->
	<div class="div-derecho">

		<table id="grdUsuarios" class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>idUsuario</th>
					<th>nombre</th>
					<th>apellido</th>
					<th>FechaNacimiento</th>
					<th>dni</th>
					<th>mail</th>
					<th>nroTelefono</th>
					<th>direccion</th>
					<!-- <th>idTipoUsuario</th> -->
					<th>TipoUsuario</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listaUsuarios}" var="objUsuario">
					<tr>
						<td>${objUsuario.idUsuario}</td>
						<td>${objUsuario.nombre}</td>
						<td>${objUsuario.apellido}</td>
						<!--<td>${objUsuario.fechaNac}</td>-->
						<td><fmt:formatDate value="${objUsuario.fechaNac}"
								pattern="yyyy-MM-dd" /></td>
						<td>${objUsuario.dni}</td>
						<td>${objUsuario.mail}</td>
						<td>${objUsuario.nroTelefono}</td>
						<td>${objUsuario.calleNombre}&nbsp;${objUsuario.calleAltura}</td>
						<td>${objUsuario.objTipoUsuario.descripcion}</td>
						<td>
							<div>
								<form action="UsuarioServlet" method="POST">
									<input type="hidden" value="${objUsuario.idUsuario}"
										name="idUsuarioToDelete" /> <input type="hidden"
										name="accionPOST" value="eliminarUsuario"></input> <input
										type="submit" value="Eliminar" class="btn btn-danger" />
								</form>
							</div>
							<div>
								<form action="UsuarioServlet" method="POST">
									<input type="hidden" value="${objUsuario.idUsuario}"
										name="idUsuarioToUpdate" /> <input type="hidden"
										name="accionPOST" value="modificarUsuarioLoad"></input> <input
										type="submit" value="Modificar" class="btn btn-info" />
								</form>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>