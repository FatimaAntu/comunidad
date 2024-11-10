package modelo_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InicioSesion {
	public String validarUsuario(String username) {
		String sql = "SELECT Contrasena FROM comunidad.usuarios WHERE NombreUSuario = ?";
		Connection conn = Utilidades.Util.dameConexion();
		String pssw = "";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				pssw = rs.getString("Contrasena");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pssw;
	}

	public void agregarNombreUsuario(String username) {
		String sqlSelect = "SELECT Nombre, Apellidos FROM comunidad.usuarios WHERE NombreUSuario = ?";
		String sqlDelete = "DELETE FROM comunidad.nombreusuario";
		String sqlInsert = "INSERT INTO comunidad.nombreusuario (Nombre) VALUES (?)";

		String Nombre = "";
		try (Connection conn = Utilidades.Util.dameConexion(); PreparedStatement ps = conn.prepareStatement(sqlSelect)) {
			ps.setString(1, username);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Nombre = rs.getString("Nombre") + " " + rs.getString("Apellidos");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (Connection conn = Utilidades.Util.dameConexion();
				PreparedStatement stmtDelete = conn.prepareStatement(sqlDelete);
				PreparedStatement stmtInsert = conn.prepareStatement(sqlInsert)) {

			stmtDelete.executeUpdate();
			stmtInsert.setString(1, Nombre);
			stmtInsert.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String extraerN() {
		String sqlSelect = "SELECT * FROM comunidad.nombreusuario";
		String Nombre = "";
		try (Connection conn = Utilidades.Util.dameConexion();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sqlSelect)) {
			if (rs.next()) {
				Nombre = rs.getString("nombre");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Nombre;
	}
}
