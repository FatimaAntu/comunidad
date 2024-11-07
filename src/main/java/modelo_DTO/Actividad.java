package modelo_DTO;

import java.time.LocalDate;

public class Actividad {
	private LocalDate fecha;
    private String nombre;
    private String descripcion;

  
    public Actividad(LocalDate fecha, String nombre, String descripcion) {
        this.fecha = fecha;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

  
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
