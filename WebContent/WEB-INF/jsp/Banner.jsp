<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="utils.constantes.ConstantesJSP"%>
<%@ page import="utils.constantes.Constantes"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<!-- IMPORTS DE BANNER -->
<meta http-equiv="Content-Type"
	content="text/html; charset=utf-8; charset=ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap core CSS -->
<link href="<%=ConstantesJSP.jspLogin_bootstrapMin_css%>"
	rel="stylesheet">
<link href="<%=ConstantesJSP.jspLogin_fontAwesome_css%>"
	rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css"
	rel="stylesheet" />

<!-- Custom fonts for this template -->

<link href="<%=ConstantesJSP.jspLogin_Monserrat_css%>" rel="stylesheet"
	type="text/css">
<link href="<%=ConstantesJSP.jspLogin_Italic_css%>" rel="stylesheet"
	type="text/css">

<!-- Plugin CSS -->
<link href="<%=ConstantesJSP.jspLogin_magnific_popup%>" rel="stylesheet"
	type="text/css">
<link href="<%=ConstantesJSP.jspLogin_freelancer_min_css%>"
	rel="stylesheet">

<link rel="shortcut icon" type="image/png"
	href="<%=ConstantesJSP.jspLogin_img_profilepng%>" />

<script src="<%=ConstantesJSP.jspValidar_js%>"></script>

<title></title>
</head>
<body>
	<!-- Navigation -->
	<nav class="navbar  bg-secondary" id="mainNav">
		<div class="container">
			<a class="navbar-brand js-scroll-trigger text-uppercase">Sist.
				Gestión Educativa</a>
			<c:choose>
				<c:when test="${empty sessionScope.sessionUser}">
					<a class="btn btn-primary text-white " data-target="#iniciarSesion"
						data-toggle="modal">Iniciar Sesión</a>
				</c:when>
				<c:otherwise>
					<button
						class="navbar-toggler navbar-toggler-right text-uppercase bg-primary text-white rounded"
						type="button" data-toggle="collapse"
						data-target="#navbarResponsive" aria-controls="navbarResponsive"
						aria-expanded="true">Menu</button>
				</c:otherwise>
			</c:choose>
			<div class="collapse navbar-collapse text-uppercase"
				id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<c:choose>
						<c:when
							test="${sessionScope.sessionUser.getObjTipoUsuario().idTipoUsuario eq Constantes.idTipoUsuarioAlumn}">
							<li class="nav-item"><a href="InicioAlumno.html">&nbsp;Inicio</a></li>
							<li class="nav-item mx-0 mx-lg-1"><a
								class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger">
									${sessionScope.sessionUser.nombre }&nbsp;${sessionScope.sessionUser.apellido } - Alumno</a> <a
								href="modificarUsuarioLogueadoLoad.html">&nbsp;Editar Perfil</a>
								<a href="cerrarSesion.html">&nbsp;Salir</a></li>
						</c:when>
						<c:when
							test="${sessionScope.sessionUser.getObjTipoUsuario().idTipoUsuario eq Constantes.idTipoUsuarioProfe}">
							<li class="nav-item"><a href="InicioProfesor.html">&nbsp;Inicio</a></li>
							<li class="nav-item"><a
								class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger">
									${sessionScope.sessionUser.nombre }&nbsp;${sessionScope.sessionUser.apellido } - Profe</a><a
								href="modificarUsuarioLogueadoLoad.html">&nbsp;Editar Perfil</a>
								<a href="cerrarSesion.html">&nbsp;Salir</a></li>
						</c:when>
						<c:when
							test="${sessionScope.sessionUser.getObjTipoUsuario().idTipoUsuario eq Constantes.idTipoUsuarioAdmin}">
							<li class="nav-item"><a href="InicioAdministrador.html">&nbsp;Inicio</a></li>
							<li class="nav-item"><a
								class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger">
									${sessionScope.sessionUser.nombre }&nbsp;${sessionScope.sessionUser.apellido } - Admin</a><a
								href="modificarUsuarioLogueadoLoad.html">&nbsp;Editar Perfil</a>
								<a href="cerrarSesion.html">&nbsp;Salir</a></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a class="nav-link "
								data-target="#iniciarSesion" data-toggle="modal">Iniciar
									Sesión</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Modal -->
	<div class="modal fade" id="iniciarSesion">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-body">
					<%@include file="iniciarSesion.jsp"%>
				</div>
			</div>
		</div>
	</div>
	<div>
		<br>
	</div>
	<!-- Bootstrap core JavaScript -->
	<script src="<%=ConstantesJSP.jspLogin_jquery_js%>"></script>
	<script src="<%=ConstantesJSP.jspLogin_bootstrap_bundle_min_js%>"></script>
	<!-- Plugin JavaScript -->
	<script src="<%=ConstantesJSP.jspLogin_jquery_easing_min_js%>"></script>
	<script src="<%=ConstantesJSP.jspLogin_jquery_magnific_popup_min_js%>"></script>
	<!-- Contact Form JavaScript -->
	<script src="<%=ConstantesJSP.jspLogin_bootstrap_bundle_min_js%>"></script>
	<script src="<%=ConstantesJSP.jspLogin_jqBootstrapValidation_js%>"></script>
	<script src="<%=ConstantesJSP.jspLogin_contact_me_js%>"></script>
	<!-- Custom scripts for this template -->
	<script src="<%=ConstantesJSP.jspLogin_freelancer_min_js%>"></script>
</body>
</html>