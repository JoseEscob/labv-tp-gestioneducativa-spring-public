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
	<%@ include file="Banner.jsp"%>
	<div class="container">
		<h2>Lista de Usuarios</h2>
		<hr />
		<div class="row">
			<%@ include file="mostrarInfoMessage.jsp"%>
		</div>
	</div>
	<div class="container">

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
						<td><fmt:formatDate value="${objUsuario.fechaNac}"
								pattern="yyyy-MM-dd" /></td>
						<td>${objUsuario.dni}</td>
						<td>${objUsuario.mail}</td>
						<td>${objUsuario.nroTelefono}</td>
						<td>${objUsuario.calleNombre}&nbsp;${objUsuario.calleAltura}</td>
						<td>${objUsuario.objTipoUsuario.descripcion}</td>
						<td>
							<div>
								<form action="eliminarUsuario.html" method="POST">
									<input type="hidden" value="${objUsuario.idUsuario}"
										name="idUsuarioToDelete" /> <input type="submit"
										value="Eliminar" class="btn btn-danger" />
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
							
							<div>
								<a href="<c:url value='/select-user-${objUsuario.idUsuario}' />" class="btn btn-primary" >
									Ver Perfil
								</a>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>