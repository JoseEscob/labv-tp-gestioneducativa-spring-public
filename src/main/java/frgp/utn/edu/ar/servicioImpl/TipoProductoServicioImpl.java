package frgp.utn.edu.ar.servicioImpl;

import java.util.ArrayList;

import frgp.utn.edu.ar.dao.TipoProductoDao;
import frgp.utn.edu.ar.dominio.TipoProducto;
import frgp.utn.edu.ar.servicio.TipoProductoServicio;

public class TipoProductoServicioImpl implements TipoProductoServicio {


	private TipoProductoDao dataAccess = null;
	
	public void setDataAccess(TipoProductoDao dataAccess) {
		this.dataAccess = dataAccess;
	}

	@Override
	public TipoProducto obtenerTipoProducto(Integer id) {
		return this.dataAccess.obtenerTipoProductoPorId(id);
	}

	@Override
	public ArrayList<TipoProducto> obtenerTipoProductos() {
		return this.dataAccess.obtenerTipoProductos();
	}

	@Override
	public void insertarTipoProducto(TipoProducto tipoproducto) {
		this.dataAccess.insertarTipoProducto(tipoproducto);
	}

	@Override
	public Integer obtenerMaximoIdProducto() {
		return this.dataAccess.obtenerMaximoTipoProducto();
	}
}