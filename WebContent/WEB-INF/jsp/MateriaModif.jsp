<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Modificar - Materia/Curso</title>
</head>
<body>
	<%@ include file="Banner.jsp"%>
	<div class="container">
		<h2>Modificar - Materia/Curso</h2>
		<hr />
		<!--Horizantal line divider  <div class="page-header"/> -->
		<div class="row">
			<%@ include file="mostrarInfoMessage.jsp"%>
		</div>
	</div>
	<form method="POST" action="modificarMateriaCurso.html">

		<div class="container">
			<div class="row">
				<%@ include file="_frmMateriaCurso.jsp"%>
			</div>
		</div>
	</form>

</body>
</html>