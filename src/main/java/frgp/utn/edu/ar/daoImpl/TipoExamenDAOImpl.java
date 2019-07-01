package frgp.utn.edu.ar.daoImpl;

import java.util.ArrayList;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.ITipoExamenDAO;
import frgp.utn.edu.ar.dominio.TipoExamen;
import utils.constantes.ConstantesDAO;

public class TipoExamenDAOImpl implements ITipoExamenDAO {
	private final String fromTable = String.format("FROM %s", ConstantesDAO.TipoExamen);
	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public ArrayList<TipoExamen> getAll() throws Exception {
		return (ArrayList<TipoExamen>) this.hibernateTemplate.loadAll(TipoExamen.class);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public TipoExamen get(int id) throws Exception {
		return this.hibernateTemplate.get(TipoExamen.class, id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public int getMax() throws Exception {
		int maxID = 0;
		String queryHQL = String.format("SELECT MAX(idTipoExamen) %s ", fromTable);
		maxID = (int) this.hibernateTemplate.find(queryHQL).get(0);
		return maxID;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int insert(TipoExamen obj) throws Exception {
		int idGenerado = (int) this.hibernateTemplate.save(obj);
		return idGenerado;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean update(TipoExamen obj) throws Exception {
		boolean estado = false;
		this.hibernateTemplate.update(obj);
		estado = true;
		return estado;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean delete(int id) throws Exception {
		boolean estado = false;
		TipoExamen obj = new TipoExamen();
		obj.setIdTipoExamen(id);
		this.hibernateTemplate.delete(obj);
		estado = true;
		return estado;
	}

}
