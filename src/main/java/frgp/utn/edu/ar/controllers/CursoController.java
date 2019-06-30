package frgp.utn.edu.ar.controllers;

import javax.servlet.ServletConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import frgp.utn.edu.ar.dominio.Curso;
import frgp.utn.edu.ar.servicio.ICursoService;
import frgp.utn.edu.ar.servicio.ITipoPeriodoService;
import utils.InfoMessage;
import utils.LOG;
import utils.constantes.Constantes;

@Controller
public class CursoController {
	private String paginaJsp;
	@Autowired
	private ICursoService serviceCurso;
	@Autowired
	private ITipoPeriodoService serviceTipoPeriodo;

	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		this.serviceCurso = (ICursoService) ctx.getBean("serviceCurso");
		this.serviceTipoPeriodo = (ITipoPeriodoService) ctx.getBean("serviceTipoPeriodo");
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
			Curso objCursos = new Curso();
			objCursos.setIdCurso(idCurso);
			// 3- pasar las variables al jsp a cargar
			MV.addObject("objCursos", objCursos);
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

}
