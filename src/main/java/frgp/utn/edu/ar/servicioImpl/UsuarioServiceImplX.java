package frgp.utn.edu.ar.servicioImpl;

import java.util.ArrayList;

import frgp.utn.edu.ar.dao.IConnectable;
import frgp.utn.edu.ar.dominio.Usuario;
import frgp.utn.edu.ar.servicio.IService;

public class UsuarioServiceImplX implements IService<Usuario> {
	private IConnectable<Usuario> dataAccess = null;

	public void setDataAccess(IConnectable<Usuario> dataAccess) {
		this.dataAccess = dataAccess;
	}

	@Override
	public ArrayList<Usuario> getAll() throws Exception {
		return this.dataAccess.getAll();
	}

	@Override
	public Usuario get(int id) throws Exception {
		return this.dataAccess.get(id);
	}

	@Override
	public int getMax() throws Exception {
		return this.dataAccess.getMax();
	}

	@Override
	public int insert(Usuario obj) throws Exception {
		return this.dataAccess.insert(obj);
	}

	@Override
	public boolean update(Usuario obj) throws Exception {
		return this.dataAccess.update(obj);
	}

	@Override
	public boolean remove(int id) throws Exception {
		return this.dataAccess.remove(id);
	}

	@Override
	public int delete(Usuario obj) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
