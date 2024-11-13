package modelo_DTO;

public class Sugerencias {

	private int idSugerencia;
	private String Nombre;
	private String Apellidos;
	private String Texto;

	public Sugerencias(int idSugerencia, String nombre, String apellidos, String texto) {
		super();
		this.idSugerencia = idSugerencia;
		Nombre = nombre;
		Apellidos = apellidos;
		Texto = texto;
	}
	
	//constructor sin idSugerencia
	public Sugerencias(String nombre, String apellidos, String texto) {
		super();
		Nombre = nombre;
		Apellidos = apellidos;
		Texto = texto;
	}


	public Sugerencias() {
		super();

	}

	public int getIdSugerencia() {
		return idSugerencia;
	}

	public void setIdSugerencia(int idSugerencia) {
		this.idSugerencia = idSugerencia;
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

	

	public String getTexto() {
		return Texto;
	}

	public void setTexto(String texto) {
		Texto = texto;
	}
	
	

	@Override
	public String toString() {
		return "Sugerencias [idSugerencia=" + idSugerencia + ", Nombre=" + Nombre + ", Apellidos=" + Apellidos
				 + ", Texto=" + Texto + "]";
	}

	public static Object getSelectionModel() {
		
		return null;
	}


}
