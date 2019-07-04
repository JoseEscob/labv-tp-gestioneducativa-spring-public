<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Calificacion - Listado</title>
</head>
<body>
	<%@ include file="Banner.jsp"%>
	<div class="container">
		<h2>Calificaciones</h2>
		<hr />
		<div class="row">
			<%@ include file="mostrarInfoMessage.jsp"%>
		</div>
	</div>

	<div class="container">
		<!-- Tabla de calificaciones - Cabecera Página -->
		<div class="row">
			<div class="row col-md-12">
				<div class="col-md-6">
					<h3>${objCurso.nombreCurso}</h3>
				</div>
				<div class="col-md-6 pull-right">
					<h4>Nro de Curso: ${objCurso.idCurso}</h4>
				</div>
			</div>
			<div class="row col-md-12">
				<div class="col-md-6">
					<h3>
						<small> <br> <label>Año: ${objCurso.anio}</label> <br>
							<label>Periodo:
								${objCurso.getObjTipoPeriodo().descripcion}</label>
						</small>
					</h3>
				</div>
				<div class="col-md-6 "></div>
			</div>
			<hr />
		</div>

		<!-- Tabla de calificaciones - Vista Profesor -->
		<div class="row">

			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>idCursoCalif</th>
						<th>dniAlumno</th>
						<th>Nombre Apellido</th>
						<th>TipoExamen.id</th>
						<th>TipoExamen.descripcion</th>
						<th>nota</th>
						<th>fechaCalif</th>
						<th>fechaUltModif</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${objUsuario.listaCursosCalificaciones}"
						var="objCalificacion">
						<tr>
							<td>${objCalificacion.idCursoCalif}</td>
							<td>${objCalificacion.getObjUsuarioAlumn().dniAlumno}</td>
							<td>${objCalificacion.getObjUsuarioAlumn().nombre}</td>
							<td>${objCalificacion.getObjTipoExamen().idTipoExamen}</td>
							<td>${objCalificacion.getObjTipoExamen().descripcion}</td>
							<td>${objCalificacion.nota}</td>
							<td>${objCalificacion.fechaCalif}</td>
							<td>${objCalificacion.fechaUltModif}</td>
							<td>
								<div>
									<form action="modificarCalificacionUsuarioLoad.html"
										method="POST">
										<input type="hidden" value="${objCalificacion.idCursoCalif}"
											name="idCursoCalifToUpdate" /> <input type="submit"
											value="Modificar" class="btn btn-warning" />
									</form>
								</div>
							</td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
		</div>




	</div>

</body>
</html>