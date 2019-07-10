<tr>
	<fmt:formatDate value="${objCalificacion.fechaCalif}" 	pattern="yyyy-MM-dd" var="fechaCalif_format"/>
	<fmt:formatDate value="${objCalificacion.fechaUltModif}" pattern="yyyy-MM-dd" var="fechaUltModif_format"/>
	<td align="center">${status.count}</td>
	<td><form:input path="listaCalificaciones[${status.index}].idCursoCalif" 	type="number" 	class="form-control" value="${objCalificacion.idCursoCalif}" readonly="true"/></td>
	<td><form:input path="listaCalificaciones[${status.index}].dni" 			type="text" 	class="form-control" value="${objCalificacion.dni}" maxlength="15" onkeypress="return soloNros(event)" /></td>
	<td><form:input path="listaCalificaciones[${status.index}].nota" 			type="number" 	class="form-control" value="${objCalificacion.nota}"  pattern="^\d\d$" min="1" max="10"/></td>
	<td><form:input path="listaCalificaciones[${status.index}].fechaCalif" 		type="date" 	class="form-control" value="${fechaCalif_format}" /></td>
	<td><form:input path="listaCalificaciones[${status.index}].fechaUltModif" 	type="date" 	class="form-control" value="${fechaUltModif_format}" readonly="true" id="idFechaUltModif"/></td>
		<form:input path="listaCalificaciones[${status.index}].idCurso" 		type="hidden" 	class="form-control" value="${objCalificacion.idCurso}"/> 
		<form:input path="listaCalificaciones[${status.index}].idTipoExamen" 	type="hidden" 	class="form-control" value="${objCalificacion.idTipoExamen}"/>
	<!--
	<td>
	${objCalificacion.idCurso} *
	${objCalificacion.idTipoExamen}
	
	</td>
	-->
</tr>
