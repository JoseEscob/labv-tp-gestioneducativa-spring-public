package frgp.utn.edu.ar.dao;

import java.util.ArrayList;

import frgp.utn.edu.ar.dominio.Curso;

public interface ICursoDAO {

	public ArrayList<Curso> getAll() throws Exception;

	public Curso get(int id) throws Exception;

	public int getMax() throws Exception;

	public int insert(Curso obj) throws Exception;

	public boolean update(Curso obj) throws Exception;

	public boolean delete(int id) throws Exception;
}
