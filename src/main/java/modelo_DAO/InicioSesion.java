package modelo_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo_DTO.Usuario_global;

public class InicioSesion {
	public String validarUsuario(String username) {
		String sql = "SELECT Contrasena, Nombre, Apellidos, NumHijos FROM comunidad.usuarios WHERE NombreUSuario = ?";
		Connection conn = Utilidades.Util.dameConexion();
		String pssw = "";
		String name = "";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				pssw = rs.getString("Contrasena");
				String nombre = (rs.getString("Nombre"));
				String apellidos = (rs.getString("Apellidos"));
				int numHijos = (rs.getInt("NumHijos"));
				name = (nombre +" "+ apellidos);
				Usuario_global.getInstance().setNombre(name);
				Usuario_global.getInstance().setNumHijos(numHijos);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pssw;
	}
}
