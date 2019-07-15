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
			<%@ include file="_frmMateriaCabecera.jsp"%>
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