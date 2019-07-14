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
		<div class="row">
			<div class="row col-md-12 form-group">
				<h4>Filtros</h4>
			</div>
			<div class="row col-md-12">
				<div class="col-md-3 form-group">
					<form method="POST" action="listaUsuariosByTipoUsuario.html">
						<label>Tipo de Usuario </label>
						<div class="input-group">
							<select name="idTipoUsuarioBuscado" class="form-control border">
								<c:forEach items="${listaTipoUsuarios}" var="item">
									<option value="${item.idTipoUsuario}">${ item.descripcion}</option>
								</c:forEach>
							</select> <span class="input-group-append"> <input type="submit"
								class="btn btn-primary border" value="Filtrar"></input>
							</span>
						</div>
					</form>
				</div>
				<div class="col-md-3 form-group pull-right">
					<form method="POST" action="listaUsuariosByDNI.html">
						<label>Buscador de Usuario (Por DNI)</label>
						<div class="input-group">
							<input class="form-control border" type="search"
								name="txtDNIBuscado" placeholder="Nro de DNI (solo números)"
								onkeypress="return soloNros(event)" maxlength="10" /> <span
								class="input-group-append">
								<button class="btn btn-outline-secondary border" type="submit">
									<i class="fa fa-search"></i>
								</button>
							</span>
						</div>
					</form>
				</div>
				<div class="col-md-6 form-group"></div>
			</div>
		</div>
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
								<a
									href="<c:url value='/modificar-user-${objUsuario.idUsuario}' />"
									class="btn btn-primary"> Ver-Modificar </a>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>