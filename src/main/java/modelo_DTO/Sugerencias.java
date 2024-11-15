package modelo_DTO;

public class Sugerencias {

	private int idSugerencia;
	private String Texto;

	public Sugerencias(int idSugerencia, String texto) {
		super();
		this.idSugerencia = idSugerencia;
		Texto = texto;
	}
	
	//constructor sin idSugerencia
	public Sugerencias(String texto) {
		super();
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

	public String getTexto() {
		return Texto;
	}

	public void setTexto(String texto) {
		Texto = texto;
	}
	
	@Override
	public String toString() {
		return "Sugerencias [idSugerencia=" + idSugerencia + ", Texto=" + Texto + "]";
	}

	public static Object getSelectionModel() {
		
		return null;
	}


}
