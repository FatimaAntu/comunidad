package modelo_DTO;

public class Sugerencias {

	private int idSugerencia; 
	private String texto; 
	private boolean denunciada;

	
	public Sugerencias(int idSugerencia, String texto, boolean denunciada) {
		this.idSugerencia = idSugerencia;
		this.texto = texto;
		this.denunciada = denunciada;
	}

	// Constructor para texto solamente (usado para nuevas sugerencias sin ID ni
	// estado denunciado)
	public Sugerencias(String texto) {
		this.idSugerencia = 0; // Por defecto, sin asignar un ID
		this.texto = texto;
		this.denunciada = false; // Por defecto, no denunciada
	}

	// Constructor para ID y texto (por si no se requiere estado denunciado
	// explícito)
	public Sugerencias(int idSugerencia, String texto) {
		this.idSugerencia = idSugerencia;
		this.texto = texto;
		this.denunciada = false; // Por defecto, no denunciada
	}

	// Getters y Setters
	public int getIdSugerencia() {
		return idSugerencia;
	}

	public void setIdSugerencia(int idSugerencia) {
		this.idSugerencia = idSugerencia;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public boolean isDenunciada() {
		return denunciada;
	}

	public void setDenunciada(boolean denunciada) {
		this.denunciada = denunciada;
	}

	// Método toString para depuración y representación de datos
	@Override
	public String toString() {
		return "Sugerencias [idSugerencia=" + idSugerencia + ", texto=" + texto + ", denunciada=" + denunciada + "]";
	}

	public static Object getSelectionModel() {

		return null;
	}

}