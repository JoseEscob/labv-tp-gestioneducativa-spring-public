package frgp.utn.edu.ar.servicio;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Curso;
import frgp.utn.edu.ar.dominio.TipoPeriodo;

public interface ICursoService {
	public ArrayList<Curso> getAll() throws Exception;

	public Curso get(int id) throws Exception;

	public int getMax() throws Exception;

	public int insert(Curso obj) throws Exception;

	public boolean update(Curso obj) throws Exception;

	public boolean delete(int id) throws Exception;
	
	public ArrayList<Curso> getAllByDNIProfe(String dniProfesor) throws Exception;

	ArrayList<TipoPeriodo> getAllDistinctTipoPeriodo() throws Exception;
	ArrayList<Integer> getAllDistinctAnio() throws Exception;

	ArrayList<Curso> getAllByNombreCursoBuscado(String nombreCurso) throws Exception;
	ArrayList<Curso> getAllByFiltroPeriodoAnio(int idTipoPeriodo, int anio) throws Exception;
}
