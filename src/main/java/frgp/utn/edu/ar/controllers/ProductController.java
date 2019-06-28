package frgp.utn.edu.ar.controllers;

import javax.servlet.ServletConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import frgp.utn.edu.ar.dominio.Producto;
import frgp.utn.edu.ar.servicio.ProductoServicio;
import frgp.utn.edu.ar.servicio.TipoProductoServicio;

@Controller
public class ProductController {

	
	@Autowired
	public  ProductoServicio serviceProducto;
	@Autowired
	public  TipoProductoServicio serviceTipoProducto;
	
	
	
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		
		this.serviceProducto = (ProductoServicio) ctx.getBean("serviceProductoBean");
		this.serviceTipoProducto = (TipoProductoServicio) ctx.getBean("serviceTipoProductoBean");
	}
	
	@RequestMapping("abrirAltaProductos.html")
	public ModelAndView abrirAltaProducto(){
		ModelAndView MV = new ModelAndView();
		
		//Carga los tipos de productos
		MV.addObject("listaTiposProductos", serviceTipoProducto.obtenerTipoProductos());
		
		//Carga el numero del ID del proximo articulo
		MV.addObject("IdProducto", serviceProducto.obtenerMaximoIdProducto()+1);
		MV.setViewName("AltaProductos"); 
		return MV;
	}
	
	@RequestMapping("ListarProductos.html")
	public ModelAndView listarProductos(){
		ModelAndView MV = new ModelAndView();
		MV.setViewName("ListadoProductos"); 
		MV.addObject("listaProductos", serviceProducto.obtenerProductos());
		return MV;
	}
	@RequestMapping("altaProducto.html")
	public ModelAndView guardarProducto(String txtNombre, float txtPrecio, Integer tipoA){

		ModelAndView MV = new ModelAndView();
		Producto producto = new Producto();
		producto.setNombre(txtNombre);
		producto.setPrecio(txtPrecio);
		//Por parámetro se envía el tipo de producto, por ende obtengo el objeto completo
		producto.setTipo(this.serviceTipoProducto.obtenerTipoProducto(tipoA));
		serviceProducto.insertarProducto(producto);
		MV.setViewName("AltaProductos"); 
		
		//Carga el numero del ID del proximo articulo
		MV.addObject("IdProducto", serviceProducto.obtenerMaximoIdProducto()+1);		
		
		//Carga los tipos de productos
		MV.addObject("listaTiposProductos", serviceTipoProducto.obtenerTipoProductos());
		return MV;
	}
}
