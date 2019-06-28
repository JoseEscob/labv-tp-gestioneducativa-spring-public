package frgp.utn.edu.ar.servicio;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Producto;


public interface ProductoServicio {

	ArrayList<Producto> obtenerProductos();
	Producto obtenerUnRegistro(Integer id);
	void insertarProducto(Producto producto);
    void eliminarProducto(Integer id) ;
	void actualizarProducto(Producto producto);
	public Integer obtenerMaximoIdProducto();
}
