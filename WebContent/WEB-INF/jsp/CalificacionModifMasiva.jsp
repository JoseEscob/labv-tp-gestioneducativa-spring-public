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
		<form:form method="POST" action="modificarCalificacionMasiva.html"
			commandName="objCalificacionForm">
			<div class="row col-md-12">

				<table class="table table-striped table-bordered">
					<thead>
						<tr>
							<th>No.</th>
							<th>idCursoCalif</th>
							<th>dniAlumno</th>
							<th>nota</th>
							<th>fechaCalif</th>
							<th>fechaUltModif</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${objCalificacionForm.listaCalificaciones}"
							var="objCalificacion" varStatus="status">
							<%@ include file="_filaCalificacionMasiva.jsp"%>
						</c:forEach>
					</tbody>
				</table>

			</div>
			<div class="row col-md-12">
				<div class="col-md-6 form-group"></div>
				<div class="col-md-6 form-group" align="right">
					<input type="submit" class="btn btn-primary" name="btnGuardar"
						value="Guardar"></input>
				</div>
			</div>
		</form:form>


	</div>

</body>
</html>