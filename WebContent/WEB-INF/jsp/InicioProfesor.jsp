<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

</head>
<body>
	<%@ include file="Banner.jsp"%>
	<div class="container">
		<h2>
			Bienvenido Profesor <small>${sessionScope.sessionUser.nombre }&nbsp;${sessionScope.sessionUser.apellido }</small>
		</h2>
		<hr />
		<div class="row">
			<%@ include file="mostrarInfoMessage.jsp"%>
		</div>
	</div>
	<div class="container">

		<div>
			<c:url value="CalificacionServlet" var="url_altaCalificacionLoad">
				<c:param name="accionGET" value="altaCalificacionLoad" />
			</c:url>
			<a href="${url_altaCalificacionLoad}" class="btn btn-warning"
				data-toggle="tooltip" title="Nuevo usuario"> Alta Calificacion </a>
		</div>

		<div>
			<c:url value="MateriaCursoServlet" var="url_listarCursosProfe">
				<c:param name="accionGET" value="listarCursosProfe" />
			</c:url>
			<a href="${url_listarCursosProfe}" class="btn btn-info"> Listado
				de Materias/Cursos distados por mí</a>
		</div>
	</div>
</body>
</html>