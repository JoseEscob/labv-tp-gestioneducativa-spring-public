package frgp.utn.edu.ar.dao;

import java.util.ArrayList;
import frgp.utn.edu.ar.dominio.TipoProducto;

public interface TipoProductoDao {
	
	public void insertarTipoProducto(TipoProducto tipoProducto);
	public TipoProducto obtenerTipoProductoPorId(Integer idTipoProducto);
	public ArrayList<TipoProducto> obtenerTipoProductos();
	public Integer obtenerMaximoTipoProducto();
	
}
