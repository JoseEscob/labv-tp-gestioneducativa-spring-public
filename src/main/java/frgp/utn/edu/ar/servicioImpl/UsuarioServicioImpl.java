package frgp.utn.edu.ar.servicioImpl;

import java.util.ArrayList;

import frgp.utn.edu.ar.dao.UsuarioDao;
import frgp.utn.edu.ar.dominio.Usuarios;
import frgp.utn.edu.ar.servicio.UsuarioServicio;

public class UsuarioServicioImpl implements UsuarioServicio{

	private UsuarioDao dataAccess = null;

	public void setDataAccess(UsuarioDao dataAccess) {
		this.dataAccess = dataAccess;
	}
	
	@Override
	public ArrayList<Usuarios> obtenerUsuarios() {
		return dataAccess.obtenerUsuarios();
	}

	@Override
	public Usuarios obtenerUnRegistro(String nombreUser) {
		return dataAccess.obtenerUsuarioPorNombre(nombreUser);
	}

	@Override
	public void insertarUsuario(Usuarios usuario) {
		 dataAccess.insertarUsuario(usuario);
		
	}

	@Override
	public void eliminarUsuario(Integer idUser) {
		dataAccess.eliminarUsuario(idUser);
		
	}

	@Override
	public void actualizarUsuario(Usuarios usuario) {
		dataAccess.actualizarUsuario(usuario);
		
	}

}
