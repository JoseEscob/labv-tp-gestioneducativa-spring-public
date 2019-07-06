package frgp.utn.edu.ar.dominio;

import java.util.ArrayList;
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

@Entity
@Table(name = ConstantesDAO.Usuario, indexes = { @Index(columnList = "dni", name = "dniIndex") })
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUsuario", updatable = false, nullable = false)
	private Integer idUsuario;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "idTipoUsuario")
	private TipoUsuario objTipoUsuario;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellido")
	private String apellido;

	@Column(name = "dni")
	// @Index(name = "dniIndex")
	private String dni;

	@Column(name = "calleNombre")
	private String calleNombre;

	@Column(name = "calleAltura")
	private String calleAltura;

	@Column(name = "fechaNac")
	private Date fechaNac;

	@Column(name = "nroTelefono")
	private String nroTelefono;

	@Column(name = "mail")
	private String mail;

	@Column(name = "clave")
	private String clave;

	@Column(name = "habilitado", columnDefinition = "boolean default true")
	private boolean habilitado;

	//fetch=FetchType.LAZY
	/*List<Curso> listaCursos = new ArrayList<Curso>();
	List<CursosCalificaciones> listaCursosCalificaciones = new ArrayList<CursosCalificaciones>();
*/
	public Usuario() {
		super();
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public TipoUsuario getObjTipoUsuario() {
		return objTipoUsuario;
	}

	public void setObjTipoUsuario(TipoUsuario objTipoUsuario) {
		this.objTipoUsuario = objTipoUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCalleNombre() {
		return calleNombre;
	}

	public void setCalleNombre(String calleNombre) {
		this.calleNombre = calleNombre;
	}

	public String getCalleAltura() {
		return calleAltura;
	}

	public void setCalleAltura(String calleAltura) {
		this.calleAltura = calleAltura;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getNroTelefono() {
		return nroTelefono;
	}

	public void setNroTelefono(String nroTelefono) {
		this.nroTelefono = nroTelefono;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
/*
	public List<Curso> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public List<CursosCalificaciones> getListaCursosCalificaciones() {
		return listaCursosCalificaciones;
	}

	public void setListaCursosCalificaciones(List<CursosCalificaciones> listaCursosCalificaciones) {
		this.listaCursosCalificaciones = listaCursosCalificaciones;
	}
	*/
}
