<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Calificaci�n - Modificaci�n</title>
</head>
<body>
	<%@ include file="Banner.jsp"%>
	<div class="container">
		<h2>Formulario de Modificaci�n - Calificaci�n</h2>
		<hr />
		<!--Horizantal line divider  <div class="page-header"/> -->
		<div class="row">
			<%@ include file="mostrarInfoMessage.jsp"%>
		</div>
	</div>
	<form method="POST" action="modificarCalificacionByID.html">
		<div class="container">
			<div class="row">
				<%@ include file="_frmCalificacion.jsp"%>
			</div>
			<div class="row">
				<div class="row col-md-12">
					<div class="col-md-6 form-group"></div>
					<div class="col-md-6 form-group" align="right">
						<input type="submit" class="btn btn-primary" name="btnGuardar"
							value="Guardar"></input>
					</div>
				</div>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		//$("[name=fechaCalif]").attr('disabled', 'disabled');
		//document.getElementsByName('fechaCalif')[0].disabled = true;
	</script>
</body>
</html>