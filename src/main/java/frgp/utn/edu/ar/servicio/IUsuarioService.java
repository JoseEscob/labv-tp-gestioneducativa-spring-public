package frgp.utn.edu.ar.servicio;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Usuario;

public interface IUsuarioService {
	public ArrayList<Usuario> getAll() throws Exception;

	public Usuario get(int id) throws Exception;

	// public int getCount() throws Exception;

	public int getMax() throws Exception;

	public int insert(Usuario obj) throws Exception;

	public boolean update(Usuario obj) throws Exception;

	public boolean remove(int id) throws Exception;

	public int delete(Usuario obj) throws Exception;

	public Usuario getUsuarioByLogin(String correoUsuario, String claveUsuario) throws Exception;
}
