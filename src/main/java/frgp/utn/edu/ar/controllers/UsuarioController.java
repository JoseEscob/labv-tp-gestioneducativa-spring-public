package frgp.utn.edu.ar.controllers;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;// utilizado para parámetros por url
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import frgp.utn.edu.ar.dominio.Usuario;
import frgp.utn.edu.ar.dominio.validacion.UsuarioValidator;
import frgp.utn.edu.ar.servicio.ITipoUsuarioService;
import frgp.utn.edu.ar.servicio.IUsuarioService;
import utils.InfoMessage;
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

		this.serviceUsuario = (IUsuarioService) ctx.getBean("serviceUsuario");
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
	public ModelAndView InicioAdministrador(HttpSession session) {
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {
			Utilitario.verificarQueElUsuarioLogueadoSeaAdmin(session);
			paginaJsp = "InicioAdministrador";
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
			paginaJsp = Constantes.indexJsp;
			MV.addObject("objInfoMessage", objInfoMessage);
		}
		MV.setViewName(paginaJsp);
		return MV;
	}

	@RequestMapping("/admListarUsuarios.html")
	public ModelAndView admListarUsuarios(HttpSession session) {
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {
			// 1- verificar que el usuario tenga permisos de administrador
			Utilitario.verificarQueElUsuarioLogueadoSeaAdmin(session);
			// 2- devolver resultados obtenidos
			ArrayList<Usuario> listaUsuarios = this.serviceUsuario.getAll();
			listaUsuarios.sort((o1, o2) -> o2.getIdUsuario().compareTo(o1.getIdUsuario()));
			MV.addObject("listaUsuarios", listaUsuarios);
			// 3- carga de combo de filtro
//			ArrayList<TipoUsuario> listaTipoUsuarios = new ArrayList<TipoUsuario>();
//
//			for (Usuario objUsuario : listaUsuarios.stream()
//					.filter(Utilitario.distinctByKey(Usuario::getObjTipoUsuario)).collect(Collectors.toList())) {
//				listaTipoUsuarios.add(objUsuario.getObjTipoUsuario());
//			}
			MV.addObject("listaTipoUsuarios", serviceUsuario.getAllTipoUsuarioByUsuarios());
			paginaJsp = "admListarUsuarios";
			objInfoMessage = new InfoMessage(true, "Lista de usuarios cargada exitósamente");
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
			paginaJsp = Constantes.indexJsp;
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(paginaJsp);
		return MV;
	}

	@RequestMapping(value = "/iniciarSesion" + Constantes.html, method = RequestMethod.POST)
	public ModelAndView iniciarSesion(String txtLoginUsuario, String txtLoginClave, HttpSession session) {
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {
			// 1- recuperar valores del formulario JSP
			String correoUsuario = txtLoginUsuario;
			String claveUsuario = txtLoginClave;
			// 2- validar informacion obtenida JSP
			Usuario objUsuario = serviceUsuario.getUsuarioByLogin(correoUsuario, claveUsuario);
			// 3- verificar resultado
			if (objUsuario == null)
				throw new ValidacionException("El usuario no está registrado");
			// 4- Se guarda una variable SESSION
			ORSesion.nuevaSesion(session, objUsuario);
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
			paginaJsp = Constantes.indexJsp;
			MV.addObject("objInfoMessage", objInfoMessage);
		}
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
			return null;
		}
	}

	private Usuario obtenerUsuarioDeObjetoValidator(UsuarioValidator objUsuarioValidator) throws Exception {
		Usuario objUsuario = new Usuario();
		objUsuario.setObjTipoUsuario(serviceTipoUsuario.get(objUsuarioValidator.getIdTipoUsuario()));
		objUsuario.setNombre(objUsuarioValidator.getNombre());
		objUsuario.setApellido(objUsuarioValidator.getApellido());
		objUsuario.setDni(objUsuarioValidator.getDni());
		objUsuario.setCalleNombre(objUsuarioValidator.getCalleNombre());
		objUsuario.setCalleAltura(objUsuarioValidator.getCalleAltura());
		objUsuario.setFechaNac(objUsuarioValidator.getFechaNac());
		objUsuario.setNroTelefono(objUsuarioValidator.getNroTelefono());
		objUsuario.setMail(objUsuarioValidator.getMail());
		objUsuario.setClave(objUsuarioValidator.getClave());
		objUsuario.setHabilitado(objUsuarioValidator.getHabilitado());
		return objUsuario;
	}

	@RequestMapping(value = "/altaUsuarioSave" + Constantes.html, method = RequestMethod.POST)
	public ModelAndView altaUsuario(@ModelAttribute("objUsuario") UsuarioValidator objUsuarioValidator,
			HttpSession session, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView(Constantes.indexJsp);
		}

		ModelAndView MV = new ModelAndView();
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		try {
			// 1- verificar que el usuario tenga permisos de administrador
			Utilitario.verificarQueElUsuarioLogueadoSeaAdmin(session);
			// 2- validar campos
			Utilitario.validarObjetoClasePorValidator(objUsuarioValidator);

			// 3- insertar en BBDD y verificar estado de transacción
			Usuario objUsuario = obtenerUsuarioDeObjetoValidator(objUsuarioValidator);
			serviceUsuario.validarCamposUnicos(objUsuario);

			int idGenerado = serviceUsuario.insert(objUsuario);
			if (!(idGenerado > 0))
				throw new ValidacionException("SQL: Ocurrió un error al guardar el usuario");
			// 4- informar resultados
			message = String.format("Se registró al usuario exitosamente su ID es : %d", idGenerado);
			objInfoMessage = new InfoMessage(true, message);
			paginaJsp = Constantes.indexJsp;
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
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
			Usuario objUsuarioLogueado = ORSesion.getUsuarioBySession(session);
			if (idUsuarioToDelete == objUsuarioLogueado.getIdUsuario())
				throw new ValidacionException("No se puede eliminar al usuario logueado con ID: " + idUsuarioToDelete);
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

	@RequestMapping(value = { "/modificar-user-{idUsuarioToViewModif}" }, method = RequestMethod.GET)
	public ModelAndView modificarUsuarioByAdminLoad(@PathVariable int idUsuarioToViewModif, HttpSession session) {
		// 0- declaracion de variables locales
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {
			// 1- verificar que el usuario tenga permisos de administrador
			Utilitario.verificarQueElUsuarioLogueadoSeaAdmin(session);
			// 1- Recuperar info del usuario seleccionado
			Usuario objUsuario = serviceUsuario.get(idUsuarioToViewModif);
			// 2- validar la informacion recuperada
			if (objUsuario == null)
				throw new ValidacionException("No se encontró al usuario con ID: " + idUsuarioToViewModif);
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

	@RequestMapping(value = "/modificarUsuarioByAdmin" + Constantes.html, method = RequestMethod.POST)
	public ModelAndView modificarUsuarioByAdmin(@ModelAttribute("objUsuario") UsuarioValidator objUsuarioValidator,
			int idUsuarioToViewModif, HttpSession session) {

		ModelAndView MV = new ModelAndView();
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		try {
			// 1- verificar que el usuario tenga permisos de administrador
			Utilitario.verificarQueElUsuarioLogueadoSeaAdmin(session);
			// 2- validar campos
			Utilitario.validarObjetoClasePorValidator(objUsuarioValidator);

			// 3- insertar en BBDD y verificar estado de transacción
			Usuario objUsuario = obtenerUsuarioDeObjetoValidator(objUsuarioValidator);
			// serviceUsuario.validarCamposUnicos(objUsuario);
			// 3.2 Se indica qué id actualizar (siempre)
			objUsuario.setIdUsuario(idUsuarioToViewModif);
			if (!serviceUsuario.update(objUsuario))
				throw new ValidacionException("SQL: Ocurrió un error al guardar las modificaciones del usuario");
			// 4- informar resultados
			message = String.format("Se modificaron los datos del usuario exitosamente ");
			objInfoMessage = new InfoMessage(true, message);
			paginaJsp = Constantes.indexJsp;
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
			paginaJsp = Constantes.indexJsp;
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(paginaJsp);
		return MV;
	}

	@RequestMapping(value = "/modificarUsuarioLogueado" + Constantes.html, method = RequestMethod.POST)
	public ModelAndView modificarUsuarioLogueado(@ModelAttribute("objUsuario") UsuarioValidator objUsuarioValidator,
			HttpSession session) {

		ModelAndView MV = new ModelAndView();
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		try {

			// 2- validar campos
			Utilitario.validarObjetoClasePorValidator(objUsuarioValidator);

			// 3- insertar en BBDD y verificar estado de transacción
			if (!ORSesion.sesionActiva(session))
				throw new ValidacionException("La sesión no fue iniciada. No se pudo modificar los datos");
			Usuario objUsuarioLogueado = ORSesion.getUsuarioBySession(session);
			objUsuarioValidator.setIdTipoUsuario(objUsuarioLogueado.getObjTipoUsuario().getIdTipoUsuario());
			Usuario objUsuario = obtenerUsuarioDeObjetoValidator(objUsuarioValidator);
			// serviceUsuario.validarCamposUnicos(objUsuario);
			// 3.2 Se indica qué id actualizar (siempre)
			int idUsuarioToViewModif = objUsuarioLogueado.getIdUsuario();
			objUsuario.setIdUsuario(idUsuarioToViewModif);
			if (!serviceUsuario.update(objUsuario))
				throw new ValidacionException("SQL: Ocurrió un error al guardar las modificaciones del usuario");
			// 4- actualizar datos de la sesión del usuario
			session.removeAttribute(Constantes.sessionUser);
			//ORSesion.cerrarSesion(session);
			ORSesion.nuevaSesion(session, objUsuario);

			// 5- informar resultados
			message = String.format("Se modificaron los datos del usuario exitosamente ");
			objInfoMessage = new InfoMessage(true, message);
			paginaJsp = Constantes.indexJsp;
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
			paginaJsp = Constantes.indexJsp;
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(paginaJsp);
		return MV;
	}

	/// ******************* LISTADO DE FILTROS ******************* ///
	@RequestMapping(value = "/listaUsuariosByDNI.html", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listaUsuariosByDNI(String txtDNIBuscado, HttpSession session) {
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {
			// 1- verificar que el usuario tenga permisos de administrador
			Utilitario.verificarQueElUsuarioLogueadoSeaAdmin(session);
			// 2- devolver resultados obtenidos
			ArrayList<Usuario> listaUsuarios = serviceUsuario.getAllByDNIBuscado(txtDNIBuscado);
			if (listaUsuarios.isEmpty())
				throw new ValidacionException("No se encontraron usuarios que comiencen con DNI: " + txtDNIBuscado);
			listaUsuarios.sort((o1, o2) -> o2.getIdUsuario().compareTo(o1.getIdUsuario()));
			MV.addObject("listaUsuarios", listaUsuarios);
			MV.addObject("listaTipoUsuarios", serviceUsuario.getAllTipoUsuarioByUsuarios());
			paginaJsp = "admListarUsuarios";
			objInfoMessage = new InfoMessage(true, "Búsqueda de usuarios que comienzan con DNI: " + txtDNIBuscado);
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
			paginaJsp = Constantes.indexJsp;
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(paginaJsp);
		return MV;
	}

	@RequestMapping(value = "/listaUsuariosByTipoUsuario.html", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listaUsuariosByTipoUsuario(int idTipoUsuarioBuscado, HttpSession session) {
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {
			// 1- verificar que el usuario tenga permisos de administrador
			Utilitario.verificarQueElUsuarioLogueadoSeaAdmin(session);
			// 2- devolver resultados obtenidos
			ArrayList<Usuario> listaUsuarios = (ArrayList<Usuario>) serviceUsuario.getAll().stream()
					.filter(item -> (item.getObjTipoUsuario().getIdTipoUsuario() == idTipoUsuarioBuscado))
					.collect(Collectors.toList());
			if (listaUsuarios.isEmpty())
				throw new ValidacionException("No se encontraron usuarios con TipoUsuario: " + idTipoUsuarioBuscado);
			listaUsuarios.sort((o1, o2) -> o2.getIdUsuario().compareTo(o1.getIdUsuario()));
			MV.addObject("listaUsuarios", listaUsuarios);
			MV.addObject("listaTipoUsuarios", serviceUsuario.getAllTipoUsuarioByUsuarios());
			paginaJsp = "admListarUsuarios";
			objInfoMessage = new InfoMessage(true, "Filtro de usuarios aplicados exitosamente");
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
			paginaJsp = Constantes.indexJsp;
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(paginaJsp);
		return MV;
	}

}
