package frgp.utn.edu.ar.servicio;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Usuarios;

public interface UsuarioServicio {

	ArrayList<Usuarios> obtenerUsuarios();

	Usuarios obtenerUnRegistro(String nombreUser);

	void insertarUsuario(Usuarios usuario);

    void eliminarUsuario(Integer idUser) ;

	void actualizarUsuario(Usuarios usuario);
	
}
