package frgp.utn.edu.ar.controllers;

import java.util.ArrayList;
import java.util.Date;

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

import frgp.utn.edu.ar.dominio.CursosCalificaciones;
import frgp.utn.edu.ar.dominio.Usuario;
import frgp.utn.edu.ar.dominio.validacion.CalificacionForm;
import frgp.utn.edu.ar.dominio.validacion.CalificacionValidator;
import frgp.utn.edu.ar.servicio.ICursoService;
import frgp.utn.edu.ar.servicio.ICursosCalificacionesService;
import frgp.utn.edu.ar.servicio.ITipoExamenService;
import frgp.utn.edu.ar.servicio.IUsuarioService;
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

	@Autowired
	private ICursoService serviceCurso;

	@Autowired
	public IUsuarioService serviceUsuario;

	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		this.serviceCursosCalificaciones = (ICursosCalificacionesService) ctx.getBean("serviceCursosCalificaciones");
		this.serviceTipoExamen = (ITipoExamenService) ctx.getBean("serviceTipoExamen");
		this.serviceCurso = (ICursoService) ctx.getBean("serviceCurso");
		this.serviceUsuario = (IUsuarioService) ctx.getBean("serviceUsuario");
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
			MV.addObject("objInfoMessage", objInfoMessage);
			paginaJsp = Constantes.indexJsp;
		}
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

	@RequestMapping(value = "/altaCalificacionLoad.html", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView altaCalificacionLoad(int idCursoToViewCalificaciones, HttpSession session) {
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

			CursosCalificaciones objCalificacion = new CursosCalificaciones();
			objCalificacion.setObjCurso(serviceCurso.get(idCursoToViewCalificaciones));
			objCalificacion.setFechaCalif(Utilitario.getCurrentDateAndHoursJavaUtil());
			MV.addObject("objCalificacion", objCalificacion);
			MV.addObject("listaTiposExamen", serviceTipoExamen.getAll());

			// 3- informar resultados
			paginaJsp = "CalificacionAlta";
			message = String.format("Se cargaron los datos del formulario calificación ");
			objInfoMessage = new InfoMessage(true, message);
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
			paginaJsp = Constantes.indexJsp;
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(paginaJsp);
		return MV;
	}

	@RequestMapping(value = "/altaCalificacion.html", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView altaCalificacion(HttpSession session, CalificacionValidator objCalificacionValidator) {
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
			// 1.2- Verificar existencia de alumno para esa materia/ curso
			String dniAlumno = objCalificacionValidator.getDni();
			int idCurso = objCalificacionValidator.getIdCurso();
			if (!serviceCursosCalificaciones.existeAlumnoDNIByIDCurso(dniAlumno, idCurso))
				throw new ValidacionException(String.format(
						"El alumno con DNI [%s] no está registrado en el curso con ID: %d", dniAlumno, idCurso));
			// 2- Ejecutar transacción DB y devolver las respuestas
			CursosCalificaciones objCalificacion = obtenerCalificacionPorObjetoValidator(objCalificacionValidator);
			Date fechaCreacion = Utilitario.getCurrentDateAndHoursJavaUtil();
			objCalificacion.setFechaCalif(fechaCreacion);
			objCalificacion.setFechaUltModif(fechaCreacion);
			serviceCursosCalificaciones.validarCamposUnicos(objCalificacion);
			int idGenerado = serviceCursosCalificaciones.insert(objCalificacion);
			if (!(idGenerado > 0))
				throw new ValidacionException("SQL: Ocurrió un error al guardar la calificación ");
			// 3- Guardar valor obtenido
			// 4- informar resultados
			message = String.format("Se creó exitosamente la calificación con ID: %d", idGenerado);
			objInfoMessage = new InfoMessage(true, message);
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(Constantes.indexJsp);
		return MV;
	}

	@RequestMapping(value = "/inscripcionAlumnoLoad.html", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView inscripcionAlumnoLoad(HttpSession session, int idCurso) {
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {
			Utilitario.verificarQueElUsuarioLogueadoSeaAdmin(session);
			MV.addObject("objMateriaCurso", serviceCurso.get(idCurso));
			paginaJsp = "MateriaInscripcionAlumno";
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
			paginaJsp = Constantes.indexJsp;
			MV.addObject("objInfoMessage", objInfoMessage);
		}
		MV.setViewName(paginaJsp);
		return MV;
	}

	@RequestMapping(value = "/inscripcionAlumnoMasiva.html", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView inscripcionAlumnoMasiva(HttpSession session, int idCurso,
			@ModelAttribute(value = "dniAlumno") frgp.utn.edu.ar.dominio.validacion.ListaDNIForm objListaDNIForm) {
		// 0- declaracion de variables locales
		ModelAndView MV = new ModelAndView();
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		try {
			// 1- Verificar permisos del usuario logueado
			if (ORSesion.getUsuarioBySession(session).getObjTipoUsuario()
					.getIdTipoUsuario() == Constantes.idTipoUsuarioAlumn)
				throw new ValidacionException("El usuario alumno no puede ingresar a esta funcionalidad");
			// 1.2- Verificar existencia de alumno para esa materia/ curso
			int cantRegistrosExitosos = 0;
			for (String dniAlumnoInsert : objListaDNIForm.getDniAlumno()) {
				if (serviceCursosCalificaciones.existeAlumnoDNIByIDCurso(dniAlumnoInsert, idCurso))
					throw new ValidacionException(
							String.format("El alumno con DNI [%s] ya se encuentra registrado en el curso con ID: %d",
									dniAlumnoInsert, idCurso));
				// 1.3- Verificar que el DNI ingresado sea de un alumno
				Usuario objUsuarioAlumno = serviceUsuario.getUsuarioByDNI(dniAlumnoInsert);
				if (objUsuarioAlumno.getObjTipoUsuario().getIdTipoUsuario() != Constantes.idTipoUsuarioAlumn)
					throw new ValidacionException("El DNI ingresado debe corresponder a un alumno");
				// 2- Ejecutar transacción DB y devolver las respuestas
				CursosCalificaciones objCalificacion = new CursosCalificaciones();
				objCalificacion.setObjCurso(serviceCurso.get(idCurso));
				objCalificacion.setObjUsuarioAlumn(serviceUsuario.getUsuarioByDNI(dniAlumnoInsert));
				if (!(serviceCursosCalificaciones.insert(objCalificacion) > 0))
					throw new ValidacionException("SQL: Ocurrió un error al guardar la calificación ");

				cantRegistrosExitosos++;
			}
			// 4- informar resultados
			message = String.format("Se registraron las inscripciones de %d alumno/s", cantRegistrosExitosos);
			objInfoMessage = new InfoMessage(true, message);
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(Constantes.indexJsp);
		return MV;
	}

	@RequestMapping(value = "/inscripcionAlumno.html", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView inscripcionAlumno(HttpSession session, int idCurso, String dniAlumno) {
		// 0- declaracion de variables locales
		ModelAndView MV = new ModelAndView();
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		try {
			// 1- Verificar permisos del usuario logueado
			if (ORSesion.getUsuarioBySession(session).getObjTipoUsuario()
					.getIdTipoUsuario() == Constantes.idTipoUsuarioAlumn)
				throw new ValidacionException("El usuario alumno no puede ingresar a esta funcionalidad");
			// 1.2- Verificar existencia de alumno para esa materia/ curso
			if (serviceCursosCalificaciones.existeAlumnoDNIByIDCurso(dniAlumno, idCurso))
				throw new ValidacionException(
						String.format("El alumno con DNI [%s] ya se encuentra registrado en el curso con ID: %d",
								dniAlumno, idCurso));
			// 1.3- Verificar que el DNI ingresado sea de un alumno
			Usuario objUsuarioAlumno = serviceUsuario.getUsuarioByDNI(dniAlumno);
			if (objUsuarioAlumno.getObjTipoUsuario().getIdTipoUsuario() != Constantes.idTipoUsuarioAlumn)
				throw new ValidacionException("El DNI ingresado debe corresponder a un alumno");
			// 2- Ejecutar transacción DB y devolver las respuestas
			CursosCalificaciones objCalificacion = new CursosCalificaciones();
			objCalificacion.setObjCurso(serviceCurso.get(idCurso));
			objCalificacion.setObjUsuarioAlumn(serviceUsuario.getUsuarioByDNI(dniAlumno));
			int idGenerado = serviceCursosCalificaciones.insert(objCalificacion);
			if (!(idGenerado > 0))
				throw new ValidacionException("SQL: Ocurrió un error al guardar la calificación ");
			// 4- informar resultados
			message = String.format("Se creó exitosamente la inscripción con ID: %d", idGenerado);
			objInfoMessage = new InfoMessage(true, message);
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(Constantes.indexJsp);
		return MV;
	}

	@RequestMapping(value = "/bajaAlumnoMateriaCurso.html", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView bajaAlumnoMateriaCurso(HttpSession session, int idCurso, String dniAlumno) {
		// 0- declaracion de variables locales
		ModelAndView MV = new ModelAndView();
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		try {
			// 1- Verificar permisos del usuario logueado
			if (ORSesion.getUsuarioBySession(session).getObjTipoUsuario()
					.getIdTipoUsuario() == Constantes.idTipoUsuarioAlumn)
				throw new ValidacionException("El usuario alumno no puede ingresar a esta funcionalidad");
			// 1.2- Verificar existencia de alumno para esa materia/ curso
			if (!serviceCursosCalificaciones.existeAlumnoDNIByIDCurso(dniAlumno, idCurso))
				throw new ValidacionException(
						String.format("El alumno con DNI [%s] no se encuentra registrado en el curso con ID: %d",
								dniAlumno, idCurso));
			// 1.3- Verificar que el DNI ingresado sea de un alumno
			Usuario objUsuarioAlumno = serviceUsuario.getUsuarioByDNI(dniAlumno);
			if (objUsuarioAlumno.getObjTipoUsuario().getIdTipoUsuario() != Constantes.idTipoUsuarioAlumn)
				throw new ValidacionException("El DNI ingresado debe corresponder a un alumno");
			// 2- Ejecutar transacción DB y devolver las respuestas
			int cantRegistrosEliminados = 0;
			for (CursosCalificaciones obj : serviceCursosCalificaciones.getAllByDNIAlumno(dniAlumno)) {
				if (obj.getObjCurso().getIdCurso() == idCurso) {
					if (!serviceCursosCalificaciones.delete(obj.getIdCursoCalif()))
						throw new ValidacionException("Ocurrió un error al intentar eliminar la Calificación con ID: "
								+ obj.getIdCursoCalif());
					cantRegistrosEliminados++;
				}
			}
			// 3- informar resultados
			message = String.format("Se eliminaron %d registros del alumno con DNI: %s", cantRegistrosEliminados,
					dniAlumno);
			objInfoMessage = new InfoMessage(true, message);
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(Constantes.indexJsp);
		return MV;
	}

	// TODO: Calificaciones: mostrar nombre y apellido de los alumnos
	/// ******************* CALIFICACIONES - MASIVA ******************* ///
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

	@RequestMapping(value = "/eliminarCalificacion.html", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView eliminarCalificacion(int idCursoCalifToDelete, HttpSession session) {
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
			if (!serviceCursosCalificaciones.delete(idCursoCalifToDelete))
				throw new ValidacionException(
						"Ocurrió un error al intentar eliminar la Calificación con ID: " + idCursoCalifToDelete);
			// 3- informar resultados
			message = String.format("Se eliminó la Calificación con ID: %d", idCursoCalifToDelete);
			objInfoMessage = new InfoMessage(true, message);
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(Constantes.indexJsp);
		return MV;
	}

}
