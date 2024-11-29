package modelo_DAO;



import modelo_DTO.Sugerencias;
import java.sql.*;

public class EliminarSugerencias {

    // Método para eliminar una sugerencia
    public boolean eliminarSugerencia(Sugerencias sugerencia) {
        String sql = "DELETE FROM comunidad.sugerencias WHERE IdSugerencia = ?";

        try (Connection conn = Utilidades.Util.dameConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, sugerencia.getIdSugerencia());
            int filasAfectadas = pst.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar la sugerencia: " + e.getMessage());
            e.printStackTrace();
        }
        return false; // En caso de error, devuelve false
    }
}