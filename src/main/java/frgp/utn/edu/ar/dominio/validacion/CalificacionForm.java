package frgp.utn.edu.ar.dominio.validacion;

import java.util.ArrayList;
import java.util.List;

import frgp.utn.edu.ar.dominio.CursosCalificaciones;
import utils.LOG;

public class CalificacionForm {
	private ArrayList<CalificacionValidator> listaCalificaciones;

	public CalificacionForm() {
		super();
	}

	public CalificacionForm(ArrayList<CalificacionValidator> listaCalificaciones) {
		super();
		this.listaCalificaciones = listaCalificaciones;
	}

	public ArrayList<CalificacionValidator> getListaCalificaciones() {
		return listaCalificaciones;
	}

	public void setListaCalificaciones(ArrayList<CalificacionValidator> listaCalificaciones) {
		this.listaCalificaciones = listaCalificaciones;
	}

	private CalificacionValidator convertCalif2CalifValidator(CursosCalificaciones objCalifHibernate) {
		CalificacionValidator objCalifValidator = new CalificacionValidator();
		objCalifValidator.setIdCursoCalif(objCalifHibernate.getIdCursoCalif());
		objCalifValidator.setDni(objCalifHibernate.getObjUsuarioAlumn().getDni());
		objCalifValidator.setNota(objCalifHibernate.getNota());
		objCalifValidator.setFechaCalif(objCalifHibernate.getFechaCalif());
		objCalifValidator.setFechaUltModif(objCalifHibernate.getFechaUltModif());
		objCalifValidator.setIdCurso(objCalifHibernate.getObjCurso().getIdCurso());
		objCalifValidator.setIdTipoExamen(objCalifHibernate.getObjTipoExamen().getIdTipoExamen());
		return objCalifValidator;
	}

	public void cargarListCalifHibernate(ArrayList<CursosCalificaciones> listaCalifHibernate) {

		ArrayList<CalificacionValidator> listaCalificaciones = new ArrayList<CalificacionValidator>();
		listaCalifHibernate.stream().forEach(item -> {
			listaCalificaciones.add(convertCalif2CalifValidator(item));
		});
		this.setListaCalificaciones(listaCalificaciones);
		LOG.info("Se cargó la lista de calificaciones validator con éxito");
	}
}
