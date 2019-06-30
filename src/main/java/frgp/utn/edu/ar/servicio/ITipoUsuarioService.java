package frgp.utn.edu.ar.servicio;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.TipoUsuario;

public interface ITipoUsuarioService {
	public ArrayList<TipoUsuario> getAll() throws Exception;

	public TipoUsuario get(int id) throws Exception;

	public int getMax() throws Exception;

	public int insert(TipoUsuario obj) throws Exception;

	public boolean update(TipoUsuario obj) throws Exception;

	public boolean delete(int id) throws Exception;

}
