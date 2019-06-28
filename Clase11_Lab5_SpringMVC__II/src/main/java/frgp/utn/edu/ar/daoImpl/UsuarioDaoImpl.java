package frgp.utn.edu.ar.daoImpl;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.UsuarioDao;
import frgp.utn.edu.ar.dominio.Usuarios;

public class UsuarioDaoImpl implements UsuarioDao {

	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void insertarUsuario(Usuarios usuario) {
		this.hibernateTemplate.save(usuario);
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Usuarios obtenerUsuarioPorNombre(String nombreUser) {
		return this.hibernateTemplate.get(Usuarios.class, nombreUser);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<Usuarios> obtenerUsuarios() {
		return (ArrayList<Usuarios>) this.hibernateTemplate.loadAll(Usuarios.class);
	}

	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void eliminarUsuario(Integer idUsuario) {
		Usuarios user = new Usuarios();
		user.setId(idUsuario);
		this.hibernateTemplate.delete(user);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void actualizarUsuario(Usuarios persona) {
		this.hibernateTemplate.update(persona);
	}


}
