package frgp.utn.edu.ar.daoImpl;

import java.util.ArrayList;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.ITipoPeriodoDAO;
import frgp.utn.edu.ar.dominio.TipoPeriodo;
import utils.constantes.ConstantesDAO;

public class TipoPeriodoDAOImpl implements ITipoPeriodoDAO {
	private final String fromTable = String.format("FROM %s", ConstantesDAO.TipoPeriodo);
	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public ArrayList<TipoPeriodo> getAll() throws Exception {
		return (ArrayList<TipoPeriodo>) this.hibernateTemplate.loadAll(TipoPeriodo.class);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public TipoPeriodo get(int id) throws Exception {
		return this.hibernateTemplate.get(TipoPeriodo.class, id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public int getMax() throws Exception {
		int maxID = 0;
		String queryHQL = String.format("SELECT MAX(idPeriodo) %s ", fromTable);
		maxID = (int) this.hibernateTemplate.find(queryHQL).get(0);
		return maxID;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int insert(TipoPeriodo obj) throws Exception {
		int idGenerado = (int) this.hibernateTemplate.save(obj);
		return idGenerado;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean update(TipoPeriodo obj) throws Exception {
		boolean estado = false;
		this.hibernateTemplate.update(obj);
		estado = true;
		return estado;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean delete(int id) throws Exception {
		boolean estado = false;
		TipoPeriodo obj = new TipoPeriodo();
		obj.setIdPeriodo(id);
		this.hibernateTemplate.delete(obj);
		estado = true;
		return estado;
	}

}
