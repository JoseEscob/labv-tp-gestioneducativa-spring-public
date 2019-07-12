package frgp.utn.edu.ar.daoImpl;

import java.util.ArrayList;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.ICursosCalificacionesDAO;
import frgp.utn.edu.ar.dominio.CursosCalificaciones;
import utils.constantes.ConstantesDAO;
import utils.LOG;

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
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public ArrayList<CursosCalificaciones> getAllByDNIAlumno(String dniAlumno) throws Exception {
		String queryHQL = String.format(" %s WHERE objUsuarioAlumn.dni = %s", fromTable, dniAlumno);
		return (ArrayList<CursosCalificaciones>) this.hibernateTemplate.find(queryHQL);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public ArrayList<CursosCalificaciones> getAllByDNIAlumnoIDCurso(String dniAlumno, int id) throws Exception {
		String queryHQL = String.format(" %s WHERE objUsuarioAlumn.dni = %s AND idCurso = %s", fromTable, dniAlumno, id);
		return (ArrayList<CursosCalificaciones>) this.hibernateTemplate.find(queryHQL);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<String> getAllDNIByIDCurso(int id) throws Exception {
		String queryHQL = String.format("SELECT DISTINCT objUsuarioAlumn.dni %s WHERE idCurso = %s", fromTable, id);
		return (ArrayList<String>) this.hibernateTemplate.find(queryHQL);
	}

	@Override
	public void validarCamposUnicos(CursosCalificaciones obj) throws Exception {
		LOG.info("Calificaciones: Comienza el proceso de validación de campos únicos");
		int idCurso = obj.getObjCurso().getIdCurso();
		// int idTipoPeriodo = obj.getObjCurso().getObjTipoPeriodo().getIdPeriodo();
		String dniAlumno = obj.getObjUsuarioAlumn().getDni();
		int idTipoExamen = obj.getObjTipoExamen().getIdTipoExamen();

		for (CursosCalificaciones dbObj : getAllByID(idCurso)) {
			if ((dbObj.getObjUsuarioAlumn().getDni().equals(dniAlumno))) {
				if (dbObj.getObjTipoExamen().getIdTipoExamen() == idTipoExamen) {
					throw new Exception(String.format(
							"ERROR DB: Combinación de datos repetidos. \nYa existe una calificación para el curso [%d - %s], alumno con DNI [%s] y tipo de examen: [%d - %s]",
							idCurso, dbObj.getObjCurso().getNombreCurso(), dniAlumno, idTipoExamen,
							dbObj.getObjTipoExamen().getDescripcion()));
				}
			}
		}
		LOG.info("Calificaciones: El proceso de validación de campos únicos finalizó con éxito");
	}

	public boolean existeAlumnoDNIByIDCurso(String dniAlumno, int id) throws Exception {
		String queryHQL = String.format("SELECT count(*) %s WHERE idCurso = %s AND objUsuarioAlumn.dni = %s", fromTable, id,
				dniAlumno);
		boolean existe = (long) this.hibernateTemplate.find(queryHQL).get(0) > 0;
		return existe;
	}
}
