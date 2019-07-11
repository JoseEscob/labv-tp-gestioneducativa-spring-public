package frgp.utn.edu.ar.daoImpl;

import java.util.ArrayList;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.ICursosCalificacionesDAO;
import frgp.utn.edu.ar.dominio.CursosCalificaciones;
import utils.constantes.ConstantesDAO;

public class CursosCalificacionesDAOImpl implements ICursosCalificacionesDAO {
	private final String fromTable = String.format("FROM %s", ConstantesDAO.CursosCalificaciones);
	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public ArrayList<CursosCalificaciones> getAll() throws Exception {
		return (ArrayList<CursosCalificaciones>) this.hibernateTemplate.loadAll(CursosCalificaciones.class);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public CursosCalificaciones get(int id) throws Exception {
		return this.hibernateTemplate.get(CursosCalificaciones.class, id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public int getMax() throws Exception {
		int maxID = 0;
		String queryHQL = String.format("SELECT MAX(curso.idCursoCalif) %s curso", fromTable);
		maxID = (int) this.hibernateTemplate.find(queryHQL).get(0);
		return maxID;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int insert(CursosCalificaciones obj) throws Exception {
		int idGenerado = (int) this.hibernateTemplate.save(obj);
		return idGenerado;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean update(CursosCalificaciones obj) throws Exception {
		boolean estado = false;
		this.hibernateTemplate.update(obj);
		estado = true;
		return estado;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean delete(int id) throws Exception {
		boolean estado = false;
		CursosCalificaciones obj = new CursosCalificaciones();
		obj.setIdCursoCalif(id);
		this.hibernateTemplate.delete(obj);
		estado = true;
		return estado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<CursosCalificaciones> getAllByID(int id) throws Exception {
		String queryHQL = String.format(" %s WHERE idCurso = %s", fromTable, id);
		return (ArrayList<CursosCalificaciones>) this.hibernateTemplate.find(queryHQL);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<String> getAllDNIByIDCurso(int id) throws Exception {
		String queryHQL = String.format("SELECT DISTINCT objUsuarioAlumn.dni %s WHERE idCurso = %s", fromTable, id);
		return (ArrayList<String>) this.hibernateTemplate.find(queryHQL);
	}

}
