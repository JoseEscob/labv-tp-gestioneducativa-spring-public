package frgp.utn.edu.ar.dao;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Curso;
import frgp.utn.edu.ar.dominio.CursosCalificaciones;

public interface ICursosCalificacionesDAO {

	public ArrayList<CursosCalificaciones> getAll() throws Exception;

	public CursosCalificaciones get(int id) throws Exception;

	public int getMax() throws Exception;

	public int insert(CursosCalificaciones obj) throws Exception;

	public boolean update(CursosCalificaciones obj) throws Exception;

	public boolean delete(int id) throws Exception;

	/**
	 * Verifica que no se repita la combinación de los campos
	 * 
	 * <ol>
	 * <li>idCurso</li>
	 * <li>dniAlumno</li>
	 * <li>idTipoExamen</li>
	 * </ol>
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public void validarCamposUnicos(CursosCalificaciones obj) throws Exception;

	public ArrayList<CursosCalificaciones> getAllByID(int id) throws Exception;
	ArrayList<String> getAllDNIByIDCurso(int id) throws Exception;
	ArrayList<CursosCalificaciones> getAllByDNIAlumno(String dniAlumno) throws Exception;
	ArrayList<CursosCalificaciones> getAllByDNIAlumnoIDCurso(String dniAlumno, int id) throws Exception;

	public boolean existeAlumnoDNIByIDCurso(String dniAlumno, int id) throws Exception;

	ArrayList<Curso> getAllCursosByDNIAlumno(String dniAlumno) throws Exception;
}
