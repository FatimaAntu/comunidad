package modelo_DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo_DTO.Sugerencias;


public class ListarSugerencias {
	public ArrayList<Sugerencias> leerSugerencia() {
		String sql = "SELECT * FROM comunidad.sugerencias";
		ArrayList<Sugerencias> sugerencias = new ArrayList<Sugerencias>();
		try (Connection conn = Utilidades.Util.dameConexion();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql)) {
			while (rs.next()) {
				int IdSugerencia = rs.getInt("IdSugerencia");
				String Nombre = rs.getString("Nombre");
				String Apellidos = rs.getString("Apellidos");
				String Texto = rs.getString("Texto");
				
				 Sugerencias sugerencia = new Sugerencias(IdSugerencia, Nombre, Apellidos,Texto);
	                sugerencias.add(sugerencia);   

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sugerencias;
	}

}
