<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<%@ include file="Banner.jsp"%>
	<div class="container">
		<h2>
			Bienvenido Admin <small>${sessionScope.sessionUser.nombre }&nbsp;${sessionScope.sessionUser.apellido }</small>
		</h2>
		<hr />
		<!--Horizantal line divider  <div class="page-header"/> -->
		<div class="row">
			<%@ include file="mostrarInfoMessage.jsp"%>
		</div>
	</div>
	<div class="container">
		<div>
			<c:url value="UsuarioServlet" var="url_admListarUsuarios">
				<c:param name="accionGET" value="admListarUsuarios" />
			</c:url>
			<a href="${url_admListarUsuarios}" class="btn btn-primary"
				data-toggle="tooltip" title="Listado en forma de grilla"> <span
				class="glyphicon glyphicon-ok-circle"></span> Listar Usuarios
			</a>
		</div>


		<div>
			<c:url value="UsuarioServlet" var="url_altaUsuarioAlumnoLoad">
				<c:param name="accionGET" value="altaUsuarioLoad" />
			</c:url>
			<a href="${url_altaUsuarioAlumnoLoad}" class="btn btn-warning"
				data-toggle="tooltip" title="Nuevo usuario"> Alta Usuario </a>
		</div>
		<div>
			<c:url value="MateriaCursoServlet" var="url_altaMateriaCursoLoad">
				<c:param name="accionGET" value="altaMateriaCursoLoad" />
			</c:url>
			<a href="${url_altaMateriaCursoLoad}" class="btn btn-default"
				data-toggle="tooltip" title="Dar de alta una materia/curso"> <span
				class="glyphicon glyphicon-ok-circle"></span> Alta Materia/Curso
			</a>
		</div>
		<div>
			<c:url value="MateriaCursoServlet" var="url_admListarCursos">
				<c:param name="accionGET" value="admListarCursos" />
			</c:url>
			<a href="${url_admListarCursos}" class="btn btn-info"> Listado de
				Materias/Cursos </a>
		</div>
	</div>

</body>
</html>