package modelo_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import modelo_DTO.Usuarios;

public class AgregarUsuarios {
	public void AgregarUsuario(Usuarios user) {
		String sql = "INSERT INTO comunidad.usuarios (Nombre, Apellidos, Vivienda, NombreUsuario, Contrasena) VALUES (?, ?, ?, ?, ?)";
		
	try(Connection conn = Utilidades.Util.dameConexion(); PreparedStatement ps = conn.prepareStatement(sql)){
		
		ps.setString(1, user.getNombre());
		ps.setString(2, user.getApellidos());
		ps.setString(3, user.getVivienda());
		ps.setString(4, user.getNombreUsuario());
		ps.setString(5, user.getContrasena());
		
		ps.executeUpdate();
	}catch(Exception e){
		e.printStackTrace();
	}
	}

	
}
