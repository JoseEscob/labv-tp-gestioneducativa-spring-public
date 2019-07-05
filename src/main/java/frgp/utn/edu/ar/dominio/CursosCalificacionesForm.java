package frgp.utn.edu.ar.dominio;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import utils.constantes.ConstantesDAO;

public class CursosCalificacionesForm {
	private Integer idCursoCalif;

	private Curso objCurso;

	private Usuario objUsuarioAlumn;
	
	private List<Usuario> listaAlumnos;

	private TipoExamen objTipoExamen;

	private int nota;

	private Date fechaCalif;

	private Date fechaUltModif;

	public CursosCalificacionesForm() {
		super();
	}

	public CursosCalificacionesForm(Integer idCursoCalif, Curso objCurso, Usuario objUsuarioAlumn, TipoExamen objTipoExamen,
			int nota, Date fechaCalif, Date fechaUltModif) {
		super();
		this.idCursoCalif = idCursoCalif;
		this.objCurso = objCurso;
		this.objUsuarioAlumn = objUsuarioAlumn;
		this.objTipoExamen = objTipoExamen;
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

	public Curso getObjCurso() {
		return objCurso;
	}

	public void setObjCurso(Curso objCurso) {
		this.objCurso = objCurso;
	}

	public Usuario getObjUsuarioAlumn() {
		return objUsuarioAlumn;
	}

	public void setObjUsuarioAlumn(Usuario objUsuarioAlumn) {
		this.objUsuarioAlumn = objUsuarioAlumn;
	}

	public TipoExamen getObjTipoExamen() {
		return objTipoExamen;
	}

	public void setObjTipoExamen(TipoExamen objTipoExamen) {
		this.objTipoExamen = objTipoExamen;
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

	public List<Usuario> getListaAlumnos() {
		return listaAlumnos;
	}

	public void setListaAlumnos(List<Usuario> listaAlumnos) {
		this.listaAlumnos = listaAlumnos;
	}

}
