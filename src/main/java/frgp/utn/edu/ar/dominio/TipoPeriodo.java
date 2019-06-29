package frgp.utn.edu.ar.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import utils.constantes.ConstantesDAO;

@Entity
@Table(name = ConstantesDAO.TipoPeriodo)
public class TipoPeriodo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPeriodo")
	private Integer idPeriodo;

	@Column(name = "descripcion")
	private String descripcion;

	public TipoPeriodo() {
		super();
	}

	public TipoPeriodo(Integer idPeriodo, String descripcion) {
		super();
		this.idPeriodo = idPeriodo;
		this.descripcion = descripcion;
	}

	public Integer getIdPeriodo() {
		return idPeriodo;
	}

	public void setIdPeriodo(Integer idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
