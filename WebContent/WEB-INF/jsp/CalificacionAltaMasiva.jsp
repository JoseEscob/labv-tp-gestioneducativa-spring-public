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
			<form:form method="POST" action="altaCalificacionMasiva.html" commandName="objCalificacionForm" >

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
						<tr>
							<td align="center">${status.count}</td>
							<td><input name="objCalificacionForm.listaCalificaciones[${status.index}].idCursoCalif" 	type="number" 	value="${objCalificacion.idCursoCalif}"		readonly/></td>
							<td><input name="objCalificacionForm.listaCalificaciones[${status.index}].dni " 			type="text" 	value="${objCalificacion.dni}"	/></td>
							<td><input name="objCalificacionForm.listaCalificaciones[${status.index}].nota" 			type="number" 	value="${objCalificacion.nota}"						/></td>
							<td><input name="objCalificacionForm.listaCalificaciones[${status.index}].fechaCalif" 		type="date" 	value="<fmt:formatDate value="${objCalificacion.fechaCalif}" 	pattern="yyyy-MM-dd" />"/></td>
							<td><input name="objCalificacionForm.listaCalificaciones[${status.index}].fechaUltModif" 	type="date" 	value="<fmt:formatDate value="${objCalificacion.fechaUltModif}" pattern="yyyy-MM-dd" />"/></td>
								<input name="objCalificacionForm.listaCalificaciones[${status.index}].idCurso" 			type="hidden" 	value="${objCalificacion.idCurso}"/> 
								<input name="objCalificacionForm.listaCalificaciones[${status.index}].idTipoExamen" 	type="hidden" 	value="${objCalificacion.idTipoExamen}"/>
								
							<td><form:input path="listaCalificaciones[${status.index}].nota" 		type="number" 	value="${objCalificacion.nota}"						/></td>

							<td>
							${objCalificacion.idCurso} *
							${objCalificacion.idTipoExamen}
							</td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
			<br/>
			<input type="submit" value="Guardar" />
			</form:form>
		</div>




	</div>

</body>
</html>