package frgp.utn.edu.ar.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import frgp.utn.edu.ar.dominio.Curso;
import frgp.utn.edu.ar.dominio.Usuario;
import frgp.utn.edu.ar.dominio.validacion.CalificacionForm;
import frgp.utn.edu.ar.dominio.validacion.CalificacionValidator;
import frgp.utn.edu.ar.dominio.TipoPeriodo;
import frgp.utn.edu.ar.servicio.ICursoService;
import frgp.utn.edu.ar.servicio.ICursosCalificacionesService;
import frgp.utn.edu.ar.servicio.ITipoPeriodoService;
import frgp.utn.edu.ar.servicio.IUsuarioService;
import utils.InfoMessage;
import utils.LOG;
import utils.ORSesion;
import utils.Utilitario;
import utils.constantes.Constantes;
import utils.excepciones.ValidacionException;

@Controller
public class CursoController {
	private String paginaJsp;
	@Autowired
	private ICursoService serviceCurso;
	@Autowired
	private ITipoPeriodoService serviceTipoPeriodo;
	@Autowired
	public IUsuarioService serviceUsuario;
	@Autowired
	private ICursosCalificacionesService serviceCursosCalificaciones;

	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		this.serviceCurso = (ICursoService) ctx.getBean("serviceCurso");
		this.serviceTipoPeriodo = (ITipoPeriodoService) ctx.getBean("serviceTipoPeriodo");
		this.serviceUsuario = (IUsuarioService) ctx.getBean("serviceUsuario");
		this.serviceCursosCalificaciones = (ICursosCalificacionesService) ctx.getBean("serviceCursosCalificaciones");
	}

	@RequestMapping(value = "")
	public void cargar() {

	}

	@RequestMapping("/altaMateriaCursoLoad.html")
	public ModelAndView altaMateriaCursoLoad() {
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {
			// 1- recuperar valores de la BBDD
			MV.addObject("listaPeriodos", serviceTipoPeriodo.getAll());
			int idCurso = serviceCurso.getMax() + 1;
			// 2- guardar la información recuperada en las variables
			Curso objMateriaCurso = new Curso();
			objMateriaCurso.setIdCurso(idCurso);
			// 3- pasar las variables al jsp a cargar
			MV.addObject("objMateriaCurso", objMateriaCurso);
			// 4- informar resultados
			message = String.format("Se cargaron los datos del formulario");
			objInfoMessage = new InfoMessage(true, message);
			paginaJsp = "MateriaAlta";
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
			LOG.warning(e.getMessage());
			paginaJsp = Constantes.indexJsp;
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(paginaJsp);
		return MV;
	}

	@RequestMapping(value = "/altaMateriaCurso.html", method = RequestMethod.POST)
	public ModelAndView altaMateriaCurso(String nombreCurso, int anio, int idPeriodo, String dniProfesor) {
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {
			// 1- validar datos cargados por JSP (Cliente)
			if (nombreCurso.isEmpty())
				throw new ValidacionException("Por favor complete el nombre de la materia/ curso");
			if (dniProfesor.isEmpty())
				throw new ValidacionException("Por favor complete el dni del profesor de la materia/ curso");

			Usuario objUsuarioProfe = serviceUsuario.getUsuarioByDNI(dniProfesor);
			if (objUsuarioProfe.getObjTipoUsuario().getIdTipoUsuario() != Constantes.idTipoUsuarioProfe)
				throw new ValidacionException("El DNI ingresado debe corresponder a un profesor");
			TipoPeriodo objTipoPeriodo = serviceTipoPeriodo.get(idPeriodo);
			// 2- guardar la información recuperada en las variables
			Curso objMateriaCurso = new Curso();
			objMateriaCurso.setNombreCurso(nombreCurso);
			objMateriaCurso.setAnio(anio);
			objMateriaCurso.setObjTipoPeriodo(objTipoPeriodo);
			objMateriaCurso.setObjUsuarioProfe(objUsuarioProfe);
			// 3- insertar en BBDD y verificar estado de transacción
			int idGenerado = serviceCurso.insert(objMateriaCurso);
			if (!(idGenerado > 0))
				throw new ValidacionException("SQL: Ocurrió un error al guardar la materia/ curso");
			// 4- informar resultados
			message = String.format("Se registró la materia con éxito. ID: " + idGenerado);
			objInfoMessage = new InfoMessage(true, message);
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
			LOG.warning(e.getMessage());
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(Constantes.indexJsp);
		return MV;
	}

	@RequestMapping(value = "/listarMateriasCursosProfe" + Constantes.html, method = RequestMethod.GET)
	public ModelAndView listarMateriasCursosProfe(HttpSession session) {
		// 0- declaracion de variables locales
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {
			// 1- Recuperar info de la sesión del usuario
			Usuario objUsuario = ORSesion.getUsuarioBySession(session);
			// 2- validar la informacion recuperada
			if (objUsuario == null)
				throw new ValidacionException("La sesión no fue iniciada");
			// 3- pasar las variables al jsp a cargar
			// TODO getAllByProfe - Usuario.listaCursos
			List<Curso> listaCursos = serviceCurso.getAllByDNIProfe(objUsuario.getDni());
			MV.addObject("listaCursos", listaCursos);
			// 4- informar resultados
			message = String.format("Se cargaron las materias del usuario");
			objInfoMessage = new InfoMessage(true, message);
			paginaJsp = "/MateriasListadoProfe"; // TODO preparar vista
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
			paginaJsp = Constantes.indexJsp;
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(paginaJsp);
		return MV;
	}

	@RequestMapping(value = "/cargarMateriasUsuarioLoad" + Constantes.html, method = RequestMethod.GET)
	public ModelAndView cargarMateriasUsuarioLoad(HttpSession session) {
		// 0- declaracion de variables locales
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {
			// 1- Recuperar info de la sesión del usuario
			Usuario objUsuario = ORSesion.getUsuarioBySession(session);
			// 2- validar la informacion recuperada
			if (objUsuario == null)
				throw new ValidacionException("La sesión no fue iniciada");
			// 3- pasar las variables al jsp a cargar
			List<Curso> listaCursos = serviceCurso.getAll();
			MV.addObject("listaCursos", listaCursos);
			// 4- informar resultados
			message = String.format("Se cargaron las materias del usuario");
			objInfoMessage = new InfoMessage(true, message);
			paginaJsp = "/MateriasListadoProfe"; // TODO preparar vista
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
			paginaJsp = Constantes.indexJsp;
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(paginaJsp);
		return MV;
	}

	@RequestMapping(value = "/calificacionListadoProfeLoad.html", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView calificacionListadoProfeLoad(int idCursoToViewCalificaciones, HttpSession session) {
		// 0- declaracion de variables locales
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {
			MV.addObject("objCurso", serviceCurso.get(idCursoToViewCalificaciones));
			MV.addObject("listaCursosCalificaciones",
					serviceCursosCalificaciones.getAllByID(idCursoToViewCalificaciones));

			message = String.format("Se cargaron las calificaciones del curso %d ", idCursoToViewCalificaciones);
			objInfoMessage = new InfoMessage(true, message);
			paginaJsp = "/CalificacionListadoProfe";
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
			paginaJsp = Constantes.indexJsp;
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(paginaJsp);
		return MV;
	}

	@RequestMapping(value = "/altaCalificacionMasivaLoad.html", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView altaCalificacionMasivaLoad(int idCursoToViewCalificaciones, HttpSession session) {
		// 0- declaracion de variables locales
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {
			// 1- cargar listas de objetos de la BBDD
			CalificacionForm objCalificacionForm = new CalificacionForm();
			objCalificacionForm
					.cargarListCalifHibernate(serviceCursosCalificaciones.getAllByID(idCursoToViewCalificaciones));
			// 2- guardar los valores obtenidos en las variables de la vista
			MV.addObject("objCurso", serviceCurso.get(idCursoToViewCalificaciones));
			MV.addObject("objCalificacionForm", objCalificacionForm);

			message = String.format("Se cargaron las calificaciones del curso %d ", idCursoToViewCalificaciones);
			objInfoMessage = new InfoMessage(true, message);
			paginaJsp = "/CalificacionAltaMasiva";
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
			paginaJsp = Constantes.indexJsp;
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(paginaJsp);
		return MV;
	}

	@RequestMapping(value = "/altaCalificacionMasiva.html", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView altaCalificacionMasiva(
			@ModelAttribute("objCalificacionForm") CalificacionForm objCalificacionForm) {
		// 0- declaracion de variables locales
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {

			for (CalificacionValidator item : objCalificacionForm.getListaCalificaciones()) {
				Utilitario.validarObjetoClasePorValidator(item);
				System.out.println(item.toString());
			}

			/*
			 * message = String.format("Se cargaron las calificaciones del curso %d ",
			 * idCursoToViewCalificaciones); objInfoMessage = new InfoMessage(true,
			 * message); paginaJsp = "/CalificacionAltaMasiva";
			 */
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
			paginaJsp = Constantes.indexJsp;
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(paginaJsp);
		return MV;
	}

}
