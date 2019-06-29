package utils.constantes;

/**
 * Contiene el nombre de los campos de la Base de Datos
 *
 */
public class ConstantesDAO {
	//MySQL
	public final String sql_STR_TO_DATE_YmdHiS = "STR_TO_DATE(?,'%Y/%m/%d %H:%i:%s')";
	// Usuarios
	public final String idUsuario = "idUsuario";
	public final String nombre = "nombre";
	public final String apellido = "apellido";
	public final String dni = "dni";
	public final String direccion = "direccion";
	public final String fechaNac = "fechaNac";
	public final String nroTelefono = "nroTelefono";
	public final String mail = "mail";
	public final String clave = "clave";
	public final String idTipoUsuario = "idTipoUsuario";
	public final String habilitado = "habilitado";
	// Nombre de tablas
	public static final String TipoExamen = "TipoExamen";
	public static final String TipoPeriodo = "TipoPeriodo";
	public static final String TipoUsuarios = "TipoUsuarios";
	public static final String Usuario = "Usuario";
	public static final String Cursos = "Cursos";      
	public static final String CursosCalificaciones = "CursosCalificaciones";
	public static final String CursosAsistencias = "CursosAsistencias";
}
	
