package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import frgp.utn.edu.ar.dominio.Usuario;
import utils.constantes.Constantes;
import utils.excepciones.ValidacionException;

/**
 * Funciona como clase para implementar funciones reutilizables
 */
public class Utilitario {

	public static void validarObjetoClasePorValidator(T obj) throws ValidacionException {
		StringBuilder mensajeError = "";
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
		for (ConstraintViolation<Object> cv : constraintViolations) {
			System.out.println(String.format("Error here! property: [%s], value: [%s], message: [%s]",
					cv.getPropertyPath(), cv.getInvalidValue(), cv.getMessage()));
			throw new ValidacionException(cv.getMessage());
		}
	}

	public static void verificarQueElUsuarioLogueadoSeaAdmin(HttpSession session) throws ValidacionException {
		Usuario objUsuarioLogueado = ORSesion.getUsuarioBySession(session);
		if (objUsuarioLogueado.getObjTipoUsuario().getIdTipoUsuario() != Constantes.idTipoUsuarioAdmin)
			throw new ValidacionException(Constantes.usuarioSinPermisos);
	}

	/// *********************** SERVLET *****************************///
	public static void validarParametrosObligatoriosDeUnJSP(HttpServletRequest request, String[] listaNombreParametros,
			String[] listaNombreCampos) throws ValidacionException {
		LOG.info("Comienza proceso: validarParametrosObligatoriosDeUnJSP");
		if (listaNombreParametros.length != listaNombreCampos.length)
			throw new ValidacionException(
					"Programming ERROR: Las listas de validaciones de parametros no tienen la misma cantidad de ELEMENTOS");

		int i = 0;
		for (String nombreParam : listaNombreParametros) {
			if (request.getParameter(nombreParam) == null || request.getParameter(nombreParam).isEmpty())
				throw new ValidacionException("Por favor complete el campo obligatorio: " + listaNombreCampos[i]);
			i++;
		}
		LOG.info("Finaliza proceso: validarParametrosObligatoriosDeUnJSP");
	}

	/// *********************** FECHAS ******************************///
	public static int getCantOfDays(Date fechaInicio, Date fechaFin) {
		try {
			long diff = fechaFin.getTime() - fechaInicio.getTime();
			return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			// long diffDays = diff / (24 * 60 * 60 * 1000) + 1;
			// (int) diffDays......float days = (diff / (1000*60*60*24));

		} catch (Exception e) {
			return -1;
		}
	}

	public static java.sql.Date convertUtilToSql(java.util.Date uDate) {
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		return sDate;
	}

	public static java.sql.Date textoAFechaSQL(String textoFecha) {
		try {
			DateTimeFormatter formatter = DateTimeFormat.forPattern(Constantes.DDMMYYYY);
			long fechaSQL = ((formatter.parseDateTime(textoFecha)).toDateTime().getMillis());
			return new java.sql.Date(fechaSQL);
		} catch (Exception e) {
			return null;
		}
	}

	public static java.util.Date textoAFecha(String textoFecha) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date dateFecha = formatter.parse(textoFecha);
			return dateFecha;
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean esMayorDeEdad(String textoFecha) {
		try {
			LocalDate birthdate = LocalDate.parse(textoFecha, DateTimeFormat.forPattern(Constantes.DDMMYYYY));
			LocalDate now = new LocalDate();
			int edad = Years.yearsBetween(birthdate, now).getYears();
			if (edad >= 18)
				return true;
			else
				return false;
		} catch (Exception e) {
			throw e;
		}
	}

	public static String getCurrentDateString() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern(Constantes.DDMMYYYY);
		DateTime currentDate = new DateTime(new java.util.Date());
		return formatter.print(currentDate);
	}

	public static String getCurrentDateAndHoursString() {
		java.text.DateFormat dateFormat = new java.text.SimpleDateFormat(Constantes.yyyyMMddHHmmss);
		java.util.Date date = new java.util.Date();
		return dateFormat.format(date); // 2016/11/16 12:08:43
	}

	public static java.sql.Date getCurrentDateAndHoursSQL() throws Exception {
		try {
			java.text.DateFormat dateFormat = new java.text.SimpleDateFormat(Constantes.yyyyMMddHHmmss);
			java.util.Date date = new java.util.Date();
			String textoFecha = dateFormat.format(date);
			DateTimeFormatter formatter = DateTimeFormat.forPattern(Constantes.yyyyMMddHHmmss);
			long fechaSQL = ((formatter.parseDateTime(textoFecha)).toDateTime().getMillis());
			return new java.sql.Date(fechaSQL);
		} catch (Exception e) {
			throw e;
		}
	}

	public static java.util.Date getCurrentDateAndHoursJavaUtil() throws Exception {
		try {
			// java.text.DateFormat dateFormat = new
			// java.text.SimpleDateFormat(Constantes.yyyyMMddHHmmss);
			// java.util.Date date = new java.util.Date();
			return new java.util.Date();
		} catch (Exception e) {
			throw e;
		}
	}

	public static Date getDateAndHoursFromString(String dateString) throws ParseException {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		return sdf.parse(dateString);
	}

	public static int compareDateString(String dateString1, String dateString2) throws ParseException {
		Date date1 = getDateAndHoursFromString(dateString1);
		Date date2 = getDateAndHoursFromString(dateString2);

		if (date1.after(date2))
			return 1;
		if (date1.before(date2))
			return -1;
		if (date1.equals(date2))
			return 0;

		return 0;
	}

	/// *********************** OTROS ******************************///

}
