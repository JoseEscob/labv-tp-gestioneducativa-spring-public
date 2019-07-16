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
		<div class="row col-md-12">
			<div class="card text-center col-md-6">
				<img class="card-img-top" src="<%=ConstantesJSP.imgMenuMaterias%>"
					alt="ImagenMateria" height="250" width="100">
				<div class="card-body">
					<h5 class="card-title">Materias / Cursos</h5>
					<p class="card-text">Organización de materias</p>
					<!--<div class="col-md-12 form-group row">-->
					<div>
						<a href="listarMateriasCursosProfe.html" class="btn btn-info"
							data-toggle="tooltip" title="Listado de materias/cursos">&nbsp;Listar
							Materias/Cursos-PROFE </a>
					</div>
					<!--<div class="col-md-6"> </div>-->
					<!--<div class="col-md-6"> </div>-->
					<!--</div>-->

				</div>
			</div>

		</div>
	</div>
	<div class="container">
		<br>
	</div>
</body>
</html>