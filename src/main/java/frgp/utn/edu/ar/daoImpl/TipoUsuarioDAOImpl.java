package frgp.utn.edu.ar.daoImpl;

import java.util.ArrayList;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.ITipoUsuarioDAO;
import frgp.utn.edu.ar.dominio.TipoUsuario;
import utils.constantes.ConstantesDAO;

public class TipoUsuarioDAOImpl implements ITipoUsuarioDAO {
	private final String fromTable = String.format("FROM %s", ConstantesDAO.TipoUsuarios);
	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public ArrayList<TipoUsuario> getAll() throws Exception {
		return (ArrayList<TipoUsuario>) this.hibernateTemplate.loadAll(TipoUsuario.class);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public TipoUsuario get(int id) throws Exception {
		return this.hibernateTemplate.get(TipoUsuario.class, id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public int getMax() throws Exception {
		int maxID = 0;
		String queryHQL = String.format("SELECT MAX(idTipoUsuario) %s ", fromTable);
		maxID = (int) this.hibernateTemplate.find(queryHQL).get(0);
		return maxID;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int insert(TipoUsuario obj) throws Exception {
		int idGenerado = (int) this.hibernateTemplate.save(obj);
		return idGenerado;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean update(TipoUsuario obj) throws Exception {
		boolean estado = false;
		this.hibernateTemplate.update(obj);
		estado = true;
		return estado;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean delete(int id) throws Exception {
		boolean estado = false;
		TipoUsuario obj = new TipoUsuario();
		obj.setIdTipoUsuario(id);
		this.hibernateTemplate.delete(obj);
		estado = true;
		return estado;
	}

}
