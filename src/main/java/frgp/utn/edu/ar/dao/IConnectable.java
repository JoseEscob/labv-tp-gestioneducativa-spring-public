package frgp.utn.edu.ar.dao;
import java.util.ArrayList;

public interface IConnectable<T> {

	public ArrayList<T> getAll() throws Exception;

	public T get(int id) throws Exception;

	//public int getCount() throws Exception;

	public int getMax() throws Exception;

	public int insert(T obj) throws Exception;

	public boolean update(T obj) throws Exception;

	public boolean remove(int id) throws Exception;

	public int delete(T obj) throws Exception;

}
