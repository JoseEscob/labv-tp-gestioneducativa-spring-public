package frgp.utn.edu.ar.daoImpl;

import java.util.ArrayList;

import frgp.utn.edu.ar.dao.IConnectable;
import frgp.utn.edu.ar.dominio.Usuario;
import utils.constantes.ConstantesDAO;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class UsuarioDAOImplX implements IConnectable<Usuario> {
	private HibernateTemplate hibernateTemplate = null;
	private final String fromTable = String.format("FROM %s", ConstantesDAO.Usuario);

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public ArrayList<Usuario> getAll() throws Exception {
		return (ArrayList<Usuario>) this.hibernateTemplate.loadAll(Usuario.class);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Usuario get(int id) throws Exception {
		return this.hibernateTemplate.get(Usuario.class, id);
	}

	@Override
	public int getMax() throws Exception {
		int maxID = 0;
		if (getAll().size() > 0) {
			String queryHQL = String.format("SELECT MAX(user.idUsuario) %s user", fromTable);
			maxID = (int) this.hibernateTemplate.find(queryHQL).get(0);
		}
		return maxID;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int insert(Usuario obj) throws Exception {
		int idGenerado = (int) this.hibernateTemplate.save(obj);
		return idGenerado;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean update(Usuario obj) throws Exception {
		boolean estado = false;
		this.hibernateTemplate.update(obj);
		estado = true;
		return estado;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean remove(int idUsuario) throws Exception {
		boolean estado = false;
		Usuario obj = new Usuario();
		obj.setIdUsuario(idUsuario);
		this.hibernateTemplate.delete(obj);
		estado = true;
		return estado;
	}

	@Override
	public int delete(Usuario obj) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
