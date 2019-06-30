package frgp.utn.edu.ar.servicioImpl;

import java.util.ArrayList;

import frgp.utn.edu.ar.dao.ITipoPeriodoDAO;
import frgp.utn.edu.ar.dominio.TipoPeriodo;
import frgp.utn.edu.ar.servicio.ITipoPeriodoService;

public class TipoPeriodoServiceImpl implements ITipoPeriodoService {
	private ITipoPeriodoDAO dataAccess = null;

	public void setDataAccess(ITipoPeriodoDAO dataAccess) {
		this.dataAccess = dataAccess;
	}

	@Override
	public ArrayList<TipoPeriodo> getAll() throws Exception {
		return dataAccess.getAll();
	}

	@Override
	public TipoPeriodo get(int id) throws Exception {
		return dataAccess.get(id);
	}

	@Override
	public int getMax() throws Exception {
		return dataAccess.getMax();
	}

	@Override
	public int insert(TipoPeriodo obj) throws Exception {
		return dataAccess.insert(obj);
	}

	@Override
	public boolean update(TipoPeriodo obj) throws Exception {
		return dataAccess.update(obj);
	}

	@Override
	public boolean delete(int id) throws Exception {
		return dataAccess.delete(id);
	}

}
