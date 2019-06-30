package frgp.utn.edu.ar.controllers;

import javax.servlet.ServletConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.PathVariable;
import frgp.utn.edu.ar.dominio.Usuario;
import frgp.utn.edu.ar.servicio.IUsuarioService;
import utils.InfoMessage;
import utils.LOG;
import utils.constantes.Constantes;
import utils.excepciones.ValidacionException;

@Controller
@SessionAttributes(Constantes.sessionUser)
public class UsuarioController {
	private String paginaJsp;
	@Autowired
	public IUsuarioService service;

	@SuppressWarnings("unchecked")
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());

		this.service = (IUsuarioService) ctx.getBean("serviceUsuarioBeanX");
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

	@RequestMapping("/inicio.html")
	public ModelAndView inicio() {
		ModelAndView MV = new ModelAndView();
		MV.setViewName("index");
		return MV;
	}

	@RequestMapping(value = "/iniciarSesion" + Constantes.html, method = RequestMethod.POST)
	public ModelAndView iniciarSesion(String txtLoginUsuario, String txtLoginClave) {
		Usuario objUsuario;
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {
			// 1- recuperar valores del formulario JSP
			String correoUsuario = txtLoginUsuario;
			String claveUsuario = txtLoginClave;
			// 2- validar informacion obtenida JSP
			objUsuario = service.getUsuarioByLogin(correoUsuario, claveUsuario);
			// 3- verificar resultado
			if (objUsuario == null)
				throw new ValidacionException("El usuario no está registrado");
			// 4- Se guarda una variable SESSION
			// ORSesion.nuevaSesion(request, objUsuario);
			MV.addObject(Constantes.sessionUser, objUsuario);
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
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(paginaJsp);
		return MV;
	}
}
