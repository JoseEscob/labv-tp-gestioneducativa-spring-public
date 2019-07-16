<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Tipo Examen - Modificar</title>
</head>
<body>
	<%@ include file="Banner.jsp"%>
	<div class="container">
		<h2>Tipo Examen - Modificar</h2>
		<hr />
		<div class="row">
			<%@ include file="mostrarInfoMessage.jsp"%>
		</div>
	</div>
	<div class="container">
		<form method="POST" action="modificarTipoExamen.html">

			<div class="row">
				<div class="row col-md-12">
					<div class="col-md-6 form-group">
						<span style="color: RED"> </span><label>ID </label> <input
							type="number" class="form-control"
							value="${objTipoExamen.idTipoExamen}" required readonly> <input
							type="hidden" value="${objTipoExamen.idTipoExamen}"
							name="idTipoExamen">
					</div>
					<div class="col-md-6 form-group">
						<span style="color: RED">* </span><label>Descripción</label><input
							type="text" name="descripcion" class="form-control"
							value="${objTipoExamen.descripcion}" maxlength="20" required>
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
