package modelo_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InicioSesion {
	public String validarUsuario (String username) {
		String sql = "SELECT Contrasena FROM comunidad.usuarios WHERE NombreUSuario = ?";
		Connection conn = Utilidades.Util.dameConexion();
		String pssw = "";
		try (PreparedStatement ps = conn.prepareStatement(sql)){
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
}
