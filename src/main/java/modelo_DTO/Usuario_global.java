package modelo_DTO;

public class Usuario_global {
	private static Usuario_global instance = null;
	private String nombreusuarioglobal;
	private int numHijos;
	private String vivienda;

	private Usuario_global() {}
	
	public static Usuario_global getInstance() {
		if (instance == null) {
			instance = new Usuario_global();
		}
		return instance;
	}
	public String getNombreusuarioglobal() {
		return nombreusuarioglobal;
	}
	
	public void setNombre(String nombre) {
		this.nombreusuarioglobal = nombre;
	}

	public int getNumHijos() {
		return numHijos;
	}

	public void setNumHijos(int numHijos) {
		this.numHijos = numHijos;
	}

	public String getVivienda() {
		return vivienda;
	}

	public void setVivienda(String vivienda) {
		this.vivienda = vivienda;
	}
	
	
}
