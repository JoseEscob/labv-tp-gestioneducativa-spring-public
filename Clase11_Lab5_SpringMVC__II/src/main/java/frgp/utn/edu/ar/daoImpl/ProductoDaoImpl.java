package frgp.utn.edu.ar.daoImpl;

import java.util.ArrayList;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import frgp.utn.edu.ar.dao.ProductoDao;
import frgp.utn.edu.ar.dominio.Producto;


public class ProductoDaoImpl implements ProductoDao{


	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void insertarProducto(Producto producto) {
		this.hibernateTemplate.save(producto);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Producto obtenerProductoPorId(Integer idProducto) {
		return this.hibernateTemplate.get(Producto.class, idProducto);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<Producto> obtenerProductos() {
		return (ArrayList<Producto>) this.hibernateTemplate.find("select p from Producto p");	
	} 

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void eliminarProducto(Integer idProducto) {
		Producto prod = new Producto();
		prod.setIdProducto(idProducto);
		this.hibernateTemplate.delete(prod);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void actualizarProducto(Producto producto) {
		this.hibernateTemplate.update(producto);
		
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Integer obtenerMaximoIdProducto() {
		Integer a=0;
		if(obtenerProductos().size()>0)
		a = (Integer) this.hibernateTemplate.find("select MAX(p.idProducto) from Producto p").get(0);
		return a;
	}

}
