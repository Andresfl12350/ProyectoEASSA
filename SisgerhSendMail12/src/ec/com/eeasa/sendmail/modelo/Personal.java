/*
* Kempresa.java 27/11/14
* Copyright 2014 INEC-DIZ3C, Inc. All rights reserved.
*/

package ec.com.eeasa.sendmail.modelo;

/**
* Clase Personal. Contiene los metodos necesarios para el manejo de la informacion de la tabla PERSONAL.
* @author: DC
* @version: 1.0
*/
public class Personal{
	private String departamento;
	private String rol;
	private String empleado;
	private String cargo;
	private String email;
	
	public Personal() {
	}
	
	public Personal(String departamento, String rol, String empleado,
			String rel_laboral, String cargo, String email) {
		super();
		this.departamento = departamento;
		this.rol = rol;
		this.empleado = empleado;
		this.cargo = cargo;
		this.email = email;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getRol() {
		return rol;
	}


	public void setRol(String rol) {
		this.rol = rol;
	}


	public String getEmpleado() {
		return empleado;
	}


	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

	public String getCargo() {
		return cargo;
	}


	public void setCargo(String cargo) {
		this.cargo = cargo;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	
}