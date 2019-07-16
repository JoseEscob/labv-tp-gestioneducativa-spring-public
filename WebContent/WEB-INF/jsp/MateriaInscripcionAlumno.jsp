<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Inscripción a Materias/Cursos</title>
</head>
<body>
	<%@ include file="Banner.jsp"%>
	<div class="container">
		<h2>Inscripción a Materias/Cursos</h2>
		<hr />
		<div class="row">
			<%@ include file="mostrarInfoMessage.jsp"%>
		</div>
	</div>
	<div class="container">
		<form method="POST" action="inscripcionAlumnoMasiva.html"
			modelAttribute="objListaDNIForm">

			<div class="row">
				<div class="row col-md-12">
					<div class="col-md-6 form-group">
						<span style="color: RED">* </span><label>DNI Alumno</label><input
							type="text" name="dniAlumno" class="form-control"
							onkeypress="return soloNros(event)" value="${objUsuario.dni}"
							maxlength="10" required>
					</div>
					<div class="col-md-6 form-group">
						<span style="color: RED"> </span><label>ID Materia/ Curso
						</label> <input type="number" class="form-control"
							value="${objMateriaCurso.idCurso}"
							onkeypress="return soloNros(event)" maxlength="50" required
							readonly> <input type="hidden"
							value="${objMateriaCurso.idCurso}" name="idCurso">
					</div>
				</div>
				<div class="row col-md-12">
					<input type="button" class="btn btn-info"
						value="Agregar más alumnos" onclick="agregarFila()" />
				</div>
				<div class="row col-md-12">
					<table id="grdTablaListaDNI">
						<tbody>
							<tr>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="row col-md-12">
					<div class="col-md-6 form-group"></div>
					<div class="col-md-6 form-group" align="right">
						<input type="submit" class="btn btn-primary" name="btnGuardar"
							value="Guardar"></input>
					</div>
				</div>
			</div>
		</form>
	</div>


	<script type="text/javascript">
		var arrHead = new Array();
		arrHead = [ 'DNI Alumno', '' ];

		function agregarFila() {
			var grdTabla = document.getElementById('grdTablaListaDNI');

			var rowCnt = grdTabla.rows.length; // GET TABLE ROW COUNT.
			var tr = grdTabla.insertRow(rowCnt); // TABLE ROW.
			tr = grdTabla.insertRow(rowCnt);
			var cantTotalColumnas = arrHead.length;
			for (var columnNumber = 0; columnNumber < cantTotalColumnas; columnNumber++) {
				var td = document.createElement('td'); // TABLE DEFINITION.
				td = tr.insertCell(columnNumber);

				//if (columnNumber == 0) { // FIRST COLUMN.
				if (columnNumber == 0) {
					// ADD A BUTTON.<input type="button" value="Remove" onclick="removeRow(this)">
					var button = document.createElement('input');

					// SET INPUT ATTRIBUTE.
					button.setAttribute('type', 'button');
					button.setAttribute('value', 'Eliminar');
					button.setAttribute('class', 'btn btn-danger');

					// ADD THE BUTTON's 'onclick' EVENT.
					button.setAttribute('onclick', 'removeRow(this)');

					td.appendChild(button);
				} else {
					// CREATE AND ADD TEXTBOX IN EACH CELL.
					var ele = document.createElement('input');
					ele.setAttribute('type', 'text');
					ele.setAttribute('value', '');
					ele.setAttribute('class', 'form-control');
					ele.setAttribute('name', 'dniAlumno');
					ele.setAttribute('maxlength', '10');
					ele.setAttribute('onkeypress', 'return soloNros(event)');

					td.appendChild(ele);
				}
			}
		}

		function removeRow(oButton) {
			var empTab = document.getElementById('grdTablaListaDNI');
			empTab.deleteRow(oButton.parentNode.parentNode.rowIndex); // BUTTON -> TD -> TR.
		}
	</script>
</body>
</html>
