<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Calificación - Alta</title>
</head>
<body>
	<%@ include file="Banner.jsp"%>
	<div class="container">
		<h2>Formulario de alta - Calificación</h2>
		<hr />
		<!--Horizantal line divider  <div class="page-header"/> -->
		<div class="row">
			<%@ include file="mostrarInfoMessage.jsp"%>
		</div>
	</div>
	<form method="POST" action="altaCalificacion.html">
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
		datePickerId.max = new Date().toISOString().split("T")[0];
	</script>
</body>
</html>