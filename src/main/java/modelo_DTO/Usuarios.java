package modelo_DTO;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Usuarios {
	private int idUsuario;
	private String Nombre;
	private String Apellidos;
	private String Vivienda;
	private String NombreUsuario;
	private String Contrasena;
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellidos() {
		return Apellidos;
	}
	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}
	public String getVivienda() {
		return Vivienda;
	}
	public void setVivienda(String vivienda) {
		Vivienda = vivienda;
	}
	public String getNombreUsuario() {
		return NombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		NombreUsuario = nombreUsuario;
	}
	public String getContrasena() {
		return Contrasena;
	}
	public void setContrasena(String contrasena) {
		Contrasena = contrasena;
	}
	
	//Constructor con todos los atributos
	public Usuarios(int idUsuario, String nombre, String apellidos, String vivienda, String nombreUsuario, String contrasena) {
		super();
		this.idUsuario = idUsuario;
		Nombre = nombre;
		Apellidos = apellidos;
		Vivienda = vivienda;
		NombreUsuario = nombreUsuario;
		Contrasena = contrasena;
	}
	
	//Constructor sin el idUsuario ya que este será autoincrement, por lo tanto no hará falta dicho atributo para realizar algunas consultas
	public Usuarios(String nombre, String apellidos, String vivienda, String nombreUsuario, String contrasena) {
		super();
		Nombre = nombre;
		Apellidos = apellidos;
		Vivienda = vivienda;
		NombreUsuario = nombreUsuario;
		Contrasena = contrasena;
	}
	
	//Constructor vacío
	public Usuarios() {
	}
	
	
}
