package frgp.utn.edu.ar.dao;
import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Usuario;

public interface IUsuarioDAO<T> {

	public ArrayList<T> getAll() throws Exception;

	public T get(int id) throws Exception;

	public int getMax() throws Exception;

	public int insert(T obj) throws Exception;

	public boolean update(T obj) throws Exception;

	public boolean delete(int id) throws Exception;

	public Usuario getUsuarioByLogin(String correoUsuario, String claveUsuario) throws Exception;

}
