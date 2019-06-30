package frgp.utn.edu.ar.servicio;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.TipoPeriodo;

public interface ITipoPeriodoService {
	public ArrayList<TipoPeriodo> getAll() throws Exception;

	public TipoPeriodo get(int id) throws Exception;

	public int getMax() throws Exception;

	public int insert(TipoPeriodo obj) throws Exception;

	public boolean update(TipoPeriodo obj) throws Exception;

	public boolean delete(int id) throws Exception;

}
