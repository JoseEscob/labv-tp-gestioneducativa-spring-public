package frgp.utn.edu.ar.controllers;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;// utilizado para parámetros por url
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import frgp.utn.edu.ar.dominio.Usuario;
import frgp.utn.edu.ar.servicio.ITipoUsuarioService;
import frgp.utn.edu.ar.servicio.IUsuarioService;
import utils.InfoMessage;
import utils.LOG;
import utils.ORSesion;
import utils.Utilitario;
import utils.constantes.Constantes;
import utils.excepciones.ValidacionException;

@Controller
//@SessionAttributes(Constantes.sessionUser)
public class UsuarioController {
	private String paginaJsp;
	@Autowired
	public IUsuarioService serviceUsuario;

	@Autowired
	public ITipoUsuarioService serviceTipoUsuario;

	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());

		this.serviceUsuario = (IUsuarioService) ctx.getBean("serviceUsuarioBeanX");
		this.serviceTipoUsuario = (ITipoUsuarioService) ctx.getBean("serviceTipoUsuario");
	}

	@RequestMapping("/inicio.html")
	public ModelAndView inicio() {
		return new ModelAndView("index");
	}

	@RequestMapping("/InicioAlumno.html")
	public ModelAndView InicioAlumno() {
		return new ModelAndView("InicioAlumno");
	}

	@RequestMapping("/InicioProfesor.html")
	public ModelAndView InicioProfesor() {
		return new ModelAndView("InicioProfesor");
	}

	@RequestMapping("/InicioAdministrador.html")
	public ModelAndView InicioAdministrador() {
		return new ModelAndView("InicioAdministrador");
	}

	@RequestMapping("/admListarUsuarios.html")
	public ModelAndView admListarUsuarios(HttpSession session) {
		try {
			ModelAndView MV = new ModelAndView();
			// 1- verificar que el usuario tenga permisos de administrador
			Utilitario.verificarQueElUsuarioLogueadoSeaAdmin(session);
			// 2- devolver resultados obtenidos
			MV.addObject("listaUsuarios", this.serviceUsuario.getAll());
			MV.setViewName("admListarUsuarios");
			return MV;
		} catch (Exception e) {
			LOG.warning(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/iniciarSesion" + Constantes.html, method = RequestMethod.POST)
	public ModelAndView iniciarSesion(String txtLoginUsuario, String txtLoginClave, HttpSession session) {
		Usuario objUsuario;
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {
			// 1- recuperar valores del formulario JSP
			String correoUsuario = txtLoginUsuario;
			String claveUsuario = txtLoginClave;
			// 2- validar informacion obtenida JSP
			objUsuario = serviceUsuario.getUsuarioByLogin(correoUsuario, claveUsuario);
			// 3- verificar resultado
			if (objUsuario == null)
				throw new ValidacionException("El usuario no está registrado");
			// 4- Se guarda una variable SESSION
			ORSesion.nuevaSesion(session, objUsuario);
			// MV.addObject(Constantes.sessionUser, objUsuario);
			// 5- Informar estado
			objInfoMessage = new InfoMessage(true, "Login exitoso");

			if (objUsuario != null) {
				switch (objUsuario.getObjTipoUsuario().getIdTipoUsuario()) {
				case Constantes.idTipoUsuarioAlumn:
					paginaJsp = "/InicioAlumno";
					break;
				case Constantes.idTipoUsuarioProfe:
					paginaJsp = "/InicioProfesor";
					break;
				case Constantes.idTipoUsuarioAdmin:
					paginaJsp = "/InicioAdministrador";// "/InicioAdministrador.jsp";
					break;
				default:
					paginaJsp = Constantes.indexJsp;
					break;
				}
			}
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
			LOG.warning(e.getMessage());
			paginaJsp = Constantes.indexJsp;
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(paginaJsp);
		return MV;
	}

	@RequestMapping(value = "/cerrarSesion" + Constantes.html, method = RequestMethod.GET)
	public ModelAndView cerrarSesion(HttpSession session) {
		ModelAndView MV = new ModelAndView();

		InfoMessage objInfoMessage = new InfoMessage();
		try {
			if (ORSesion.sesionActiva(session)) {
				ORSesion.cerrarSesion(session);
				objInfoMessage = new InfoMessage(true, Constantes.msgSesionFinalizada);
			} else
				throw new ValidacionException("La sesión no fue iniciada. No se pudo finalizar");
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
		}

		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(Constantes.indexJsp);
		return MV;
	}

	@RequestMapping("/altaUsuarioLoad.html")
	public ModelAndView altaUsuarioLoad() {
		try {
			ModelAndView MV = new ModelAndView();
			MV.addObject("listaTipoUsuarios", serviceTipoUsuario.getAll());
			MV.setViewName("UsuarioAlta");
			return MV;
		} catch (Exception e) {
			LOG.warning(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/altaUsuarioSave" + Constantes.html, method = RequestMethod.POST)
	public ModelAndView altaUsuario(Usuario objUsuario, HttpSession session) {
		ModelAndView MV = new ModelAndView();

		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		try {
			// 1- verificar que el usuario tenga permisos de administrador
			Utilitario.verificarQueElUsuarioLogueadoSeaAdmin(session);
			// 2- validar campos
			// TODO implementar método por request
			// 3- insertar en BBDD y verificar estado de transacción
			serviceUsuario.validarCamposUnicos(objUsuario);
			if (!(serviceUsuario.insert(objUsuario) > 0))
				throw new ValidacionException("SQL: Ocurrió un error al guardar el usuario");
			// 4- informar resultados
			message = String.format("Se registró al usuario exitosamente su ID es : %d", objUsuario.getIdUsuario());
			objInfoMessage = new InfoMessage(true, message);
			paginaJsp = Constantes.indexJsp;
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
			LOG.warning(e.getMessage());
			paginaJsp = Constantes.indexJsp;
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(paginaJsp);
		return MV;
	}

	@RequestMapping(value = "/modificarUsuarioLogueadoLoad" + Constantes.html, method = RequestMethod.GET)
	public ModelAndView modificarUsuarioLogueadoLoad(HttpSession session) {
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
			MV.addObject("listaTipoUsuarios", serviceTipoUsuario.getAll());
			MV.addObject("objUsuario", objUsuario);
			// 4- informar resultados
			message = String.format("Se cargaron los datos del usuario");
			objInfoMessage = new InfoMessage(true, message);
			paginaJsp = "/UsuarioViewModif";
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
			paginaJsp = Constantes.indexJsp;
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(paginaJsp);
		return MV;
	}

	@RequestMapping(value = "/eliminarUsuario.html", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView eliminarUsuario(int idUsuarioToDelete, HttpSession session) {
		// 0- declaracion de variables locales
		ModelAndView MV = new ModelAndView();
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		try {
			// 1- Verificar permisos del usuario logueado
			Utilitario.verificarQueElUsuarioLogueadoSeaAdmin(session);
			// 2- Ejecutar transacción DB y devolver las respuestas
			if (!serviceUsuario.delete(idUsuarioToDelete))
				throw new ValidacionException(
						"Ocurrió un error al intentar eliminar al usuario con ID: " + idUsuarioToDelete);
			// 3- informar resultados
			message = String.format("Se eliminó al usuario con ID: %d", idUsuarioToDelete);
			objInfoMessage = new InfoMessage(true, message);
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(Constantes.indexJsp);
		return MV;
	}

	@RequestMapping(value = { "/select-user-{idUsuarioToView}" }, method = RequestMethod.GET)
	public ModelAndView selectUser(@PathVariable int idUsuarioToView) {
		// 0- declaracion de variables locales
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {
			// 1- Recuperar info del usuario seleccionado
			Usuario objUsuario = serviceUsuario.get(idUsuarioToView);
			// 2- validar la informacion recuperada
			if (objUsuario == null)
				throw new ValidacionException("No se encontró al usuario con ID: " + idUsuarioToView);
			// 3- pasar las variables al jsp a cargar
			MV.addObject("listaTipoUsuarios", serviceTipoUsuario.getAll());
			MV.addObject("objUsuario", objUsuario);
			// 4- informar resultados
			message = String.format("Se cargaron los datos del usuario a visualizar");
			objInfoMessage = new InfoMessage(true, message);
			paginaJsp = "/UsuarioViewModifAdmin";
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
			paginaJsp = Constantes.indexJsp;
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(paginaJsp);
		return MV;
	}
}
