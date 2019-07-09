package frgp.utn.edu.ar.dominio.validacion;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import utils.constantes.Constantes;
import utils.constantes.ConstantesDAO;

@Entity
@Table(name = ConstantesDAO.Usuario, indexes = { @Index(columnList = "dni", name = "dniIndex") })
public class UsuarioValidator {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUsuario", updatable = false, nullable = false)
	private Integer idUsuario;

	@NotNull(message = "Por favor seleccione un opción para el tipo de usuario")
	private int idTipoUsuario;

	@NotNull(message = "Por favor complete el campo: nombre")
	private String nombre;

	@NotNull(message = "Por favor complete el campo: apellido")
	private String apellido;
	
	@Size(min = 5, max = 10, message = "El dni debe tener al menos {min} y un máximo de {max} digitos. Valor ingresado [${validatedValue}]")
	private String dni;

	@Size(min = 3, message = "El nombre de la calle no es válido, debe tener al menos {min} caracteres. Valor ingresado [${validatedValue}]")
	private String calleNombre;

	@Size(min = 1, max = 10, message = "La altura debe tener al menos {min} digito. Valor ingresado [${validatedValue}]")
	private String calleAltura;

	@NotNull(message = "Complete la fecha de nacimiento")
	@Past
	@DateTimeFormat(pattern = Constantes.YYYYMMDD_Guiones) // "dd-mmm-yyyy"
	private Date fechaNac;

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	
	private String nroTelefono;

	
	@NotNull
	@Pattern(regexp=".+@.+\\.[a-z]+", message="Por favor ingrese un correo electrónico válido. Valor ingresado [${validatedValue}]")
	private String mail;

	@Size(min = 5, max = 20, message = "La contraseña ingresada no es válida, debe tener al menos {min} y un máximo de {max} caracteres. Valor ingresado [${validatedValue}]")
	private String clave;

	private boolean habilitado;

	public UsuarioValidator() {
		super();
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
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

	public boolean getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public int getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

}
