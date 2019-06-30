<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Formulario de Alta</title>
</head>
<body>
	<%@ include file="Banner.jsp"%>
	<div class="container">
		<h2>Formulario de alta - Materia/Curso</h2>
		<hr />
		<!--Horizantal line divider  <div class="page-header"/> -->
		<div class="row">
			<%@ include file="mostrarInfoMessage.jsp"%>
		</div>
	</div>
	<form method="POST" action="altaMateriaCurso.html">

		<div class="container">
			<div class="row">

				<div class="row col-md-12">
					<div class="col-md-6 form-group">
						<span style="color: RED">* </span><label>Nombre
							Materia/Curso</label> <input type="text" name="nombreCurso"
							class="form-control" value="${objMateriaCurso.nombreCurso}"
							onkeypress="return soloLetras(event)" maxlength="50" required>
					</div>
					<div class="col-md-6 form-group"></div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-6 form-group">
						<span style="color: RED">* </span><label>Año </label> <input
							type="number" name="anio" class="form-control"
							onkeypress="return soloNros(event)" pattern="^(19|20)\d\d$"
							min=1900 max=2500  value="2019"
							maxlength="4" required>
					</div>
					<!-- value="${objMateriaCurso.anio}" -->
					<div class="col-md-6 form-group">
						<span style="color: RED">* </span><label>Periodo </label> <select
							name="idPeriodo" class="form-control" required>
							<c:forEach items="${listaPeriodos}" var="item">
								<option value="${item.idPeriodo}">${ item.descripcion}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-6 form-group">
						<span style="color: RED">* </span><label>DNI Profesor </label> <input
							type="text" name="dniProfesor" class="form-control"
							onkeypress="return soloNros(event)" onpaste="return false"
							value="${objMateriaCurso.objUsuarioProfe.dniProfesor}" maxlength="20" required>
					</div>
				</div>

				<div class="row col-md-12">
					<div class="col-md-6 form-group"></div>
					<div class="col-md-6 form-group" align="right">
						<input type="submit" class="btn btn-primary" 
							name="btnGuardar" value="Guardar"></input>
					</div>
				</div>
			</div>
		</div>
	</form>

</body>
</html>