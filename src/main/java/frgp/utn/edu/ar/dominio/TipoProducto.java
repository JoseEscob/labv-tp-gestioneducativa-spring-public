package frgp.utn.edu.ar.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TipoProducto")
public class TipoProducto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idTipo")
	private Integer id;
	
	@Column(name="nombre")
	private String nombre;
	
	public TipoProducto()
	{
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
