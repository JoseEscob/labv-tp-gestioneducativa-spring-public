package frgp.utn.edu.ar.servicio;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.CursosCalificaciones;

public interface ICursosCalificacionesService {
	public ArrayList<CursosCalificaciones> getAll() throws Exception;

	public CursosCalificaciones get(int id) throws Exception;

	public int getMax() throws Exception;

	public int insert(CursosCalificaciones obj) throws Exception;

	public boolean update(CursosCalificaciones obj) throws Exception;

	public boolean delete(int id) throws Exception;

	public ArrayList<CursosCalificaciones> getAllByID(int id) throws Exception;

}
