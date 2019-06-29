package frgp.utn.edu.ar.controllers;

import javax.servlet.ServletConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.PathVariable;
import frgp.utn.edu.ar.dominio.Usuario;
import frgp.utn.edu.ar.servicio.IService;
import utils.LOG;

@Controller
public class UsuarioController {
	@Autowired
	public IService<Usuario> service;

	@SuppressWarnings("unchecked")
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		
		this.service = (IService<Usuario>) ctx.getBean("serviceUsuarioBeanX");
	}
	
	@RequestMapping("/admListarUsuarios.html")
	public ModelAndView admListarUsuarios() {
		try {
			ModelAndView MV = new ModelAndView();
			MV.addObject("listaUsuarios", this.service.getAll());
			MV.setViewName("admListarUsuarios");
			return MV;
		} catch (Exception e) {
			LOG.warning(e.getMessage());
			return null;
		}
	}
}
