package frgp.utn.edu.ar.dominio.validacion;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Future;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import utils.constantes.Constantes;

public class CalificacionValidator {
	private Integer idCursoCalif;

	private Integer idCurso;

	@Size(min = 5, max = 10, message = "El dni debe tener al menos {min} y un máximo de {max} digitos. Valor ingresado [${validatedValue}]")
	private String dni;

	private Integer idTipoExamen;

	@Range(min = 1)
	@NotNull(message = "Complete la nota con un valor de mayor a 0 y menor igual a 10. Valor ingresado [${validatedValue}]")
	private int nota;

	@NotNull(message = "Complete la fecha de calificacion")
	@DateTimeFormat(pattern = Constantes.YYYYMMDD_Guiones) // "dd-mmm-yyyy"
	private Date fechaCalif;

	@DateTimeFormat(pattern = Constantes.YYYYMMDD_Guiones)
	private Date fechaUltModif;

	public CalificacionValidator() {
		super();
	}

	public CalificacionValidator(Integer idCursoCalif, Integer idCurso, String dni, Integer idTipoExamen, int nota,
			Date fechaCalif, Date fechaUltModif) {
		super();
		this.idCursoCalif = idCursoCalif;
		this.idCurso = idCurso;
		this.dni = dni;
		this.idTipoExamen = idTipoExamen;
		this.nota = nota;
		this.fechaCalif = fechaCalif;
		this.fechaUltModif = fechaUltModif;
	}

	public Integer getIdCursoCalif() {
		return idCursoCalif;
	}

	public void setIdCursoCalif(Integer idCursoCalif) {
		this.idCursoCalif = idCursoCalif;
	}

	public Integer getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Integer idCurso) {
		this.idCurso = idCurso;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Integer getIdTipoExamen() {
		return idTipoExamen;
	}

	public void setIdTipoExamen(Integer idTipoExamen) {
		this.idTipoExamen = idTipoExamen;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public Date getFechaCalif() {
		return fechaCalif;
	}

	public void setFechaCalif(Date fechaCalif) {
		this.fechaCalif = fechaCalif;
	}

	public Date getFechaUltModif() {
		return fechaUltModif;
	}

	public void setFechaUltModif(Date fechaUltModif) {
		this.fechaUltModif = fechaUltModif;
	}

	@Override
	public String toString() {
		return "CalificacionValidator [idCursoCalif=" + idCursoCalif + ", idCurso=" + idCurso + ", dni=" + dni
				+ ", idTipoExamen=" + idTipoExamen + ", nota=" + nota + ", fechaCalif=" + fechaCalif
				+ ", fechaUltModif=" + fechaUltModif + "]";
	}

}
