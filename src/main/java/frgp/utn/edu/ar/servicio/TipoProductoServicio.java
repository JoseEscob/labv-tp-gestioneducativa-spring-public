package frgp.utn.edu.ar.servicio;

import java.util.ArrayList;
import frgp.utn.edu.ar.dominio.TipoProducto;

public interface TipoProductoServicio {

	TipoProducto obtenerTipoProducto(Integer id);
	ArrayList<TipoProducto> obtenerTipoProductos();
	void insertarTipoProducto(TipoProducto tipoproducto);
	public Integer obtenerMaximoIdProducto();

}
