package frgp.utn.edu.ar.servicioImpl;

import java.util.ArrayList;

import frgp.utn.edu.ar.dao.ICursosCalificacionesDAO;
import frgp.utn.edu.ar.dominio.Curso;
import frgp.utn.edu.ar.dominio.CursosCalificaciones;
import frgp.utn.edu.ar.servicio.ICursosCalificacionesService;

public class CursosCalificacionesServiceImpl implements ICursosCalificacionesService {
	private ICursosCalificacionesDAO dataAccess = null;

	public void setDataAccess(ICursosCalificacionesDAO dataAccess) {
		this.dataAccess = dataAccess;
	}

	@Override
	public ArrayList<CursosCalificaciones> getAll() throws Exception {
		return dataAccess.getAll();
	}

	@Override
	public CursosCalificaciones get(int id) throws Exception {
		return dataAccess.get(id);
	}

	@Override
	public int getMax() throws Exception {
		return dataAccess.getMax();
	}

	@Override
	public int insert(CursosCalificaciones obj) throws Exception {
		return dataAccess.insert(obj);
	}

	@Override
	public boolean update(CursosCalificaciones obj) throws Exception {
		return dataAccess.update(obj);
	}

	@Override
	public boolean delete(int id) throws Exception {
		return dataAccess.delete(id);
	}

	@Override
	public ArrayList<CursosCalificaciones> getAllByID(int id) throws Exception {
		return dataAccess.getAllByID(id);
	}

	@Override
	public ArrayList<String> getAllDNIByIDCurso(int id) throws Exception {
		return dataAccess.getAllDNIByIDCurso(id);
	}

	@Override
	public void validarCamposUnicos(CursosCalificaciones obj) throws Exception {
		dataAccess.validarCamposUnicos(obj);
	}

	@Override
	public ArrayList<CursosCalificaciones> getAllByDNIAlumno(String dniAlumno) throws Exception {
		return dataAccess.getAllByDNIAlumno(dniAlumno);
	}

	@Override
	public ArrayList<CursosCalificaciones> getAllByDNIAlumnoIDCurso(String dniAlumno, int id) throws Exception {
		return dataAccess.getAllByDNIAlumnoIDCurso(dniAlumno, id);
	}

	@Override
	public boolean existeAlumnoDNIByIDCurso(String dniAlumno, int id) throws Exception {
		return dataAccess.existeAlumnoDNIByIDCurso(dniAlumno, id);
	}

	@Override
	public ArrayList<Curso> getAllCursosByDNIAlumno(String dniAlumno) throws Exception {
		return dataAccess.getAllCursosByDNIAlumno(dniAlumno);
	}

}
