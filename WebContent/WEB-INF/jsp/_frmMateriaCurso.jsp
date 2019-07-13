
<div class="row col-md-12">
	<div class="col-md-6 form-group">
		<span style="color: RED">* </span><label>Nombre Materia/Curso</label>
		<input type="text" name="nombreCurso" class="form-control"
			value="${objMateriaCurso.nombreCurso}"
			onkeypress="return soloLetras(event)" maxlength="50" required>
	</div>
	<div class="col-md-6 form-group">
		<span style="color: RED"> </span><label>ID Materia/ Curso </label> <input
			type="number" class="form-control" value="${objMateriaCurso.idCurso}"
			onkeypress="return soloNros(event)" maxlength="50" required readonly>
		<input type="hidden" value="${objMateriaCurso.idCurso}" name="idCurso">
	</div>
</div>
<div class="row col-md-12">
	<div class="col-md-6 form-group">
		<span style="color: RED">* </span><label>Año </label> <input
			type="number" name="anio" class="form-control"
			onkeypress="return soloNros(event)" pattern="^(19|20)\d\d$" min=1900
			max=2500 value="${objMateriaCurso.anio}" maxlength="4" required>
	</div>
	<!-- value="${objMateriaCurso.anio}" -->
	<div class="col-md-6 form-group">
		<span style="color: RED">* </span><label>Periodo </label> <select
			name="idPeriodo" class="form-control" required>
			<c:forEach items="${listaPeriodos}" var="item">
				<option value="${item.idPeriodo}">${ item.descripcion}</option>
			</c:forEach>
		</select>
	</div>
</div>
<div class="row col-md-12">
	<div class="col-md-6 form-group">
		<span style="color: RED">* </span><label>DNI Profesor </label> <input
			type="text" name="dniProfesor" class="form-control"
			onkeypress="return soloNros(event)" onpaste="return false"
			value="${objMateriaCurso.objUsuarioProfe.dni}" maxlength="20"
			required>
	</div>
</div>

<div class="row col-md-12">
	<div class="col-md-6 form-group"></div>
	<div class="col-md-6 form-group" align="right">
		<input type="submit" class="btn btn-primary" name="btnGuardar"
			value="Guardar"></input>
	</div>
</div>