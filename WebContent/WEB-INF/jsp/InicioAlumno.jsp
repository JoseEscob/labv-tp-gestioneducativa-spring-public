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
			Bienvenido Alumno <small>${sessionScope.sessionUser.nombre }&nbsp;${sessionScope.sessionUser.apellido }</small>
		</h2>
		<hr />
		<!--Horizantal line divider  <div class="page-header"/> -->
		<div class="row">
			<%@ include file="mostrarInfoMessage.jsp"%>
		</div>
	</div>
	<div class="container">
		<div>
			<c:url value="MateriaCursoServlet" var="url_listarCursosAlumno">
				<c:param name="accionGET" value="listarCursosAlumno" />
			</c:url>
			<a href="${url_listarCursosAlumno}" class="btn btn-info"> Listado
				de mis Materias/Cursos</a>
		</div>
	</div>
</body>
</html>