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

import frgp.utn.edu.ar.dominio.TipoExamen;
import frgp.utn.edu.ar.servicio.ITipoExamenService;
import utils.InfoMessage;
import utils.Utilitario;
import utils.constantes.Constantes;
import utils.excepciones.ValidacionException;

@Controller
public class TipoExamenController {
	private String paginaJsp;
	@Autowired
	private ITipoExamenService serviceTipoExamen;

	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		this.serviceTipoExamen = (ITipoExamenService) ctx.getBean("serviceTipoExamen");
	}

	@RequestMapping("/altaTipoExamenLoad.html")
	public ModelAndView altaTipoExamenLoad() {
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {
			// 1- recuperar valores de la BBDD
			int idTipoExamen = serviceTipoExamen.getMax() + 1;
			// 2- guardar la información recuperada en las variables
			TipoExamen objTipoExamen = new TipoExamen();
			objTipoExamen.setIdTipoExamen(idTipoExamen);
			// 3- pasar las variables al jsp a cargar
			MV.addObject("objTipoExamen", objTipoExamen);
			// 4- informar resultados
			message = String.format("Se cargaron los datos del formulario");
			objInfoMessage = new InfoMessage(true, message);
			paginaJsp = "TipoExamenAlta";
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
			paginaJsp = Constantes.indexJsp;
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(paginaJsp);
		return MV;
	}

	@RequestMapping(value = "/altaTipoExamen.html", method = RequestMethod.POST)
	public ModelAndView altaTipoExamen(String descripcion, HttpSession session) {
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {
			// 0- verificar que sea un admin
			Utilitario.verificarQueElUsuarioLogueadoSeaAdmin(session);
			// 1- validar datos cargados por JSP (Cliente)
			if (descripcion.isEmpty())
				throw new ValidacionException("Por favor complete la descripcion del Tipo de Examen");

			// 2- guardar la información recuperada en las variables
			TipoExamen objTipoExamen = new TipoExamen();
			objTipoExamen.setDescripcion(descripcion);

			// 3- insertar en BBDD y verificar estado de transacción
			int idGenerado = serviceTipoExamen.insert(objTipoExamen);
			if (!(idGenerado > 0))
				throw new ValidacionException("SQL: Ocurrió un error al guardar el Tipo de Examen");
			// 4- informar resultados
			message = String.format("Se registró el Tipo de Examen con éxito. ID: " + idGenerado);
			objInfoMessage = new InfoMessage(true, message);
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(Constantes.indexJsp);
		return MV;
	}

	@RequestMapping(value = "/eliminarTipoExamen.html", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView eliminarTipoExamen(int idTipoExamen, HttpSession session) {
		// 0- declaracion de variables locales
		ModelAndView MV = new ModelAndView();
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		try {
			// 0- verificar que sea un admin
			Utilitario.verificarQueElUsuarioLogueadoSeaAdmin(session);
			// 1- Ejecutar transacción DB y devolver las respuestas
			if (!serviceTipoExamen.delete(idTipoExamen))
				throw new ValidacionException(
						"Ocurrió un error al intentar eliminar el Tipo de Examen con ID: " + idTipoExamen);
			// 2- informar resultados
			message = String.format("Se eliminó el Tipo de Examen con ID: %d", idTipoExamen);
			objInfoMessage = new InfoMessage(true, message);
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(Constantes.indexJsp);
		return MV;
	}

	@RequestMapping(value = "/listarTipoExamen" + Constantes.html, method = RequestMethod.GET)
	public ModelAndView listarTipoExamen(HttpSession session) {
		// 0- declaracion de variables locales
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {
			List<TipoExamen> listaTipoExamen = serviceTipoExamen.getAll();
			listaTipoExamen.sort((o1, o2) -> o2.getIdTipoExamen().compareTo(o1.getIdTipoExamen()));
			MV.addObject("listaTipoExamen", listaTipoExamen);
			// 4- informar resultados
			message = String.format("Se cargaron los Tipos de Examen del sistema");
			objInfoMessage = new InfoMessage(true, message);
			paginaJsp = "/TipoExamenListado";
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
			paginaJsp = Constantes.indexJsp;
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(paginaJsp);
		return MV;
	}

	@RequestMapping(value = "/modificarTipoExamenLoad.html", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView modificarTipoExamenLoad(int idTipoExamen, HttpSession session) {
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {
			// 1- verificar que el usuario tenga permisos de administrador
			Utilitario.verificarQueElUsuarioLogueadoSeaAdmin(session);
			// 2- recuperar valores de la BBDD y devolver resultados obtenidos
			// 3- pasar las variables al jsp a cargar
			MV.addObject("objTipoExamen", serviceTipoExamen.get(idTipoExamen));
			// 4- informar resultados
			message = String.format("Se cargaron los datos del formulario");
			objInfoMessage = new InfoMessage(true, message);
			paginaJsp = "TipoExamenModif";
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
			paginaJsp = Constantes.indexJsp;
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(paginaJsp);
		return MV;
	}

	@RequestMapping(value = "/modificarTipoExamen.html", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView modificarTipoExamen(int idTipoExamen, String descripcion, HttpSession session) {
		String message = null;
		InfoMessage objInfoMessage = new InfoMessage();
		ModelAndView MV = new ModelAndView();
		try {
			// 0- verificar que el usuario tenga permisos de administrador
			Utilitario.verificarQueElUsuarioLogueadoSeaAdmin(session);
			// 1- validar datos cargados por JSP (Cliente)
			if (descripcion.isEmpty())
				throw new ValidacionException("Por favor complete la descripcion del Tipo de Examen");

			// 2- guardar la información recuperada en las variables
			TipoExamen objTipoExamen = new TipoExamen();
			objTipoExamen.setIdTipoExamen(idTipoExamen);
			objTipoExamen.setDescripcion(descripcion);
			// 3- realizar transacción BBDD
			if (!serviceTipoExamen.update(objTipoExamen))
				throw new ValidacionException(
						"SQL: Ocurrió un error al modificar el Tipo de Examen con ID " + idTipoExamen);
			// 4- informar resultados
			message = String.format("Se modificaron los datos del Tipo de Examen con ID: %d", idTipoExamen);
			objInfoMessage = new InfoMessage(true, message);
		} catch (Exception e) {
			objInfoMessage = new InfoMessage(false, e.getMessage());
		}
		MV.addObject("objInfoMessage", objInfoMessage);
		MV.setViewName(Constantes.indexJsp);
		return MV;
	}

}
