<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>

<title>Tipos de Examen</title>
</head>
<body>
	<%@ include file="Banner.jsp"%>
	<div class="container">
		<h2>Listado de Registros</h2>
		<hr />
		<div class="row">
			<div class="pull-right">
				<div>
					<a href="altaTipoExamenLoad.html" class="btn btn-primary"
						data-toggle="tooltip" title="Dar de alta un Tipo de Examen">&nbsp;Alta
						Tipos de Examen </a>
				</div>
			</div>
			<%@ include file="mostrarInfoMessage.jsp"%>
		</div>
	</div>
	<div class="container">

		<div class="row">
			<table id="grdUsuarios" class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>id</th>
						<th>descripcion</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listaTipoExamen}" var="objTipoExamen">
						<tr>
							<td>${objTipoExamen.idTipoExamen}</td>
							<td>${objTipoExamen.descripcion}</td>

							<td>
								<div class="col-md-6 form-group row">
									<div class="col-md-6">
										<form action="modificarTipoExamenLoad.html" method="POST">
											<input type="hidden" value="${objTipoExamen.idTipoExamen}"
												name="idTipoExamen" /> <input type="submit" value="Modificar"
												class="btn btn-warning" />
										</form>
									</div>
									<div class="col-md-6">
										<form action="eliminarTipoExamen.html" method="POST">
											<input type="hidden" value="${objTipoExamen.idTipoExamen}"
												name="idTipoExamen" /> <input type="submit" value="Eliminar"
												class="btn btn-danger" />
										</form>
									</div>
								</div>
								<div></div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>