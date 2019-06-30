<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
	<form action="iniciarSesion.html" method="post">
		<div class="modal-header">
			<h4 class="modal-title">Iniciar Sesión</h4>
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
		</div>
		<div class="modal-body">
			<div class="form-group">
				<label>Mail de Usuario</label> <input type="email"
					class="form-control" required="required" name="txtLoginUsuario"
					value="admin@admin.com">
			</div>
			<div class="form-group">
				<div class="clearfix">
					<label>Contraseña</label>
					<!-- <a href="#"
									class="pull-right text-muted"><small>¿La olvidaste?</small></a>
								-->
				</div>

				<input type="password" class="form-control" required="required"
					name="txtLoginClave">
			</div>
		</div>
		<div class="modal-footer">
			<input type="submit" class="btn btn-primary pull-right"
				id="btnIniciarSesion" name="btnLogin" value="Iniciar Sesión"></input>
		</div>
	</form>
</body>
</html>