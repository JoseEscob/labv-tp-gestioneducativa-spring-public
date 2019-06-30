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
		<div class="row">
			<%@ include file="mostrarInfoMessage.jsp"%>
		</div>
	</div>
	<div class="container">
		<div>
			<a href="admListarUsuarios.html" class="btn btn-primary"
				data-toggle="tooltip" title="Listado en forma de grilla">&nbsp;Listar Usuarios
			</a>
		</div>
		<div>
			<a href="altaUsuarioLoad.html" class="btn btn-warning"
				data-toggle="tooltip" title="Dar de alta un Usuario">&nbsp;Alta Usuario
			</a>
		</div>
		<div>
			<a href="altaMateriaCursoLoad.html" class="btn btn-info"
				data-toggle="tooltip" title="Dar de alta una materia/curso">&nbsp;Alta Materia/Curso
			</a>
		</div>
		<div>
			<a href="admListarCursos.html" class="btn btn-info"
				data-toggle="tooltip" title="Dar de alta una materia/curso">&nbsp;Listar Materias/Cursos
			</a>
		</div>
	</div>

</body>
</html>