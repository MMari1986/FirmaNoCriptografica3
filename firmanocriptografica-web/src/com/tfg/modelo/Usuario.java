package com.tfg.modelo;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("usuarioBean")
@SessionScoped
public class Usuario implements Serializable {

	private static final long serialVersionUID = -1833334162166053955L;
	
	// 1.- información de la sesión
	private String nombreUsuario;
	private String password;
	private boolean estaIdentificado = false;
	
	//2.- Infromación para Google Authenticator
	private String claveSecreta;
	private String contenidoQR = null;
	private String codigoVerificacion;

	//3.- Datos básicos de usuario
	private String numeroDocumento;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String fechaNacimiento;
	private String correoElectronico;
	
	private String  passwordValidacion;
	private boolean correoElectronicoValidado;
	
	//NOTA: Se incluyen estos dos campos por si en el futuro se quieren implementar este tipo de validaciones
	//TODO. Campo para solicitar al usuario si se desea realizar alguna validación con el dervisio SCSP
	private String campoExtraValidacion;
		
	//TODO. Campo incluido para indicar que el usuario ha sido validado presencialmente
	private boolean usuarioPresencialValidado;
	
	
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Usuario(String numeroDocumento, String nombre, String apellido1, String apellido2, String fechaNacimiento) {
		super();
		this.numeroDocumento = numeroDocumento;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public void inicializar() {
		this.numeroDocumento = null;
		this.nombre = null;
		this.apellido1 = null;
		this.apellido2 = null;
		this.fechaNacimiento = null;
		
		this.nombreUsuario = null;
		this.password = null;
		this.estaIdentificado = false;
	}


	public String getNombreUsuario() {
		return nombreUsuario;
	}


	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isEstaIdentificado() {
		return estaIdentificado;
	}


	public void setEstaIdentificado(boolean estaIdentificado) {
		this.estaIdentificado = estaIdentificado;
	}


	public String getClaveSecreta() {
		return claveSecreta;
	}


	public void setClaveSecreta(String claveSecreta) {
		this.claveSecreta = claveSecreta;
	}


	public String getContenidoQR() {
		return contenidoQR;
	}


	public void setContenidoQR(String contenidoQR) {
		this.contenidoQR = contenidoQR;
	}


	public String getCodigoVerificacion() {
		return codigoVerificacion;
	}


	public void setCodigoVerificacion(String codigoVerificacion) {
		this.codigoVerificacion = codigoVerificacion;
	}


	public String getNumeroDocumento() {
		return numeroDocumento;
	}


	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido1() {
		return apellido1;
	}


	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}


	public String getApellido2() {
		return apellido2;
	}


	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}


	public String getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public String getCorreoElectronico() {
		return correoElectronico;
	}


	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}


	public String getCampoExtraValidacion() {
		return campoExtraValidacion;
	}


	public void setCampoExtraValidacion(String campoExtraValidacion) {
		this.campoExtraValidacion = campoExtraValidacion;
	}


	public boolean isCorreoElectronicoValidado() {
		return correoElectronicoValidado;
	}


	public void setCorreoElectronicoValidado(boolean correoElectronicoValidado) {
		this.correoElectronicoValidado = correoElectronicoValidado;
	}


	public boolean isUsuarioPresencialValidado() {
		return usuarioPresencialValidado;
	}


	public void setUsuarioPresencialValidado(boolean usuarioPresencialValidado) {
		this.usuarioPresencialValidado = usuarioPresencialValidado;
	}


	public String getPasswordValidacion() {
		return passwordValidacion;
	}


	public void setPasswordValidacion(String passwordValidacion) {
		this.passwordValidacion = passwordValidacion;
	}



}

