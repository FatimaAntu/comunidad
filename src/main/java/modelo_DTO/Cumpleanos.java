package modelo_DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cumpleanos {
    private String nombrePersona;
    private LocalDate fecha;
    private LocalTime hora;
    private int numeroAsistentes;
    
	public String getNombrePersona() {
		return nombrePersona;
	}
	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public LocalTime getHora() {
		return hora;
	}
	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
	public int getNumeroAsistentes() {
		return numeroAsistentes;
	}
	public void setNumeroAsistentes(int numeroAsistentes) {
		this.numeroAsistentes = numeroAsistentes;
	}

}

