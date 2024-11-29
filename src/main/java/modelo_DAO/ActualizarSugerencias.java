package modelo_DAO;

import modelo_DTO.Sugerencias;
import java.sql.*;

public class ActualizarSugerencias {

    // Método para actualizar el estado de la denuncia
    public boolean actualizarDenuncia(Sugerencias sugerencia) {
        String sql = "UPDATE comunidad.sugerencias SET Denunciada = ? WHERE IdSugerencia = ?";

        try (Connection conn = Utilidades.Util.dameConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setBoolean(1, sugerencia.isDenunciada());
            pst.setInt(2, sugerencia.getIdSugerencia());
            int filasAfectadas = pst.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar la sugerencia: " + e.getMessage());
            e.printStackTrace();
        }
        return false; // En caso de error, devuelve false
    }
}