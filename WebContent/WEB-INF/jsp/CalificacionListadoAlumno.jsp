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
		<div class="row">

			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>idCursoCalif</th>
						<th>dniAlumno</th>
						<th>Nombre Apellido - Alumno</th>
						<!--<th>TipoExamen.id</th>-->
						<th>TipoExamen</th>
						<th>nota</th>
						<th>fechaCalificado</th>
						<th>fechaUlt.Modif</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listaCursosCalificaciones}"
						var="objCalificacion">
						<tr>
							<td>${objCalificacion.idCursoCalif}</td>
							<td>${objCalificacion.getObjUsuarioAlumn().dni}</td>
							<td>${objCalificacion.getObjUsuarioAlumn().nombre}&nbsp;${objCalificacion.getObjUsuarioAlumn().apellido}</td>
							<!--<td>${objCalificacion.getObjTipoExamen().idTipoExamen}</td>-->
							<td>${objCalificacion.getObjTipoExamen().descripcion}</td>
							<td>${objCalificacion.nota}</td>
							<td><fmt:formatDate value="${objCalificacion.fechaCalif}"
									pattern="yyyy-MMM-dd HH:mm" /></td>
							<td><fmt:formatDate value="${objCalificacion.fechaUltModif}"
									pattern="yyyy-MMM-dd HH:mm" /></td>

						</tr>

					</c:forEach>
				</tbody>
			</table>
		</div>




	</div>

</body>
</html>