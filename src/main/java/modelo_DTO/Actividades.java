package modelo_DTO;

import java.util.Date;

public class Actividades {
	private int IdActividad;
	private String NombreActividad;
	private int Edad;
	private Date Fecha;
	private String Hora;
	private String HoraFin;
	private int Epoca;
	private int Plazas;
	
	public int getIdActividad() {
		return IdActividad;
	}
	public void setIdActividad(int idActividad) {
		IdActividad = idActividad;
	}
	public String getNombreActividad() {
		return NombreActividad;
	}
	public void setNombreActividad(String nombreActividad) {
		NombreActividad = nombreActividad;
	}
	public int getEdad() {
		return Edad;
	}
	public void setEdad(int edad) {
		Edad = edad;
	}
	public Date getFecha() {
		return Fecha;
	}
	public void setFecha(Date fecha) {
		Fecha = fecha;
	}
	public String getHora() {
		return Hora;
	}
	public void setHora(String hora) {
		Hora = hora;
	}
	public int getEpoca() {
		return Epoca;
	}
	public void setEpoca(int epoca) {
		Epoca = epoca;
	}
	public int getPlazas() {
		return Plazas;
	}
	public void setPlazas(int plazas) {
		Plazas = plazas;
	}
	
	public Actividades(int idActividad, String nombreActividad, Date fecha, String hora,
			String horafin, int plazas) {
		super();
		IdActividad = idActividad;
		NombreActividad = nombreActividad;
		Fecha = fecha;
		Hora = hora;
		HoraFin = horafin;
		Plazas = plazas;
	}
	
	public Actividades(String nombreActividad, int edad, Date fecha, String hora, int epoca,
			String horafin, int plazas) {
		super();
		NombreActividad = nombreActividad;
		Edad = edad;
		Fecha = fecha;
		Hora = hora;
		HoraFin = horafin;
		Epoca = epoca;
		Plazas = plazas;
	}
	
	public Actividades() {}
	@Override
	public String toString() {
		return "Actividades [IdActividad=" + IdActividad + ", NombreActividad=" + NombreActividad + ", Edad=" + Edad
				+ ", Fecha=" + Fecha + ", Hora=" + Hora + ", Epoca=" + Epoca + ", Plazas=" + Plazas + "]";
	}
	public String getHoraFin() {
		return HoraFin;
	}
	public void setHoraFin(String horaFin) {
		HoraFin = horaFin;
	};
}
