package utils;

import javax.servlet.http.HttpSession;

import frgp.utn.edu.ar.dominio.Usuario;
import utils.constantes.Constantes;

public class ORSesion {

	public static Usuario getUsuarioBySession(HttpSession session) {
		return (Usuario) session.getAttribute(Constantes.sessionUser);
	}

	public static boolean sesionActiva(HttpSession session) {
		return session.getAttribute(Constantes.sessionUser) == null ? false : true;
	}

	public static void cerrarSesion(HttpSession session) {
		session.removeAttribute(Constantes.sessionUser);
		session.invalidate();
	}

	public static void nuevaSesion(HttpSession session, Usuario obj) {
		session.setAttribute(Constantes.sessionUser, obj);
	}

}