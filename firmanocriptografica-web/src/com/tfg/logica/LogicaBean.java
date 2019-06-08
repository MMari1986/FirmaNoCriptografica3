package com.tfg.logica;


import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import com.tfg.modelo.Usuario;
import com.tfg.utilidades.Constantes;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;

@Named("logicaBean")
@SessionScoped
public class LogicaBean implements Serializable {

	private static final long serialVersionUID = -575620218868403586L;
	
	private Locale locale;

	//Contiene la accion que esta realizando el usuario
	private String operacion;

	public LogicaBean() {
		super();
		this.locale = new Locale("es");
		FacesContext.getCurrentInstance().getViewRoot().setLocale(this.locale);
	}
	
	public String altaNuevoUsuario(Usuario usuario) {
		boolean  altaFirmada = false;
		GoogleAuthenticator gAuth = new GoogleAuthenticator();
		
		switch (this.operacion) {
	        case "altaUsuario":  
	        	usuario.setNombreUsuario(usuario.getNumeroDocumento() + "@rus");
	        	//Generar la imagen de google autenticator a mostrar
	        	
	    	    GoogleAuthenticatorKey key = gAuth.createCredentials();
	    	    usuario.setClaveSecreta(key.getKey());
	    	    usuario.setContenidoQR(GoogleAuthenticatorQRGenerator.getOtpAuthTotpURL(Constantes.NOMBRE_APLICACION, usuario.getNombreUsuario(), key));
	        	break;
	        case "firmarAltaUsuario":
	        	altaFirmada = gAuth.authorize(usuario.getClaveSecreta(), Integer.parseInt(usuario.getCodigoVerificacion()));
	        	usuario.setEstaIdentificado(altaFirmada);
	        	break;
		}
		if(altaFirmada) {
			return Constantes.PAGINA_PERFILUSUARIO;
		}
		
		return Constantes.PAGINA_REGISTRO;
	}
	
	public boolean existeUsuario(Usuario usuario) {
		return false;
	}
	
	public String identificaUsuario(Usuario usuario) {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		if (usuario.getNombreUsuario() != null && usuario.getNombreUsuario().equals("admin") && usuario.getPassword()!= null &&  usuario.getPassword().equals("admin")) {
			usuario.setEstaIdentificado(true);
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", usuario.getNombreUsuario());
		} else {
			usuario.setEstaIdentificado(false);
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Credenciales no v�lidas");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		context.addCallbackParam("estaLogeado", usuario.isEstaIdentificado());
		
		if (usuario.isEstaIdentificado()) {
			return Constantes.PAGINA_PERFILUSUARIO;
		}
		
		return Constantes.PAGINA_ACTUAL;
	
	}
	
	public String terminarSesion(Usuario usuario) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		usuario.inicializar();
		return Constantes.PAGINA_INICIO;		
	}
	
	/*
	public void login(ActionEvent actionEvent) {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		if (usuario.getNombreUsuario() != null && usuario.getNombreUsuario().equals("admin") && clave. != null && clave.equals("admin")) {
			logeado = true;
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", usuario);
		} else {
			logeado = false;
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Credenciales no v�lidas");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		context.addCallbackParam("estaLogeado", logeado);
		if (logeado)
			context.addCallbackParam("view", "perfilusuario.xhtml");
	}
	
	
	public void logout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		logeado = false;
	}
	*/

	
	public String getLanguage() {
        return locale.getLanguage();
    }
	

	public String getLanguageDescription() {
		return locale.getDisplayName();
	}
	
	
	
	
	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

}
