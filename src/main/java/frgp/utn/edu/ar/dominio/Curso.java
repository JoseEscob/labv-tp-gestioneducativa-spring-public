package frgp.utn.edu.ar.dominio;

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
@Table(name = ConstantesDAO.Curso, indexes = { @Index(columnList = "dniProfesor", name = "dniIndex") })
public class Curso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCurso", updatable = false, nullable = false)
	private Integer idCurso;

	@Column(name = "nombreCurso")
	private String nombreCurso;

	@Column(name = "anio")
	private int anio;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "idPeriodo")
	private TipoPeriodo objTipoPeriodo;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "dniProfesor", referencedColumnName = "dni") 
	private Usuario objUsuarioProfe;

	public Curso() {
		super();
	}

	public Curso(Integer idCurso, String nombreCurso, int anio, TipoPeriodo objTipoPeriodo, Usuario objUsuarioProfe) {
		super();
		this.idCurso = idCurso;
		this.nombreCurso = nombreCurso;
		this.anio = anio;
		this.objTipoPeriodo = objTipoPeriodo;
		this.objUsuarioProfe = objUsuarioProfe;
	}

	public Integer getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Integer idCurso) {
		this.idCurso = idCurso;
	}

	public String getNombreCurso() {
		return nombreCurso;
	}

	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public TipoPeriodo getObjTipoPeriodo() {
		return objTipoPeriodo;
	}

	public void setObjTipoPeriodo(TipoPeriodo objTipoPeriodo) {
		this.objTipoPeriodo = objTipoPeriodo;
	}

	public Usuario getObjUsuarioProfe() {
		return objUsuarioProfe;
	}

	public void setObjUsuarioProfe(Usuario objUsuarioProfe) {
		this.objUsuarioProfe = objUsuarioProfe;
	}

}
