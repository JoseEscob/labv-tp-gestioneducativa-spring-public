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

import frgp.utn.edu.ar.dominio.TipoPeriodo;
import frgp.utn.edu.ar.servicio.ITipoPeriodoService;
import utils.InfoMessage;
import utils.Utilitario;
import utils.constantes.Constantes;
import utils.excepciones.ValidacionException;

@Controller
public class TipoPeriodoController {
	private String paginaJsp;
	@Autowired
	private ITipoPeriodoService serviceTipoPeriodo;

	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		this.serviceTipoPeriodo = (ITipoPeriodoService) ctx.getBean("serviceTipoPeriodo");
	}

	@RequestMapping("/altaTipoPeriodoLoad.html")
	public ModelAndView altaTipoPeriodoLoad() {
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {
			// 1- recuperar valores de la BBDD
			int idTipoPeriodo = serviceTipoPeriodo.getMax() + 1;
			// 2- guardar la información recuperada en las variables
			TipoPeriodo objTipoPeriodo = new TipoPeriodo();
			objTipoPeriodo.setIdPeriodo(idTipoPeriodo);
			// 3- pasar las variables al jsp a cargar
			MV.addObject("objTipoPeriodo", objTipoPeriodo);
			// 4- informar resultados
			message = String.format("Se cargaron los datos del formulario");
			objInfoMessage = new InfoMessage(true, message);
			paginaJsp = "TipoPeriodoAlta";
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
			paginaJsp = Constantes.indexJsp;
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(paginaJsp);
		return MV;
	}

	@RequestMapping(value = "/altaTipoPeriodo.html", method = RequestMethod.POST)
	public ModelAndView altaTipoPeriodo(String descripcion, HttpSession session) {
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {
			// 0- verificar que sea un admin
			Utilitario.verificarQueElUsuarioLogueadoSeaAdmin(session);
			// 1- validar datos cargados por JSP (Cliente)
			if (descripcion.isEmpty())
				throw new ValidacionException("Por favor complete la descripcion del Tipo de Periodo");

			// 2- guardar la información recuperada en las variables
			TipoPeriodo objTipoPeriodo = new TipoPeriodo();
			objTipoPeriodo.setDescripcion(descripcion);

			// 3- insertar en BBDD y verificar estado de transacción
			int idGenerado = serviceTipoPeriodo.insert(objTipoPeriodo);
			if (!(idGenerado > 0))
				throw new ValidacionException("SQL: Ocurrió un error al guardar el Tipo de Periodo");
			// 4- informar resultados
			message = String.format("Se registró el Tipo de Periodo con éxito. ID: " + idGenerado);
			objInfoMessage = new InfoMessage(true, message);
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(Constantes.indexJsp);
		return MV;
	}

	@RequestMapping(value = "/eliminarTipoPeriodo.html", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView eliminarTipoPeriodo(int idPeriodo, HttpSession session) {
		// 0- declaracion de variables locales
		ModelAndView MV = new ModelAndView();
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		try {
			// 0- verificar que sea un admin
			Utilitario.verificarQueElUsuarioLogueadoSeaAdmin(session);
			// 1- Ejecutar transacción DB y devolver las respuestas
			if (!serviceTipoPeriodo.delete(idPeriodo))
				throw new ValidacionException(
						"Ocurrió un error al intentar eliminar el Tipo de Periodo con ID: " + idPeriodo);
			// 2- informar resultados
			message = String.format("Se eliminó el Tipo de Periodo con ID: %d", idPeriodo);
			objInfoMessage = new InfoMessage(true, message);
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(Constantes.indexJsp);
		return MV;
	}

	@RequestMapping(value = "/listarTipoPeriodo" + Constantes.html, method = RequestMethod.GET)
	public ModelAndView listarTipoPeriodo(HttpSession session) {
		// 0- declaracion de variables locales
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {
			List<TipoPeriodo> listaTipoPeriodo = serviceTipoPeriodo.getAll();
			listaTipoPeriodo.sort((o1, o2) -> o2.getIdPeriodo().compareTo(o1.getIdPeriodo()));
			MV.addObject("listaTipoPeriodo", listaTipoPeriodo);
			// 4- informar resultados
			message = String.format("Se cargaron los Tipos de Periodo del sistema");
			objInfoMessage = new InfoMessage(true, message);
			paginaJsp = "/TipoPeriodoListado";
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
			paginaJsp = Constantes.indexJsp;
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(paginaJsp);
		return MV;
	}

	@RequestMapping(value = "/modificarTipoPeriodoLoad.html", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView modificarTipoPeriodoLoad(int idPeriodo, HttpSession session) {
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {
			// 1- verificar que el usuario tenga permisos de administrador
			Utilitario.verificarQueElUsuarioLogueadoSeaAdmin(session);
			// 2- recuperar valores de la BBDD y devolver resultados obtenidos
			// 3- pasar las variables al jsp a cargar
			MV.addObject("objTipoPeriodo", serviceTipoPeriodo.get(idPeriodo));
			// 4- informar resultados
			message = String.format("Se cargaron los datos del formulario");
			objInfoMessage = new InfoMessage(true, message);
			paginaJsp = "TipoPeriodoModif";
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
			paginaJsp = Constantes.indexJsp;
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(paginaJsp);
		return MV;
	}

	@RequestMapping(value = "/modificarTipoPeriodo.html", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView modificarTipoPeriodo(int idPeriodo, String descripcion, HttpSession session) {
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {
			// 0- verificar que el usuario tenga permisos de administrador
			Utilitario.verificarQueElUsuarioLogueadoSeaAdmin(session);
			// 1- validar datos cargados por JSP (Cliente)
			if (descripcion.isEmpty())
				throw new ValidacionException("Por favor complete la descripcion del Tipo de Periodo");

			// 2- guardar la información recuperada en las variables
			TipoPeriodo objTipoPeriodo = new TipoPeriodo();
			objTipoPeriodo.setIdPeriodo(idPeriodo);
			objTipoPeriodo.setDescripcion(descripcion);
			// 3- realizar transacción BBDD
			if (!serviceTipoPeriodo.update(objTipoPeriodo))
				throw new ValidacionException(
						"SQL: Ocurrió un error al modificar el Tipo de Periodo con ID " + idPeriodo);
			// 4- informar resultados
			message = String.format("Se modificaron los datos del Tipo de Periodo con ID: %d", idPeriodo);
			objInfoMessage = new InfoMessage(true, message);
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(Constantes.indexJsp);
		return MV;
	}

}
