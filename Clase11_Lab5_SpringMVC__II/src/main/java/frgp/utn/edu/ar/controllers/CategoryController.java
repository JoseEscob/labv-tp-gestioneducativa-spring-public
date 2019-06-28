package frgp.utn.edu.ar.controllers;

import javax.servlet.ServletConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import frgp.utn.edu.ar.dominio.TipoProducto;
import frgp.utn.edu.ar.servicio.TipoProductoServicio;

@Controller
public class CategoryController {

	@Autowired
	public  TipoProductoServicio serviceTipoProducto;
	
	
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		this.serviceTipoProducto = (TipoProductoServicio) ctx.getBean("serviceTipoProductoBean");
	}
	
	@RequestMapping("abrirAltaCategorias.html")
	public ModelAndView abrirAltaCategorias(){
		ModelAndView MV = new ModelAndView();
		//Envia el número de la próxima categoría
		MV.addObject("IdCategoria", serviceTipoProducto.obtenerMaximoIdProducto()+1);
		MV.setViewName("AltaCategorias"); 
		return MV;
	}
	
	@RequestMapping("altaCategoria.html")
	public ModelAndView AltaCategorias(String txtNombre){
		
		//Agrega la categoria
		TipoProducto x = new TipoProducto();
		x.setNombre(txtNombre);
		serviceTipoProducto.insertarTipoProducto(x);
		
		ModelAndView MV = new ModelAndView();
		//Envia el número de la próxima categoría		
		MV.addObject("IdCategoria", serviceTipoProducto.obtenerMaximoIdProducto()+1);
		MV.setViewName("AltaCategorias"); 
		return MV;
	}
	
}
