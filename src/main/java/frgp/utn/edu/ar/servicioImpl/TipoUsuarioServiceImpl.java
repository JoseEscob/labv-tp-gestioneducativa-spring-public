package frgp.utn.edu.ar.servicioImpl;

import java.util.ArrayList;

import frgp.utn.edu.ar.dao.ITipoUsuarioDAO;
import frgp.utn.edu.ar.dominio.TipoUsuario;
import frgp.utn.edu.ar.servicio.ITipoUsuarioService;

public class TipoUsuarioServiceImpl implements ITipoUsuarioService {
	private ITipoUsuarioDAO dataAccess = null;

	public void setDataAccess(ITipoUsuarioDAO dataAccess) {
		this.dataAccess = dataAccess;
	}

	@Override
	public ArrayList<TipoUsuario> getAll() throws Exception {
		return dataAccess.getAll();
	}

	@Override
	public TipoUsuario get(int id) throws Exception {
		return dataAccess.get(id);
	}

	@Override
	public int getMax() throws Exception {
		return dataAccess.getMax();
	}

	@Override
	public int insert(TipoUsuario obj) throws Exception {
		return dataAccess.insert(obj);
	}

	@Override
	public boolean update(TipoUsuario obj) throws Exception {
		return dataAccess.update(obj);
	}

	@Override
	public boolean delete(int id) throws Exception {
		return dataAccess.delete(id);
	}

}
