package modelo_DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modelo_DTO.Usuarios;

public class ListarUsuarios {
	public ArrayList<Usuarios> leerUsuarios() {
		String sql = "SELECT * FROM comunidad.usuarios";
		ArrayList<Usuarios> usuarios = new ArrayList<Usuarios>();
		try (Connection conn = Utilidades.Util.dameConexion();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql)) {
			while (rs.next()) {
				int IdUsuario = rs.getInt("IdUsuario");
				String Nombre = rs.getString("Nombre");
				;
				String Apellidos = rs.getString("Apellidos");
				String Vivienda = rs.getString("Vivienda");
				String NombreUsuario = rs.getString("NombreUsuario");
				String Contrasena = rs.getString("Contrasena");

				Usuarios usuario = new Usuarios(IdUsuario, Nombre, Apellidos, Vivienda, NombreUsuario, Contrasena);
				usuarios.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
	}
}
