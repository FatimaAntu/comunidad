package modelo_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo_DTO.Sugerencias;

public class AgregarSugerencia {

    // Método para agregar una sugerencia a la base de datos
    public void AgregarSugerencia(Sugerencias sugerencia) {
        String sql = "INSERT INTO comunidad.sugerencias (Nombre, Apellido, Texto) VALUES (?, ?, ?)";

        try (Connection conn = Utilidades.Util.dameConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, sugerencia.getNombre());
            pst.setString(2, sugerencia.getApellido());
            pst.setString(3, sugerencia.getTexto());
            
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al agregar la sugerencia: " + e.getMessage());
        }
    }
}
