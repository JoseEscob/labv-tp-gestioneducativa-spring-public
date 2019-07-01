package frgp.utn.edu.ar.servicioImpl;

import java.util.ArrayList;

import frgp.utn.edu.ar.dao.ITipoExamenDAO;
import frgp.utn.edu.ar.dominio.TipoExamen;
import frgp.utn.edu.ar.servicio.ITipoExamenService;

public class TipoExamenServiceImpl implements ITipoExamenService {
	private ITipoExamenDAO dataAccess = null;

	public void setDataAccess(ITipoExamenDAO dataAccess) {
		this.dataAccess = dataAccess;
	}

	@Override
	public ArrayList<TipoExamen> getAll() throws Exception {
		return dataAccess.getAll();
	}

	@Override
	public TipoExamen get(int id) throws Exception {
		return dataAccess.get(id);
	}

	@Override
	public int getMax() throws Exception {
		return dataAccess.getMax();
	}

	@Override
	public int insert(TipoExamen obj) throws Exception {
		return dataAccess.insert(obj);
	}

	@Override
	public boolean update(TipoExamen obj) throws Exception {
		return dataAccess.update(obj);
	}

	@Override
	public boolean delete(int id) throws Exception {
		return dataAccess.delete(id);
	}

}
