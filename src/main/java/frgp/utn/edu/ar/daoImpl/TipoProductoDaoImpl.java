package frgp.utn.edu.ar.daoImpl;

import java.util.ArrayList;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import frgp.utn.edu.ar.dao.TipoProductoDao;
import frgp.utn.edu.ar.dominio.TipoProducto;

public class TipoProductoDaoImpl implements TipoProductoDao{

	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public TipoProducto obtenerTipoProductoPorId(Integer idTipoProducto) {
		return this.hibernateTemplate.get(TipoProducto.class, idTipoProducto);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<TipoProducto> obtenerTipoProductos() {
		return (ArrayList<TipoProducto>) this.hibernateTemplate.loadAll(TipoProducto.class);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void insertarTipoProducto(TipoProducto tipoProducto) {
		this.hibernateTemplate.save(tipoProducto);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Integer obtenerMaximoTipoProducto() {
		
		Integer a=0;
		if(obtenerTipoProductos().size()>0)
			a= (Integer)this.hibernateTemplate.find("select MAX(p.id) from TipoProducto p").get(0);
		 return a;
	}

	
}
