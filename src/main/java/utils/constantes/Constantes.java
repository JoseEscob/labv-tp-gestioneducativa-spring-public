package utils.constantes;

/**
 * Clase para definir constantes
 *
 * Rutas de archivos
 * 
 * @author José
 */
public class Constantes {
	// Utilizados como variables
	public final static String DDMMYYYY = "dd/MM/yyyy";// "dd/MM/yyyy";"
	public final static String yyyyMMddHHmmss = "yyyy/MM/dd HH:mm:ss";// "dd/MM/yyyy";"
	public final static String sessionUser = "sessionUser";
	public final static String accionGET = "accionGET";
	public final static String accionPOST = "accionPOST";
	public final static int idTipoUsuarioAlumn = 1;
	public final static int idTipoUsuarioProfe = 2;
	public final static int idTipoUsuarioAdmin = 3;

	// Mensajes reutilizables
	public final static String REGISTROEXITOSO = "Se registraron los datos de forma exitosa";
	public final static String logJSPAccion = "JSP - Acción: ";
	public final static String RedireccionandoA = "Redireccionando a: ";
	public final static String indexJsp = "/index.jsp";
	public final static String usuarioSinPermisos = "Usted no tiene permisos para realizar esta accción";
	public final static String msgSesionFinalizada = "Su sesión fue cerrada exitosamente. Hasta luego";
	public final static String msgUsuarioPermisosAlumn = "El usuario logueado debe ser un alumno";
	public final static String msgUsuarioPermisosProfe = "El usuario logueado debe ser un profesor";
}
