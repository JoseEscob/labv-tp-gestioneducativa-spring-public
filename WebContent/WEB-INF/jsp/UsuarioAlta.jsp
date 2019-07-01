<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Formulario de Alta</title>
</head>
<body>
	<%@ include file="Banner.jsp"%>
	<div class="container">
		<h2>Formulario de alta usuario</h2>
		<hr />
		<!--Horizantal line divider  <div class="page-header"/> -->
		<div class="row">
			<%@ include file="mostrarInfoMessage.jsp"%>
		</div>
	</div>
	<form method="POST" action="altaUsuarioSave.html">

		<div class="container">
			<div class="row">
			<%@ include file="_frmUsuario.jsp"%>
<!-- 
				<div class="row col-md-12">
					<div class="col-md-6 form-group">
						<span style="color: RED">* </span><label>Nombre </label> <input
							type="text" name="nombre" class="form-control"
							value="${objUsuario.nombre}"
							onkeypress="return soloLetras(event)" maxlength="50" required>
					</div>
					<div class="col-md-6 form-group">
						<font color="red">* </font><label>Apellido </label> <input
							type="text" name="apellido" class="form-control"
							onkeypress="return soloLetras(event)"
							value="${objUsuario.apellido}" maxlength="50" required>
					</div>
				</div>
				<div class="row col-md-12">
					<br>
				</div>
				<div class="row col-md-12">
					<div class="col-md-4">
						<div class="form-group">
							<span style="color: RED">* </span><label>DNI </label> <input
								type="text" name="dni" class="form-control"
								onkeypress="return soloNros(event)" onpaste="return false"
								value="${objUsuario.dni}" maxlength="10" required>
						</div>
						<div class="form-group">
							<span style="color: RED">* </span><label>Fecha de
								Nacimiento </label> <input type="date" name="fechaNac"
								class="form-control" value="${objUsuario.fechaNac}"
								pattern="^\d{2}-\d{2}-2\d{3}$" maxlength="10" id="datePickerId"
								placeholder="dd-MM-yyyy" min="01-01-1900" required>
						</div>
						<div class="form-group">
							<span style="color: RED"> </span><label>Nro Teléfono </label> <input
								type="text" name="nroTelefono" class="form-control"
								onkeypress="return soloNros(event)" onpaste="return false"
								value="${objUsuario.nroTelefono}" maxlength="20">
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<span style="color: RED">* </span><label>Calle </label> <input
								type="text" name="calleNombre" class="form-control"
								value="${objUsuario.calleNombre}"
								onkeypress="return soloLetras(event)" maxlength="50" required>
						</div>
						<div class="form-group">
							<span style="color: RED">* </span><label>Altura </label> <input
								type="text" name="calleAltura" class="form-control"
								onkeypress="return soloNros(event)"
								value="${objUsuario.calleAltura}" maxlength="5" required>
						</div>
					</div>

					<div class="col-md-4">
					
						<div class="form-group">
							<span style="color: RED">* </span><label>Tipo de Usuario
							</label> <select name="idTipoUsuario" class="form-control" required>
								<c:forEach items="${listaTipoUsuarios}" var="item">
									<option value="${item.idTipoUsuario}">${ item.descripcion}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<span style="color: RED">* </span><label>Mail </label> <input
								type="email" name="mail" class="form-control" maxlength="30"
								value="${objUsuario.mail}" required>
						</div>
						<div class="form-group">
							<span style="color: RED">* </span><label>Clave </label> <input
								type="text" name="clave" class="form-control" maxlength=20
								value="${objUsuario.clave}" required>
						</div>
					</div>

				</div>
				 -->
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