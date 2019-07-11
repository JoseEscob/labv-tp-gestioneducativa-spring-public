package frgp.utn.edu.ar.controllers;

import java.util.ArrayList;
import java.util.Date;
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
import frgp.utn.edu.ar.dominio.CursosCalificaciones;
import frgp.utn.edu.ar.dominio.Usuario;
import frgp.utn.edu.ar.dominio.validacion.CalificacionForm;
import frgp.utn.edu.ar.dominio.validacion.CalificacionValidator;
import frgp.utn.edu.ar.dominio.TipoPeriodo;
import frgp.utn.edu.ar.servicio.ICursoService;
import frgp.utn.edu.ar.servicio.ICursosCalificacionesService;
import frgp.utn.edu.ar.servicio.ITipoExamenService;
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
	@Autowired
	private ITipoExamenService serviceTipoExamen;

	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		this.serviceCurso = (ICursoService) ctx.getBean("serviceCurso");
		this.serviceTipoPeriodo = (ITipoPeriodoService) ctx.getBean("serviceTipoPeriodo");
		this.serviceUsuario = (IUsuarioService) ctx.getBean("serviceUsuario");
		this.serviceCursosCalificaciones = (ICursosCalificacionesService) ctx.getBean("serviceCursosCalificaciones");
		this.serviceTipoExamen = (ITipoExamenService) ctx.getBean("serviceTipoExamen");
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
			List<Curso> listaCursos = serviceCurso.getAllByDNIProfe(objUsuario.getDni());
			MV.addObject("listaCursos", listaCursos);
			// 4- informar resultados
			message = String.format("Se cargaron las materias del usuario");
			objInfoMessage = new InfoMessage(true, message);
			paginaJsp = "/MateriasListadoProfe";
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
			paginaJsp = "/MateriasListadoProfe";
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
			paginaJsp = Constantes.indexJsp;
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(paginaJsp);
		return MV;
	}

	/// ******************* CALIFICACIONES - INDIVIDUAL ******************* ///

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

	@RequestMapping(value = "/modificarCalificacionByIDLoad.html", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView modificarCalificacionByIDLoad(int idCursoCalifToUpdate, HttpSession session) {
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

	@RequestMapping(value = "/modificarCalificacionByID.html", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView modificarCalificacionByID(HttpSession session, CalificacionValidator objCalificacionValidator) {
		// 0- declaracion de variables locales
		ModelAndView MV = new ModelAndView();
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		try {
			// 1- Verificar permisos del usuario logueado
			if (ORSesion.getUsuarioBySession(session).getObjTipoUsuario()
					.getIdTipoUsuario() == Constantes.idTipoUsuarioAlumn)
				throw new ValidacionException("El usuario alumno no puede ingresar a esta funcionalidad");
			Utilitario.validarObjetoClasePorValidator(objCalificacionValidator);
			// 2- Ejecutar transacción DB y devolver las respuestas
			CursosCalificaciones objCalificacion = obtenerCalificacionPorObjetoValidator(objCalificacionValidator);
			objCalificacion.setFechaUltModif(Utilitario.getCurrentDateAndHoursJavaUtil());
			if (!serviceCursosCalificaciones.update(objCalificacion))
				throw new ValidacionException(
						"SQL: Ocurrió un error al guardar la calificación " + objCalificacion.getIdCursoCalif());
			// 3- Guardar valor obtenido
			MV.addObject("objCalificacion", objCalificacion);
			MV.addObject("listaTiposExamen", serviceTipoExamen.getAll());

			// 4- informar resultados
			paginaJsp = "CalificacionModif";
			message = String.format("Se modificaron los datos de la calificación con ID: %d",
					objCalificacion.getIdCursoCalif());
			objInfoMessage = new InfoMessage(true, message);
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
			paginaJsp = Constantes.indexJsp;
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(paginaJsp);
		return MV;
	}

	/// ******************* CALIFICACIONES - MASIVA ******************* ///
	// TODO: definir carga de alta jsp de calificaciones
	@RequestMapping(value = "/altaCalificacionMasivaLoad.html", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView altaCalificacionMasivaLoad(int idCursoToViewCalificaciones, HttpSession session) {
		// 0- declaracion de variables locales
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {
			// 1- cargar listas de objetos de la BBDD
			ArrayList<CursosCalificaciones> listaCalificaciones = new ArrayList<CursosCalificaciones>();
			ArrayList<String> listaDNIAlumno = serviceCursosCalificaciones
					.getAllDNIByIDCurso(idCursoToViewCalificaciones);
			// TODO obtener los Usuarios por idCurso así se muestra nombre y apellido

//			for(String dniAlumno : listaDNIAlumno) {
//				CursosCalificaciones objCalificacion = new CursosCalificaciones();
//				Usuario objUsuario = new Usuario();
//				objUsuario.setDni(dniAlumno);
//				objCalificacion.setObjUsuarioAlumn(objUsuario);		
//				objCalificacion.setFechaCalif(Utilitario.getCurrentDateAndHoursJavaUtil());
//				listaCalificaciones.add(objCalificacion);
//			}

			// 2- guardar los valores obtenidos en las variables de la vista
			MV.addObject("objCurso", serviceCurso.get(idCursoToViewCalificaciones));
			MV.addObject("listaTiposExamen", serviceTipoExamen.getAll());
			MV.addObject("listaDNIAlumno", listaDNIAlumno);
			
			CalificacionForm objCalificacionForm = new CalificacionForm();
			objCalificacionForm
			.cargarListCalifHibernate(serviceCursosCalificaciones.getAllByID(idCursoToViewCalificaciones));
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
			@ModelAttribute("objCalificacionForm") CalificacionForm objCalificacionForm, int idTipoExamenSeleccionado) {
		// 0- declaracion de variables locales
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {
			// 1- declaración de variables
			int cantTotal = objCalificacionForm.getListaCalificaciones().size();
			int cantModif = 0;
			ArrayList<CursosCalificaciones> listaCalificaciones = new ArrayList<CursosCalificaciones>();
			// 2- validar campos
			for (CalificacionValidator item : objCalificacionForm.getListaCalificaciones()) {
				Utilitario.validarObjetoClasePorValidator(item);
				item.setIdTipoExamen(idTipoExamenSeleccionado);
				listaCalificaciones.add(obtenerCalificacionPorObjetoValidator(item));
				cantModif++;
				LOG.info(String.format("Se verificó %d de %d de calificaciones", cantModif, cantTotal));
			}
			// 3- almacenado de registros validados en BBDD
			cantModif = 0;
			Date fechaCreacion = Utilitario.getCurrentDateAndHoursJavaUtil();
			for (CursosCalificaciones objCalificaciones : listaCalificaciones) {
				objCalificaciones.setFechaCalif(fechaCreacion);
				objCalificaciones.setFechaUltModif(fechaCreacion);
				int idGenerado = serviceCursosCalificaciones.insert(objCalificaciones);
				if (!(idGenerado > 0))
					throw new ValidacionException(
							"SQL: Ocurrió un error al guardar la calificación " + objCalificaciones.getIdCursoCalif());

				cantModif++;
			}
			// 4- informar los resultados obtenidos
			message = String.format("Se guardaron las calificaciones. Registros modificados %d de %d ", cantModif,
					cantTotal);
			objInfoMessage = new InfoMessage(true, message);
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(Constantes.indexJsp);
		return MV;
	}

	// TODO: definir controles deshabilitados en los jsp de modificación
	@RequestMapping(value = "/modificarCalificacionMasivaLoad.html", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView modificarCalificacionMasivaLoad(int idCursoToViewCalificaciones, HttpSession session) {
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
			paginaJsp = "/CalificacionModifMasiva";
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
			paginaJsp = Constantes.indexJsp;
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(paginaJsp);
		return MV;
	}

	@RequestMapping(value = "/modificarCalificacionMasiva.html", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView modificarCalificacionMasiva(
			@ModelAttribute("objCalificacionForm") CalificacionForm objCalificacionForm) {
		// 0- declaracion de variables locales
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {
			// 1- declaración de variables
			int cantTotal = objCalificacionForm.getListaCalificaciones().size();
			int cantModif = 0;
			ArrayList<CursosCalificaciones> listaCalificaciones = new ArrayList<CursosCalificaciones>();
			// 2- validar campos
			for (CalificacionValidator item : objCalificacionForm.getListaCalificaciones()) {
				Utilitario.validarObjetoClasePorValidator(item);
				listaCalificaciones.add(obtenerCalificacionPorObjetoValidator(item));
				cantModif++;
				LOG.info(String.format("Se verificó %d de %d de calificaciones", cantModif, cantTotal));
			}
			// 3- almacenado de registros validados en BBDD
			cantModif = 0;
			for (CursosCalificaciones objCalificaciones : listaCalificaciones) {
				objCalificaciones.setFechaUltModif(Utilitario.getCurrentDateAndHoursJavaUtil());
				if (!serviceCursosCalificaciones.update(objCalificaciones))
					throw new ValidacionException(
							"SQL: Ocurrió un error al guardar la calificación " + objCalificaciones.getIdCursoCalif());

				cantModif++;
			}
			// 4- informar los resultados obtenidos
			message = String.format("Se guardaron las calificaciones. Registros modificados %d de %d ", cantModif,
					cantTotal);
			objInfoMessage = new InfoMessage(true, message);
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(Constantes.indexJsp);
		return MV;
	}

	private CursosCalificaciones obtenerCalificacionPorObjetoValidator(CalificacionValidator objCalifValidator)
			throws Exception {
		CursosCalificaciones objCalif = new CursosCalificaciones();
		objCalif.setIdCursoCalif(objCalifValidator.getIdCursoCalif());
		objCalif.setNota(objCalifValidator.getNota());
		objCalif.setFechaCalif(objCalifValidator.getFechaCalif());
		objCalif.setFechaUltModif(objCalifValidator.getFechaUltModif());
		objCalif.setObjCurso(serviceCurso.get(objCalifValidator.getIdCurso()));
		objCalif.setObjTipoExamen(serviceTipoExamen.get(objCalifValidator.getIdTipoExamen()));
		objCalif.setObjUsuarioAlumn(serviceUsuario.getUsuarioByDNI(objCalifValidator.getDni()));

		return objCalif;
	}

}
