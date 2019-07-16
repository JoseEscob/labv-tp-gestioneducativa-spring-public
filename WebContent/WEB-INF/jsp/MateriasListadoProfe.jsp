<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Materias Cursos - Profesor</title>
</head>
<body>

	<%@ include file="Banner.jsp"%>
	<div class="container">
		<h2>Materias/Cursos Dictadas</h2>
		<hr />
		<!--Horizantal line divider  <div class="page-header"/> -->
		<div class="row">
			<%@ include file="mostrarInfoMessage.jsp"%>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="row col-md-12 form-group">
				<h4>Filtros</h4>
			</div>
			<div class="row col-md-12">
				<div class="col-md-8 form-group">
					<form method="POST" action="listaCursosByFiltroPeriodoAnio.html">
						<div class="col-md-4 form-group">
							<label>Tipo de Periodo </label> <select
								name="idTipoPeriodoBuscado" class="form-control">
								<c:forEach items="${listaTipoPeriodo}" var="item">
									<option value="${item.idPeriodo}">${ item.descripcion}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-4 form-group">
							<label>Año </label> <select name="anioBuscado"
								class="form-control">
								<c:forEach items="${listaAnio}" var="item">
									<option value="${item}">${item}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-4 form-group">
							<input type="submit" class="btn btn-primary border"
								value="Filtrar"></input>
						</div>
					</form>
				</div>
				<div class="col-md-4 form-group pull-right">
					<form method="POST" action="listaCursosByNombreCurso.html">
						<label>Buscador de Materias</label>
						<div class="input-group">
							<input class="form-control border" type="search"
								name="txtNombreCursoBuscado" placeholder="Nombre de Materia"
								onkeypress="return soloLetras(event)" maxlength="50" /> <span
								class="input-group-append">
								<button class="btn btn-outline-secondary border" type="submit">
									<i class="fa fa-search"></i>
								</button>
							</span>
						</div>
					</form>
				</div>

			</div>
		</div>

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
						<td><c:choose>
								<c:when
									test="${sessionScope.sessionUser.getObjTipoUsuario().idTipoUsuario eq Constantes.idTipoUsuarioAlumn}">
									<div>
										<form action="calificacionListadoAlumnoLoad.html"
											method="POST">
											<input type="hidden" value="${objCurso.idCurso}"
												name="idCursoToViewCalificaciones" /> <input type="submit"
												value="Ver mis calificaciones" class="btn btn-info" />
										</form>
									</div>
								</c:when>
								<c:otherwise>
									<div>
										<form action="gestionarMateriaCurso.html" method="POST">
											<input type="hidden" value="${objCurso.idCurso}"
												name="idCurso" /> <input type="submit"
												value="Gestionar Materia" class="btn btn-warning" />
										</form>
									</div>
								</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>