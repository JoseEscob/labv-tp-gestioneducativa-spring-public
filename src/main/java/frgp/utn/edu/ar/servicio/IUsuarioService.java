package frgp.utn.edu.ar.servicio;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.TipoUsuario;
import frgp.utn.edu.ar.dominio.Usuario;

public interface IUsuarioService {
	public ArrayList<Usuario> getAll() throws Exception;

	public Usuario get(int id) throws Exception;

	// public int getCount() throws Exception;

	public int getMax() throws Exception;

	public int insert(Usuario obj) throws Exception;

	public boolean update(Usuario obj) throws Exception;

	public boolean delete(int id) throws Exception;

	//public int delete(Usuario obj) throws Exception;

	public Usuario getUsuarioByLogin(String correoUsuario, String claveUsuario) throws Exception;

	public void validarCamposUnicos(Usuario objUsuario) throws Exception;
	
	public Usuario getUsuarioByDNI(String dni) throws Exception;
	
	public boolean saveOrUpdate(Usuario obj) throws Exception;

	ArrayList<Usuario> getAllByDNIBuscado(String dni) throws Exception;
	
	ArrayList<TipoUsuario> getAllTipoUsuarioByUsuarios() throws Exception;


}
