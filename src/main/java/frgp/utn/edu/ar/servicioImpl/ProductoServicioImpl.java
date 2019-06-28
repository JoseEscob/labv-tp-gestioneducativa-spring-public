package frgp.utn.edu.ar.servicioImpl;

import java.util.ArrayList;

import frgp.utn.edu.ar.dao.ProductoDao;
import frgp.utn.edu.ar.dominio.Producto;
import frgp.utn.edu.ar.servicio.ProductoServicio;

public class ProductoServicioImpl implements ProductoServicio{

	private ProductoDao dataAccess = null;
	
	public void setDataAccess(ProductoDao dataAccess) {
		this.dataAccess = dataAccess;
	}
	
	@Override
	public ArrayList<Producto> obtenerProductos() {
		return dataAccess.obtenerProductos();
	}

	@Override
	public Producto obtenerUnRegistro(Integer id) {
		return this.dataAccess.obtenerProductoPorId(id);
	}

	@Override
	public void insertarProducto(Producto producto) {
		this.dataAccess.insertarProducto(producto);
	}

	@Override
	public void eliminarProducto(Integer id) {
		this.dataAccess.eliminarProducto(id);
	}

	@Override
	public void actualizarProducto(Producto producto) {
		this.dataAccess.actualizarProducto(producto);
	}

	@Override
	public Integer obtenerMaximoIdProducto() {
		return this.dataAccess.obtenerMaximoIdProducto();
	}

}
