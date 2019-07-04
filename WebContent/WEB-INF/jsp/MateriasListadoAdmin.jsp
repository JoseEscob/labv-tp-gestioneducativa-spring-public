<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Materias Cursos</title>
</head>
<body>

	<%@ include file="Banner.jsp"%>
	<div class="container">
		<h2>Materias Cursos</h2>
		<hr />
		<!--Horizantal line divider  <div class="page-header"/> -->
		<div class="row">
			<%@ include file="mostrarInfoMessage.jsp"%>
		</div>
	</div>
	<div class="container">
		<table id="grdCursos" class="table table-striped table-bordered">
			<thead>
				<tr>
					<%@ include file="_filaMateriasHeader.jsp"%>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listaCursos}" var="objCurso">
					<tr>
						<%@ include file="_filaMateriasBody.jsp"%>
						<td>
							<div>
								<form action="MateriaCursoServlet" method="POST">
									<input type="hidden" value="${objCurso.idCurso}"
										name="idCursoToDelete" /> <input type="hidden"
										name="accionPOST" value="eliminarCurso"></input> <input
										type="submit" value="Eliminar" class="btn btn-danger" />
								</form>
							</div>
							<div>
								<form action="MateriaCursoServlet" method="POST">
									<input type="hidden" value="${objCurso.idCurso}"
										name="idCursoToUpdate" /> <input type="hidden"
										name="accionPOST" value="modificarCursoLoad"></input> <input
										type="submit" value="Modificar" class="btn btn-info" />
								</form>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>