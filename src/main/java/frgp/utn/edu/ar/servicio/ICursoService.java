package frgp.utn.edu.ar.servicio;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Curso;

public interface ICursoService {
	public ArrayList<Curso> getAll() throws Exception;

	public Curso get(int id) throws Exception;

	public int getMax() throws Exception;

	public int insert(Curso obj) throws Exception;

	public boolean update(Curso obj) throws Exception;

	public boolean delete(int id) throws Exception;
	
	public ArrayList<Curso> getAllByDNIProfe(String dniProfesor) throws Exception;

}
