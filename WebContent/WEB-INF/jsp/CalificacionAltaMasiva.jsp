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
		<form:form method="POST" action="altaCalificacionMasiva.html">
			<!-- Tabla de calificaciones - Cabecera Página -->
			<div class="row">
				<%@ include file="_frmMateriaCabecera.jsp"%>
			</div>

			<div class="row col-md-12">
					<div class="col-md-6"></div>
					<div class="col-md-6 form-group">
						<span style="color: RED">* </span><label>Tipo de Examen </label> <select
							name="idTipoExamenSeleccionado" class="form-control" required>
							<c:forEach items="${listaTiposExamen}" var="item">
								<option value="${item.idTipoExamen}">${ item.descripcion}</option>

							</c:forEach>
						</select>
					</div>
			</div>

			<!-- Tabla de calificaciones - Vista Profesor -->
			<div class="row">
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
							<c:set var="today" value="<%=new java.util.Date()%>" />
							${today}
							<c:forEach items="${listaDNIAlumno}" var="dniAlumno"
								varStatus="status">
								<tr>
									<fmt:formatDate value="${now}" 	pattern="yyyy-MM-dd" var="fechaCalif_format"/>
									<fmt:formatDate value="${now}"	pattern="yyyy-MM-dd" var="fechaUltModif_format"/>
									<td align="center">${status.count}</td>
									<td><form:input path="objCalificacionForm.listaCalificaciones[${status.index}].idCursoCalif" 	type="number" 	class="form-control" 						readonly="true"/></td>
									<td><form:input path="objCalificacionForm.listaCalificaciones[${status.index}].dni" 			type="text" 	class="form-control" value="${dniAlumno}" 	maxlength="15" onkeypress="return soloNros(event)" /></td>
									<td><form:input path="objCalificacionForm.listaCalificaciones[${status.index}].nota" 			type="number" 	class="form-control" pattern="^\d\d$" 		min="1" max="10"/></td>
									<td><form:input path="objCalificacionForm.listaCalificaciones[${status.index}].fechaCalif" 		type="date" 	class="form-control" value="${fechaCalif_format}" /></td>
									<td><form:input path="objCalificacionForm.listaCalificaciones[${status.index}].fechaUltModif" 	type="date" 	class="form-control" value="${fechaUltModif_format}" readonly="true" id="idFechaUltModif"/></td>
										<form:input path="objCalificacionForm.listaCalificaciones[${status.index}].idCurso" 		type="hidden" 	class="form-control" value="${objCurso.idCurso}"/> 
										<form:input path="objCalificacionForm.listaCalificaciones[${status.index}].idTipoExamen" 	type="hidden" 	class="form-control" />
									<!--
									<td>
									${objCalificacion.idCurso} *
									${objCalificacion.idTipoExamen}
									
									</td>
									-->
								</tr>

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
			</div>
		</form:form>


	</div>

</body>
</html>