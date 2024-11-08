package modelo_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import modelo_DTO.Actividades;

public class ListarActividades {
	public ArrayList<Actividades> leerActividades(int epoca) {
		String sql = "SELECT * FROM comunidad.actividades WHERE Epoca = ?";
		ArrayList<Actividades> actividades = new ArrayList<Actividades>();
		try (Connection conn = Utilidades.Util.dameConexion();
				Statement st = conn.createStatement();
				PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setInt(1, epoca);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int IdActividad = rs.getInt("IdActividad");
				String NombreActividad = rs.getString("NombreActividad");
				int edad = rs.getInt("Edad");
				Date Fecha= rs.getDate("Fecha");
				String Hora = rs.getString("Hora");
				int plazas = rs.getInt("Plazas");
				
				Actividades actividad = new Actividades(IdActividad, NombreActividad, edad, Fecha, Hora, plazas);
				actividades.add(actividad);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actividades;
	}
}