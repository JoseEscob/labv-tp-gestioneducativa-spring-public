package frgp.utn.edu.ar.dao;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Producto;

public interface ProductoDao {

	public void insertarProducto(Producto producto);
	public Producto obtenerProductoPorId(Integer idProducto);
	public ArrayList<Producto> obtenerProductos();
	public void eliminarProducto(Integer idProducto);
	public void actualizarProducto(Producto producto);
	public Integer obtenerMaximoIdProducto();
}
