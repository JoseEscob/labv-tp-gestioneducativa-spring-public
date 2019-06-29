package frgp.utn.edu.ar.dominio;

import java.util.Date;

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

@Entity
@Table(name = ConstantesDAO.CursosCalificaciones, indexes = { @Index(columnList = "dniAlumno", name = "dniIndex") })
public class CursosCalificaciones {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCursoCalif", updatable = false, nullable = false)
	private Integer idCursoCalif;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "idCurso")
	private Curso objCurso;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "dniAlumno", referencedColumnName = "dni") 
	private Usuario objUsuarioAlumn;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "idTipoExamen")
	private TipoExamen objTipoExamen;

	@Column(name = "nota")
	private int nota;

	@Column(name = "fechaCalif")
	private Date fechaCalif;

	@Column(name = "fechaUltModif")
	private Date fechaUltModif;

	public CursosCalificaciones() {
		super();
	}

	public CursosCalificaciones(Integer idCursoCalif, Curso objCurso, Usuario objUsuarioAlumn, TipoExamen objTipoExamen,
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

}
