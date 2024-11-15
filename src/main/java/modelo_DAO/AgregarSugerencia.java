package modelo_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo_DTO.Sugerencias;

public class AgregarSugerencia {

    //agregar una sugerencia a la base de datos
    public void agregarSugerencia(Sugerencias sugerencia) {
        String sql = "INSERT INTO comunidad.sugerencias (Texto) VALUES (?)";

        try (Connection conn = Utilidades.Util.dameConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, sugerencia.getTexto());
            
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al agregar la sugerencia: " + e.getMessage());
        }
    }

	public boolean eliminarSugerencia(Sugerencias sugerenciaSeleccionada) {
		
		   String sql = "DELETE FROM comunidad.sugerencias WHERE idUsuario = ?";

		    try (Connection conn = Utilidades.Util.dameConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
		        int idSugerencia = sugerenciaSeleccionada.getIdSugerencia();
				ps.setInt(1, idSugerencia);

		        int filasEliminadas = ps.executeUpdate();
		        return filasEliminadas > 0; 
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		    
	}}
