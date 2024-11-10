package modelo_DTO;

public class Sugerencias {

	private int idSugerencia;
	private String Nombre;
	private String Apellido;
	private String Texto;

	public Sugerencias(int idSugerencia, String nombre, String apellido, String texto) {
		super();
		this.idSugerencia = idSugerencia;
		Nombre = nombre;
		Apellido = apellido;
		Texto = texto;
	}
	
	//constructor sin idSugerencia
	public Sugerencias(String nombre, String apellido, String texto) {
		super();
		Nombre = nombre;
		Apellido = apellido;
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

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}

	

	public String getTexto() {
		return Texto;
	}

	public void setTexto(String texto) {
		Texto = texto;
	}
	
	

	@Override
	public String toString() {
		return "Sugerencias [idSugerencia=" + idSugerencia + ", Nombre=" + Nombre + ", Apellido=" + Apellido
				 + ", Texto=" + Texto + "]";
	}

	public static Object getSelectionModel() {
		
		return null;
	}

	

}
