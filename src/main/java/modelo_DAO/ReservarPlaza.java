package modelo_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import Utilidades.Util;

public class ReservarPlaza {
	public void ReservarPlazas(int actividad, int plazas, String vivienda) {
		String sql = "SELECT Plazas FROM comunidad.horario WHERE IdHorario= ?";
		String sql1 = "UPDATE comunidad.horario SET Plazas = ? WHERE IdHorario = ?";
		String sql2 = "INSERT INTO comunidad.rel_vivienda (IdHorario, Vivienda, Plazasreservadas) VALUES (?, ?, ?)";
		int plazasDisp = 0;
		try (Connection conn = Utilidades.Util.dameConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, actividad);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				plazasDisp = rs.getInt("Plazas");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try (Connection conn = Utilidades.Util.dameConexion(); PreparedStatement ps = conn.prepareStatement(sql1)) {
			ps.setInt(1, (plazasDisp - plazas));
			ps.setInt(2, actividad);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try (Connection conn = Utilidades.Util.dameConexion(); PreparedStatement ps = conn.prepareStatement(sql2)) {
			ps.setInt(1, actividad);
			ps.setString(2, vivienda);
			ps.setInt(3, plazas);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void reservarCumpleanos(LocalDate fecha, LocalTime hora, int numero_asistentes, String vivienda) {
		String sql = "INSERT INTO comunidad.reservas (fecha, hora, numero_asistentes) VALUES (?, ?, ?)";

		try (Connection conn = Utilidades.Util.dameConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setDate(1, java.sql.Date.valueOf(fecha));
			ps.setTime(2, java.sql.Time.valueOf(hora));
			ps.setInt(3, numero_asistentes);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error al realizar la reserva", e);
		}
	}

	public int comprobarReservas(int idhorario, String vivienda) {
		int pr = 0;
		String sql = "SELECT Plazasreservadas FROM rel_vivienda WHERE IdHorario = ? AND Vivienda = ?";
		try (Connection conn = Util.dameConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, idhorario);
			ps.setString(2, vivienda);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pr = rs.getInt("Plazasreservadas");
			}
			return pr;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pr;
	}
}