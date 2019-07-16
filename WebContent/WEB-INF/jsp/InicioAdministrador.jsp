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
		<div class="row col-md-12">
			<div class="card text-center col-md-6">
				<img class="card-img-top" src="<%=ConstantesJSP.imgMenuUsuarios%>"
					alt="ImagenMateria" height="250" width="100">
				<div class="card-body">
					<h5 class="card-title">Usuarios</h5>
					<p class="card-text">Gestione los datos de los usuarios</p>

					<div class="col-md-12 form-group row">
						<div class="col-md-6">
							<div>
								<a href="admListarUsuarios.html" class="btn btn-info"
									data-toggle="tooltip" title="Listado en forma de grilla">&nbsp;Listar
									Usuarios </a>
							</div>
						</div>
						<div class="col-md-6">
							<div>
								<a href="altaUsuarioLoad.html" class="btn btn-primary"
									data-toggle="tooltip" title="Dar de alta un Usuario">&nbsp;Alta
									Usuario </a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="card text-center col-md-6">
				<img class="card-img-top" src="<%=ConstantesJSP.imgMenuMaterias%>"
					alt="ImagenMateria" height="250" width="100">
				<div class="card-body">
					<h5 class="card-title">Materias / Cursos</h5>
					<p class="card-text">Organización de materias</p>
					<div class="col-md-12 form-group row">
						<div class="col-md-6">
							<div>
								<a href="cargarMateriasUsuarioLoad.html" class="btn btn-info"
									data-toggle="tooltip" title="Dar de alta una materia/curso">&nbsp;Listar
									Materias/Cursos</a>
							</div>
						</div>
						<div class="col-md-6">
							<div>
								<a href="altaMateriaCursoLoad.html" class="btn btn-primary"
									data-toggle="tooltip" title="Dar de alta una materia/curso">&nbsp;Alta
									Materia/Curso </a>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<br>
	</div>
	<div class="container">
		<div class="row col-md-12">
			<div class="card text-center col-md-6">
				<img class="card-img-top" src="<%=ConstantesJSP.imgMenuTipoExamen%>"
					alt="ImagenMateria" height="250" width="100">
				<div class="card-body">
					<h5 class="card-title">Tipos de Examen</h5>
					<p class="card-text">Gestione y personalice los Tipos de
						Examenes utilizados en el sistema</p>
					<div>
						<a href="listarTipoExamen.html" class="btn btn-info"
							data-toggle="tooltip" title="Listado de Tipos de Examen">&nbsp;Listar/
							Gestionar Tipos de Examen </a>
					</div>
				</div>
			</div>
			<div class="card text-center col-md-6">
				<img class="card-img-top"
					src="<%=ConstantesJSP.imgMenuTipoPeriodo%>" alt="ImagenMateria"
					height="250" width="100">
				<div class="card-body">
					<h5 class="card-title">Tipos de Periodo</h5>
					<p class="card-text">Gestione y personalice los Tipos de
						Periodo utilizados en el sistema</p>
					<div>
						<a href="listarTipoPeriodo.html" class="btn btn-info"
							data-toggle="tooltip" title="Listado de Tipos de Periodo">&nbsp;Listar/
							Gestionar Tipos de Periodo </a>
					</div>
				</div>
			</div>

		</div>
	</div>
</body>
</html>