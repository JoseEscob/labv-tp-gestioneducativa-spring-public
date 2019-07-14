<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Inscripción a Materias/Cursos</title>
</head>
<body>
	<%@ include file="Banner.jsp"%>
	<div class="container">
		<h2>Inscripción a Materias/Cursos</h2>
		<hr />
		<div class="row">
			<%@ include file="mostrarInfoMessage.jsp"%>
		</div>
	</div>
	<div class="container">
		<form method="POST" action="inscripcionAlumno.html">

			<div class="row">
				<div class="row col-md-12">
					<div class="col-md-6 form-group">
						<span style="color: RED">* </span><label>DNI </label> <input
							type="text" name="dniAlumno" class="form-control"
							onkeypress="return soloNros(event)" value="${objUsuario.dni}"
							maxlength="10" required>
					</div>
					<div class="col-md-6 form-group">
						<span style="color: RED"> </span><label>ID Materia/ Curso
						</label> <input type="number" class="form-control"
							value="${objMateriaCurso.idCurso}"
							onkeypress="return soloNros(event)" maxlength="50" required
							readonly> <input type="hidden"
							value="${objMateriaCurso.idCurso}" name="idCurso">
					</div>
				</div>
				<div class="row col-md-12">
					<div class="col-md-6 form-group"></div>
					<div class="col-md-6 form-group" align="right">
						<input type="submit" class="btn btn-primary" name="btnGuardar"
							value="Guardar"></input>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>