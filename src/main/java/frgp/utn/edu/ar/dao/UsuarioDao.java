package frgp.utn.edu.ar.dao;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Usuarios;


public interface UsuarioDao {

	//Alta de persona
	public void insertarUsuario(Usuarios usuario);

	//Obtiene una persona por dni
	public Usuarios obtenerUsuarioPorNombre(String nombreUser);

	//Obtiene todas las presonas
	public ArrayList<Usuarios> obtenerUsuarios();

	//Elimina una presona a aprtir del dni
	public void eliminarUsuario(Integer idUsuario);

	//Actualiza los datos de una persona
	public void actualizarUsuario(Usuarios usuario);
	

}
