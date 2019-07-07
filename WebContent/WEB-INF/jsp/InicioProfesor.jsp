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
			<a href="altaCalificacionLoad.html" class="btn btn-primary"
				data-toggle="tooltip" title="Calificar">&nbsp;Alta Calificación
			</a>
		</div>
		<div>
			<a href="listarMateriasCursosProfe.html" class="btn btn-info"
				data-toggle="tooltip" title="Dar de alta una materia/curso">&nbsp;Listar
				Materias/Cursos-PROFE </a>
		</div>
	</div>
</body>
</html>