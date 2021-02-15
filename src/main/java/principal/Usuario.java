package principal;

import java.util.ArrayList;

public class Usuario {
	
	private String nombre, apellido1, apellido2, nombreUsuario, email, nacionalidad, dni, contrasenna;
	private String telefono;

	//atributos para el perfil:
	private String foto, lengua,  calendario, historial;
	private int calificacion;
	private ArrayList<String> intereses;
	
	
	public Usuario() {
		super();
		this.intereses = new ArrayList<String>();
	}
	//	Constructor de Usuario solo con username y password, utilizado en la comprobación del login.
	public Usuario(String username, String password) {
		super();
		this.nombreUsuario = username;
		this.contrasenna = password;
		this.intereses = new ArrayList<String>();
	}
	
	public Usuario(String nombre, String apellido1, String apellido2, String nombreUsuario, String email,
			String nacionalidad, String dni, String telefono, String contrasenna) {
		super();
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.nombreUsuario = nombreUsuario;
		this.email = email;
		this.nacionalidad = nacionalidad;
		this.dni = dni;
		this.telefono = telefono;
		this.contrasenna = contrasenna;
		this.intereses = new ArrayList<String>();

	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return this.apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return this.apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNacionalidad() {
		return this.nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getContrasenna() {
		return this.contrasenna;
	}

	public void setContrasenna(String contrasenna) {
		this.contrasenna = contrasenna;
	}

	public String getFoto() { return this.foto;	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getHistorial() {
		return this.historial;
	}

	public void setHistorial(String historial) {
		this.historial = historial;
	}

	public ArrayList<String> getIntereses() {
		return this.intereses;
	}

	public void setIntereses(String interes) { this.intereses.add(interes);	}

	public String getLengua() {
		return this.lengua;
	}

	public void setLengua(String lengua) {
		this.lengua = lengua;
	}

	public int getCalificacion() {
		return this.calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public String getCalendario() {
		return this.calendario;
	}

	public void setCalendario(String calendario) {
		this.calendario = calendario;
	}


	
	
	
	
}
