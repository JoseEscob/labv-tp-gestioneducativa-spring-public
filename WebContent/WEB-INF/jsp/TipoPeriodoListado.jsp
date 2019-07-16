<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>

<title>Tipos de Periodo</title>
</head>
<body>
	<%@ include file="Banner.jsp"%>
	<div class="container">
		<h2>Listado de Registros</h2>
		<hr />
		<div class="row">
			<div class="pull-right">
				<div>
					<a href="altaTipoPeriodoLoad.html" class="btn btn-primary"
						data-toggle="tooltip" title="Dar de alta un Tipo de Periodo">&nbsp;Alta
						Tipos de Periodo </a>
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
					<c:forEach items="${listaTipoPeriodo}" var="objTipoPeriodo">
						<tr>
							<td>${objTipoPeriodo.idPeriodo}</td>
							<td>${objTipoPeriodo.descripcion}</td>

							<td>
								<div class="col-md-6 form-group row">
									<div class="col-md-6">
										<form action="modificarTipoPeriodoLoad.html" method="POST">
											<input type="hidden" value="${objTipoPeriodo.idPeriodo}"
												name="idPeriodo" /> <input type="submit" value="Modificar"
												class="btn btn-warning" />
										</form>
									</div>
									<div class="col-md-6">
										<form action="eliminarTipoPeriodo.html" method="POST">
											<input type="hidden" value="${objTipoPeriodo.idPeriodo}"
												name="idPeriodo" /> <input type="submit" value="Eliminar"
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