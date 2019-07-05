package frgp.utn.edu.ar.controllers;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RequestWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import frgp.utn.edu.ar.dominio.Curso;
import frgp.utn.edu.ar.dominio.CursosCalificaciones;
import frgp.utn.edu.ar.dominio.Usuario;
import frgp.utn.edu.ar.servicio.ICursoService;
import frgp.utn.edu.ar.servicio.ICursosCalificacionesService;
import frgp.utn.edu.ar.servicio.ITipoExamenService;
import utils.InfoMessage;
import utils.LOG;
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
		
	}
	
	/*
	 * altaCalificacion.html
	 * 
	 * listaTiposExamen objCalificacion
	 */

	@RequestMapping(value = "/modificarCalificacionUsuarioLoad.html", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView modificarCalificacionUsuarioLoad(int idCursoCalifToUpdate, HttpSession session) {
		// 0- declaracion de variables locales
		ModelAndView MV = new ModelAndView();
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		try {
			// 1- Verificar permisos del usuario logueado
			if (ORSesion.getUsuarioBySession(session).getObjTipoUsuario()
					.getIdTipoUsuario() == Constantes.idTipoUsuarioAlumn)
				throw new ValidacionException("El usuario alumno no puede ingresar a esta funcionalidad");
			// 2- Ejecutar transacción DB y devolver las respuestas
			CursosCalificaciones objCalificacion = serviceCursosCalificaciones.get(idCursoCalifToUpdate);
			if (objCalificacion == null)
				throw new ValidacionException("No se encontró la calificación con ID: " + idCursoCalifToUpdate);

			// 3- Guardar valor obtenido
			MV.addObject("objCalificacion", objCalificacion);
			MV.addObject("listaTiposExamen", serviceTipoExamen.getAll());

			// 4- informar resultados
			paginaJsp = "CalificacionModif";
			message = String.format("Se cargaron los datos de la calificación con ID: %d", idCursoCalifToUpdate);
			objInfoMessage = new InfoMessage(true, message);
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
			paginaJsp = Constantes.indexJsp;
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(paginaJsp);
		return MV;
	}

}
