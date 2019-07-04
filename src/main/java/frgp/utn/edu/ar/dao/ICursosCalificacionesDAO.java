package frgp.utn.edu.ar.dao;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.CursosCalificaciones;

public interface ICursosCalificacionesDAO {

	public ArrayList<CursosCalificaciones> getAll() throws Exception;

	public CursosCalificaciones get(int id) throws Exception;

	public int getMax() throws Exception;

	public int insert(CursosCalificaciones obj) throws Exception;

	public boolean update(CursosCalificaciones obj) throws Exception;

	public boolean delete(int id) throws Exception;
}
