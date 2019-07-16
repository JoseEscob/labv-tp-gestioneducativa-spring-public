<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Modificación</title>
</head>
<body>
	<%@ include file="Banner.jsp"%>
	<div class="container">
		<h2>Modificar datos del usuario</h2>
		<hr />
		<!--Horizantal line divider  <div class="page-header"/> -->
		<div class="row">
			<%@ include file="mostrarInfoMessage.jsp"%>
		</div>
	</div>
	<form method="POST" action="modificarUsuarioByAdmin.html"
		modelAttribute="objUsuario">

		<div class="container">
			<div class="row">

				<%@ include file="_frmUsuario.jsp"%>

				<div class="row col-md-12">
					<div class="col-md-6 form-group"></div>
					<div class="col-md-6 form-group" align="right">
						<input type="hidden" value="${objUsuario.idUsuario}"
							name="idUsuarioToViewModif" /> <input type="submit"
							class="btn btn-primary" name="btnGuardar" value="Guardar"></input>
					</div>
				</div>
			</div>
		</div>
	</form>
	<br>
	<form method="POST" action="listaCursosByDNIProfesor.html">
		<div class="container">
			<div class="row">
				<hr />

				<div class="row col-md-12">
					<h4>
						Cant. de Materias asignadas al Profesor: <small>${cantMateriasAsignadas}</small>
					</h4>
					<c:choose>
						<c:when test="${cantMateriasAsignadas ne 0}">
							<div class="col-md-6 form-group" align="right">
								<input type="hidden" value="${objUsuario.dni}"
									name="txtDNIBuscado" /> <input type="submit"
									class="btn btn-primary" name="btnVerMaterias"
									value="Ver Materias"></input>
							</div>
						</c:when>
						<c:otherwise>
							<div class="col-md-6 form-group">
								<div class="alert alert-info" role="alert">Este usuario no
									tiene materias asignadas</div>
							</div>
						</c:otherwise>
					</c:choose>
					<div class="col-md-6 form-group"></div>
				</div>
			</div>
		</div>
	</form>

	<script type="text/javascript">
		datePickerId.max = new Date().toISOString().split("T")[0];
	</script>
</body>
</html>