package frgp.utn.edu.ar.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import utils.constantes.ConstantesDAO;

@Entity
@Table(name = ConstantesDAO.TipoExamen)
public class TipoExamen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idTipoExamen", updatable = false, nullable = false)
	private Integer idTipoExamen;

	@Column(name = "descripcion")
	private String descripcion;

	public TipoExamen() {
		super();
	}

	public TipoExamen(Integer idTipoExamen, String descripcion) {
		super();
		this.idTipoExamen = idTipoExamen;
		this.descripcion = descripcion;
	}

	public Integer getIdTipoExamen() {
		return idTipoExamen;
	}

	public void setIdTipoExamen(Integer idTipoExamen) {
		this.idTipoExamen = idTipoExamen;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
