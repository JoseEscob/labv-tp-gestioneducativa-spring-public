package frgp.utn.edu.ar.daoImpl;

import java.util.ArrayList;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.ICursoDAO;
import frgp.utn.edu.ar.dominio.Curso;
import frgp.utn.edu.ar.dominio.TipoPeriodo;
import utils.constantes.ConstantesDAO;

public class CursoDAOImpl implements ICursoDAO {
	private final String fromTable = String.format("FROM %s", ConstantesDAO.Curso);
	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public ArrayList<Curso> getAll() throws Exception {
		return (ArrayList<Curso>) this.hibernateTemplate.loadAll(Curso.class);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Curso get(int id) throws Exception {
		return this.hibernateTemplate.get(Curso.class, id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public int getMax() throws Exception {
		int maxID = 0;
		String queryHQL = String.format("SELECT MAX(curso.idCurso) %s curso", fromTable);
		maxID = (int) this.hibernateTemplate.find(queryHQL).get(0);
		return maxID;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int insert(Curso obj) throws Exception {
		int idGenerado = (int) this.hibernateTemplate.save(obj);
		return idGenerado;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean update(Curso obj) throws Exception {
		boolean estado = false;
		this.hibernateTemplate.update(obj);
		estado = true;
		return estado;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean delete(int id) throws Exception {
		boolean estado = false;
		Curso obj = new Curso();
		obj.setIdCurso(id);
		this.hibernateTemplate.delete(obj);
		estado = true;
		return estado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Curso> getAllByDNIProfe(String dniProfesor) throws Exception {
		String queryHQL = String.format("%s WHERE dniProfesor = %s", fromTable, dniProfesor);
		return (ArrayList<Curso>) this.hibernateTemplate.find(queryHQL);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<TipoPeriodo> getAllDistinctTipoPeriodo() throws Exception {
		String queryHQL = String.format("SELECT DISTINCT objTipoPeriodo %s", fromTable);
		return (ArrayList<TipoPeriodo>) this.hibernateTemplate.find(queryHQL);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Integer> getAllDistinctAnio() throws Exception {
		String queryHQL = String.format("SELECT DISTINCT anio %s", fromTable);
		return (ArrayList<Integer>) this.hibernateTemplate.find(queryHQL);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Curso> getAllByNombreCursoBuscado(String nombreCurso) throws Exception {
		String likeParam = "'" + nombreCurso + "%')";
		String queryHQL = String.format("%s WHERE lower(nombreCurso) LIKE lower(%s", fromTable, likeParam);
		return (ArrayList<Curso>) this.hibernateTemplate.find(queryHQL);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Curso> getAllByFiltroPeriodoAnio(int idTipoPeriodo, int anio) throws Exception {
		String queryHQL = String.format("%s WHERE anio = %d AND objTipoPeriodo.idPeriodo = %d", fromTable, anio,
				idTipoPeriodo);
		return (ArrayList<Curso>) this.hibernateTemplate.find(queryHQL);
	}
}
