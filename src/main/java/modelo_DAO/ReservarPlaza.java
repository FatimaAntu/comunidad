package modelo_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservarPlaza {
	public void ReservarPlazas(int actividad, int plazas, String vivienda) {
		String sql = "SELECT Plazas FROM comunidad.horario WHERE IdHorario= ?";
		String sql1 = "UPDATE comunidad.horario SET Plazas = ? WHERE IdHorario = ?";
		String sql2 = "INSERT INTO comunidad.rel_vivienda (IdActividad, Vivienda, Plazasreservadas) VALUES (?, ?, ?)";
		int plazasDisp = 0;
		try (Connection conn = Utilidades.Util.dameConexion(); PreparedStatement ps = conn.prepareStatement(sql)){
		ps.setInt(1, actividad);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			plazasDisp = rs.getInt("Plazas");
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	try (Connection conn = Utilidades.Util.dameConexion(); PreparedStatement ps = conn.prepareStatement(sql1)){
		ps.setInt(1, (plazasDisp-plazas));
		ps.setInt(2, actividad);
		ps.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	try (Connection conn = Utilidades.Util.dameConexion(); PreparedStatement ps = conn.prepareStatement(sql2)){
		ps.setInt(1, actividad);
		ps.setString(2, vivienda);
		ps.setInt(3, plazas);
		ps.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
}