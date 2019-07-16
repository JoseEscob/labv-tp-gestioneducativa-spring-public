<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Gestión de Materia</title>
</head>
<body>
	<%@ include file="Banner.jsp"%>
	<div class="container">
		<h2>Menú de gestión de Materia</h2>
		<hr />
		<div class="row">
			<%@ include file="mostrarInfoMessage.jsp"%>
		</div>
	</div>
	<div class="container">
		<div class="row col-md-12">
			<%@ include file="_frmMateriaCabecera.jsp"%>
		</div>
		<div class="row col-md-12">
			<div class="card text-center col-md-6">
				<img class="card-img-top" src="<%=ConstantesJSP.imgMenuCalificaciones%>" alt="ImagenMateria" height="250" width="100">
				<div class="card-body">
					<h5 class="card-title">Calificaciones</h5>
					<p class="card-text">Vea las calificaciones de su materias</p>
					<div>
						<ul class="list-group list-group-flush">
							<li class="list-group-item">
								<form action="altaCalificacionLoad.html" method="POST">
									<input type="hidden" value="${objCurso.idCurso}"
										name="idCursoToViewCalificaciones" /> <input type="submit"
										value="Calificar a un Alumno" class="btn btn-primary" />
								</form>
							</li>
							<li class="list-group-item">
								<form action="calificacionListadoProfeLoad.html" method="POST">
									<input type="hidden" value="${objCurso.idCurso}"
										name="idCursoToViewCalificaciones" /> <input type="submit"
										value="Ver/ Gestionar calificaciones" class="btn btn-info" />
								</form>
							</li>
							<li class="list-group-item">
								<form action="modificarCalificacionMasivaLoad.html"
									method="POST">
									<input type="hidden" value="${objCurso.idCurso}"
										name="idCursoToViewCalificaciones" /> <input type="submit"
										value="Modificar calificaciones" class="btn btn-warning" />
								</form>
							</li>
							<li class="list-group-item">
								<form action="altaCalificacionMasivaLoad.html" method="POST">
									<input type="hidden" value="${objCurso.idCurso}"
										name="idCursoToViewCalificaciones" /> <input type="submit"
										value="Calificar Masivamente" class="btn btn-danger" />
								</form>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="card text-center col-md-6">
				<img class="card-img-top" src="<%=ConstantesJSP.imgMenuMaterias%>" alt="ImagenMateria" height="250" width="100"> 
				<div class="card-body">
					<h5 class="card-title">Materias / Cursos</h5>
					<p class="card-text">Organización de materias</p>

					<div>
						<ul class="list-group list-group-flush">
							<li class="list-group-item">
								<form action="inscripcionAlumnoLoad.html" method="POST">
									<input type="hidden" value="${objCurso.idCurso}" name="idCurso" />
									<input type="submit" value="Inscripción"
										class="btn btn-primary" />
								</form>
							</li>
							<li class="list-group-item">
								<form action="modificarMateriaCursoLoad.html" method="POST">
									<input type="hidden" value="${objCurso.idCurso}" name="idCurso" />
									<input type="submit" value="Modificar Curso"
										class="btn btn-warning" />
								</form>
							</li>
							<li class="list-group-item">
								<form action="bajaAlumnoMateriaCurso.html" method="POST">
									<div class="col-md-12 form-group row">
										<div class="col-md-2">
											<label>DNI Alumno a dar de baja</label>
										</div>
										<div class="col-md-6">
											<input type="hidden" value="${objCurso.idCurso}"
												name="idCurso" /> <input type="text" name="dniAlumno"
												class="form-control" onkeypress="return soloNros(event)"
												maxlength="10" />
										</div>
										<div class="col-md-1">
											<input type="submit" value="Dar de baja a este alumno"
												class="btn btn-danger" onclick="return ConfirmDelete();" />
										</div>
									</div>
								</form>
							</li>
							<li class="list-group-item">
								<form action="eliminarMateriaCurso.html" method="POST">
									<input type="hidden" value="${objCurso.idCurso}" name="idCurso" />
									<input type="submit" value="Eliminar Curso"
										class="btn btn-danger" />
								</form>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="row col-md-12"></div>
	</div>
	<script>
		function ConfirmDelete() {
			var dniIngresado = document.getElementsByName('dni')[0].value;
			var mensajeConfirmacion = "¿Está seguro de dar de baja al alumno con DNI "
					+ dniIngresado + " ?";
			if (confirm(mensajeConfirmacion))
				return true;
			else
				return false;
		}
	</script>
</body>
</html>