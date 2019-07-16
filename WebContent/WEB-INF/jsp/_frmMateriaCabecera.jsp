
<div class="row col-md-12">
	<div class="col-md-6">
		<h3>${objCurso.nombreCurso}</h3>
	</div>
	<div class="col-md-6 pull-right">
		<h4>Nro de Curso: ${objCurso.idCurso}</h4>
	</div>
</div>
<div class="row col-md-12">
	<div class="col-md-12">
		<h3>
			<small>Docente: </small><b>${objCurso.objUsuarioProfe.nombre}
				&nbsp;${objCurso.objUsuarioProfe.apellido}</b>
		</h3>
	</div>
	<div class="col-md-12">
		<h5>
			<small>Mail: <a>${objCurso.objUsuarioProfe.mail}</a></small>
		</h5>
	</div>
</div>
<div class="row col-md-12">
	<div class="col-md-6">
		<h3>
			<small> <br> <label>Año: ${objCurso.anio}</label> <br>
				<label>Periodo: ${objCurso.getObjTipoPeriodo().descripcion}</label>
			</small>
		</h3>
	</div>
	<div class="col-md-6 "></div>
</div>
<hr />
