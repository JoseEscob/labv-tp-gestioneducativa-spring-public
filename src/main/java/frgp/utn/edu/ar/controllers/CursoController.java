package frgp.utn.edu.ar.controllers;

import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import frgp.utn.edu.ar.dominio.Curso;
import frgp.utn.edu.ar.dominio.Usuario;
import frgp.utn.edu.ar.dominio.TipoPeriodo;
import frgp.utn.edu.ar.servicio.ICursoService;
import frgp.utn.edu.ar.servicio.ITipoPeriodoService;
import frgp.utn.edu.ar.servicio.IUsuarioService;
import utils.InfoMessage;
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

	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		this.serviceCurso = (ICursoService) ctx.getBean("serviceCurso");
		this.serviceTipoPeriodo = (ITipoPeriodoService) ctx.getBean("serviceTipoPeriodo");
		this.serviceUsuario = (IUsuarioService) ctx.getBean("serviceUsuario");
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
			listaCursos.sort((o1, o2) -> o2.getIdCurso().compareTo(o1.getIdCurso()));
			MV.addObject("listaCursos", listaCursos);
			MV.addObject("listaTipoPeriodo", serviceCurso.getAllDistinctTipoPeriodo());
			MV.addObject("listaAnio", serviceCurso.getAllDistinctAnio());
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
			MV.addObject("listaTipoPeriodo", serviceCurso.getAllDistinctTipoPeriodo());
			MV.addObject("listaAnio", serviceCurso.getAllDistinctAnio());
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

	@RequestMapping(value = "/modificarMateriaCursoLoad.html", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView modificarMateriaCursoLoad(int idCurso, HttpSession session) {
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {
			// 1- verificar que el usuario tenga permisos de administrador
			Utilitario.verificarQueElUsuarioLogueadoSeaAdmin(session);
			// 2- recuperar valores de la BBDD y devolver resultados obtenidos
			MV.addObject("listaPeriodos", serviceTipoPeriodo.getAll());
			Curso objMateriaCurso = serviceCurso.get(idCurso);
			// 3- pasar las variables al jsp a cargar
			MV.addObject("objMateriaCurso", objMateriaCurso);
			// 4- informar resultados
			message = String.format("Se cargaron los datos del formulario");
			objInfoMessage = new InfoMessage(true, message);
			paginaJsp = "MateriaModif";
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
			paginaJsp = Constantes.indexJsp;
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(paginaJsp);
		return MV;
	}

	@RequestMapping(value = "/modificarMateriaCurso.html", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView modificarMateriaCurso(int idCurso, HttpSession session, String nombreCurso, int anio,
			int idPeriodo, String dniProfesor) {
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {
			// 1- verificar que el usuario tenga permisos de administrador
			Utilitario.verificarQueElUsuarioLogueadoSeaAdmin(session);
			// 2- validar datos
			if (nombreCurso.isEmpty())
				throw new ValidacionException("Por favor complete el nombre de la materia/ curso");
			if (dniProfesor.isEmpty())
				throw new ValidacionException("Por favor complete el dni del profesor de la materia/ curso");

			Usuario objUsuarioProfe = serviceUsuario.getUsuarioByDNI(dniProfesor);
			if (objUsuarioProfe.getObjTipoUsuario().getIdTipoUsuario() != Constantes.idTipoUsuarioProfe)
				throw new ValidacionException("El DNI ingresado debe corresponder a un profesor");
			TipoPeriodo objTipoPeriodo = serviceTipoPeriodo.get(idPeriodo);
			// 2- guardar la información recuperada en las variables
			Curso objMateriaCurso = serviceCurso.get(idCurso);
			objMateriaCurso.setNombreCurso(nombreCurso);
			objMateriaCurso.setAnio(anio);
			objMateriaCurso.setObjTipoPeriodo(objTipoPeriodo);
			objMateriaCurso.setObjUsuarioProfe(objUsuarioProfe);
			// 3- realizar transacción BBDD
			if (!serviceCurso.update(objMateriaCurso))
				throw new ValidacionException("SQL: Ocurrió un error al modificar el curso con ID " + idCurso);
			// 4- informar resultados
			message = String.format("Se modificaron los datos del curso con ID: %d", idCurso);
			objInfoMessage = new InfoMessage(true, message);
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(Constantes.indexJsp);
		return MV;
	}

	@RequestMapping(value = "/eliminarMateriaCurso.html", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView eliminarMateriaCurso(int idCurso, HttpSession session) {
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
			if (!serviceCurso.delete(idCurso))
				throw new ValidacionException(
						"Ocurrió un error al intentar eliminar la Materia/Curso con ID: " + idCurso);
			// 3- informar resultados
			message = String.format("Se eliminó la Materia/Curso con ID: %d", idCurso);
			objInfoMessage = new InfoMessage(true, message);
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(Constantes.indexJsp);
		return MV;
	}

	@RequestMapping(value = "/gestionarMateriaCurso.html", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView gestionarMateriaCurso(int idCurso) {
		ModelAndView MV = new ModelAndView("MateriaGestion");
		InfoMessage objInfoMessage = new InfoMessage();
		try {
			MV.addObject("objCurso", serviceCurso.get(idCurso));
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
			MV.addObject("objInfoMessage", objInfoMessage);
		}
		return MV;
	}

	/// ******************* LISTADO DE FILTROS ******************* ///
	@RequestMapping(value = "/listaCursosByNombreCurso.html", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listaCursosByNombreCurso(String txtNombreCursoBuscado, HttpSession session) {
		String message = "materias/cursos que comiencen con: " + txtNombreCursoBuscado;
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {
			// 1- verificar que el usuario tenga permisos de administrador
			Utilitario.verificarQueElUsuarioLogueadoSeaAdmin(session);
			// 2- devolver resultados obtenidos
			List<Curso> listaCursos = serviceCurso.getAllByNombreCursoBuscado(txtNombreCursoBuscado);
			if (listaCursos.isEmpty())
				throw new ValidacionException("No se encontraron " + message);
			// 3- Ordenar resultados y guardar en las variables del JSP
			listaCursos.sort((o1, o2) -> o2.getIdCurso().compareTo(o1.getIdCurso()));
			MV.addObject("listaCursos", listaCursos);
			MV.addObject("listaTipoPeriodo", serviceCurso.getAllDistinctTipoPeriodo());
			MV.addObject("listaAnio", serviceCurso.getAllDistinctAnio());
			// 4- informar resultados
			message = "Búsqueda de " + message;
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

	@RequestMapping(value = "/listaCursosByFiltroPeriodoAnio.html", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listaUsuariosByTipoUsuario(int idTipoPeriodoBuscado, int anioBuscado, HttpSession session) {
		String message = String.format("materias/cursos con año %d y periodo %d", anioBuscado, idTipoPeriodoBuscado);
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {
			// 1- verificar que el usuario tenga permisos de administrador
			Utilitario.verificarQueElUsuarioLogueadoSeaAdmin(session);

			// 2- devolver resultados obtenidos
			List<Curso> listaCursos = serviceCurso.getAllByFiltroPeriodoAnio(idTipoPeriodoBuscado, anioBuscado);
			if (listaCursos.isEmpty())
				throw new ValidacionException("No se encontraron " + message);

			// 3- Ordenar resultados y guardar en las variables del JSP
			listaCursos.sort((o1, o2) -> o2.getIdCurso().compareTo(o1.getIdCurso()));
			MV.addObject("listaCursos", listaCursos);
			MV.addObject("listaTipoPeriodo", serviceCurso.getAllDistinctTipoPeriodo());
			MV.addObject("listaAnio", serviceCurso.getAllDistinctAnio());
			// 4- informar resultados
			message = "Búsqueda de " + message;
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
}
