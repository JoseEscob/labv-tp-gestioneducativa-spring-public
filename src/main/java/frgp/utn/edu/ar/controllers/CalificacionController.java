package frgp.utn.edu.ar.controllers;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import frgp.utn.edu.ar.dominio.CursosCalificaciones;
import frgp.utn.edu.ar.servicio.ICursosCalificacionesService;
import frgp.utn.edu.ar.servicio.ITipoExamenService;
import utils.InfoMessage;
import utils.ORSesion;
import utils.Utilitario;
import utils.constantes.Constantes;
import utils.excepciones.ValidacionException;

@Controller
public class CalificacionController {
	private String paginaJsp;
	@Autowired
	private ICursosCalificacionesService serviceCursosCalificaciones;

	@Autowired
	private ITipoExamenService serviceTipoExamen;

	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		this.serviceCursosCalificaciones = (ICursosCalificacionesService) ctx.getBean("serviceCursosCalificaciones");
		this.serviceTipoExamen = (ITipoExamenService) ctx.getBean("serviceTipoExamen");
	}

	@RequestMapping(value = "/inscribirAlumnos.html", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView inscribirAlumnos() {
		return null;
	}

	/*
	 * altaCalificacion.html
	 * 
	 * listaTiposExamen objCalificacion
	 */


	
}
