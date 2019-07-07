<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
	<div class="row col-md-12">
		<div class="col-md-6 form-group">
			<div class="form-group">
				<span style="color: RED"> </span><label>Fecha de
					Calificación </label> <input type="date" name="fechaCalif"
					class="form-control"
					value="<fmt:formatDate value="${objCalificacion.fechaCalif}" pattern="yyyy-MM-dd" />"
					maxlength="10" id="datePickerId" placeholder="dd-MM-yyyy" required
					readonly>
			</div>
		</div>
		<div class="col-md-6 form-group">
			<span style="color: RED"> </span><label>ID Calificación </label> <input
				type="number" name="idCursoCalif" class="form-control"
				value="${objCalificacion.idCursoCalif}"
				onkeypress="return soloNros(event)" maxlength="50" required readonly>
		</div>
	</div>

	<div class="row col-md-12">
		<div class="col-md-6 form-group">
			<span style="color: RED">* </span><label>Tipo de Examen </label> <select
				name="idTipoExamen" class="form-control" required>
				<c:forEach items="${listaTiposExamen}" var="item">
					<option
						<c:if test="${objCalificacion.getObjTipoExamen().idTipoExamen eq item.idTipoExamen}">
											 selected
										</c:if>
						value="${item.idTipoExamen}">${ item.descripcion}</option>

				</c:forEach>
			</select>
		</div>
		<div class="col-md-6 form-group">
			<span style="color: RED"> </span><label>ID Curso </label> <input
				type="text" name="idCurso" class="form-control"
				value="${objCalificacion.objCurso.idCurso}"
				onkeypress="return soloNros(event)" maxlength="50" required readonly>
		</div>
	</div>
	<div class="row col-md-12">
		<div class="col-md-6 form-group">
			<font color="red">* </font><label>DNI Alumno </label> <input
				type="text" name="dniAlumno" class="form-control"
				onkeypress="return soloNros(event)"
				value="${objCalificacion.getObjUsuarioAlumn().dni}"
				maxlength="15" required>
		</div>
		<div class="col-md-6 form-group">
			<font color="red">* </font><label>Nota </label> <input type="number"
				name="nota" class="form-control" pattern="^\d\d$" min=1 max=10
				onkeypress="return soloNros(event)" value="${objCalificacion.nota}"
				maxlength="2" required>
		</div>
	</div>
	<div class="row col-md-12">
		<br>
	</div>
</body>
</html>