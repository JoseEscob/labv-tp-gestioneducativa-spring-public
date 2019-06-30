<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="utils.constantes.ConstantesJSP"%>
<%@ page import="utils.constantes.Constantes"%>

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

<!-- Custom fonts for this template -->

  <link href="<%=ConstantesJSP.jspLogin_Monserrat_css%>" rel="stylesheet" type="text/css">
  <link href="<%=ConstantesJSP.jspLogin_Italic_css%>" rel="stylesheet" type="text/css">

<!-- Plugin CSS -->
<link href="<%=ConstantesJSP.jspLogin_magnific_popup%>" rel="stylesheet"
	type="text/css">

<link href="<%=ConstantesJSP.jspLogin_freelancer_min_css%>"
	rel="stylesheet">
<link href="<%=ConstantesJSP.jspLogin_fontAwe_min_css%>"
	rel="stylesheet">

<link rel="shortcut icon" type="image/png"
	href="<%=ConstantesJSP.jspLogin_img_profilepng%>" />

<script src="<%=ConstantesJSP.jspValidar_js%>"></script>

<title></title>
</head>
<body>
	<!-- Navigation -->
	<nav class="navbar  bg-secondary  text-uppercase" id="mainNav">
		<div class="container">
			<a class="navbar-brand js-scroll-trigger">Sist. Gestión Educativa</a>
			<button
				class="navbar-toggler navbar-toggler-right text-uppercase bg-primary text-white rounded"
				type="button" data-toggle="collapse" data-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="true"
				aria-label="Toggle navigation">
				Menu <i class="fas fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<c:choose>
						<c:when
							test="${sessionScope.sessionUser.getObjTipoUsuario().idTipoUsuario eq Constantes.idTipoUsuarioAlumn}">
							<li class="nav-item"><a href="InicioAlumno.jsp">&nbsp;Inicio</a></li>
							<li class="nav-item mx-0 mx-lg-1"><a
								class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger">
									${sessionScope.sessionUser.nombre } - Alumno</a> <a
								href="UsuarioServlet?accionGET=modificarUsuarioLogueadoLoad">&nbsp;Editar
									Perfil</a> <a href="UsuarioServlet?accionGET=cerrarSesion">&nbsp;Salir</a></li>
						</c:when>
						<c:when
							test="${sessionScope.sessionUser.getObjTipoUsuario().idTipoUsuario eq Constantes.idTipoUsuarioProfe}">
							<li class="nav-item"><a href="InicioProfesor.jsp">&nbsp;Inicio</a></li>
							<li class="nav-item"><a
								class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger">
									${sessionScope.sessionUser.nombre } - Profe</a><a
								href="UsuarioServlet?accionGET=modificarUsuarioLogueadoLoad">&nbsp;Editar
									Perfil</a> <a href="UsuarioServlet?accionGET=cerrarSesion">&nbsp;Salir</a></li>
						</c:when>
						<c:when
							test="${sessionScope.sessionUser.getObjTipoUsuario().idTipoUsuario eq Constantes.idTipoUsuarioAdmin}">
							<li class="nav-item"><a href="InicioAdministrador.jsp">&nbsp;Inicio</a></li>
							<li class="nav-item"><a
								class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger">
									${sessionScope.sessionUser.nombre } - Admin</a><a
								href="UsuarioServlet?accionGET=modificarUsuarioLogueadoLoad">&nbsp;Editar
									Perfil</a> <a href="UsuarioServlet?accionGET=cerrarSesion">&nbsp;Salir</a></li>
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