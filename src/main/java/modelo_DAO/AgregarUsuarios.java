package modelo_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo_DTO.Usuarios;

public class AgregarUsuarios {
	public void AgregarUsuario(Usuarios user) {
		String sql = "INSERT INTO comunidad.usuarios (Nombre, Apellidos, Vivienda, NumHijos, NombreUsuario, Contrasena, id_pregunta_seguridad, respuesta_seguridad) VALUES (?, ?, ?, ?, ?, ?, 1, 'toby')";

		try (Connection conn = Utilidades.Util.dameConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, user.getNombre());
			ps.setString(2, user.getApellidos());
			ps.setString(3, user.getVivienda());
			ps.setString(4, user.getNumHijos());
			ps.setString(5, user.getNombreUsuario());
			ps.setString(6, user.getContrasena());

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void modificarUsuario(Usuarios user) {
		String sql = "UPDATE comunidad.usuarios SET Nombre = ?, Apellidos = ?, Vivienda = ?, NumHijos = ?, NombreUsuario = ?, Contrasena = ? WHERE IdUsuario = ?";

		try (Connection conn = Utilidades.Util.dameConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, user.getNombre());
			ps.setString(2, user.getApellidos());
			ps.setString(3, user.getVivienda());
			ps.setString(4, user.getNumHijos());
			ps.setString(5, user.getNombreUsuario());
			ps.setString(6, user.getContrasena());
			ps.setInt(7, user.getIdUsuario());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean eliminarUsuario(Usuarios user) {
	    String sql = "DELETE FROM comunidad.usuarios WHERE idUsuario = ?";

	    try (Connection conn = Utilidades.Util.dameConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
	        int idUsuario = user.getIdUsuario();
			ps.setInt(1, idUsuario);

	        int filasEliminadas = ps.executeUpdate();
	        return filasEliminadas > 0; 
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	    
	}
}