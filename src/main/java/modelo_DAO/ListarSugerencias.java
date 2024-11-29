package modelo_DAO;

import modelo_DTO.Sugerencias;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListarSugerencias {

    // Método para obtener todas las sugerencias (incluyendo las denunciadas)
    public List<Sugerencias> leerSugerencia() {
        String sql = "SELECT * FROM comunidad.sugerencias";
        List<Sugerencias> sugerencias = new ArrayList<>();

        try (Connection conn = Utilidades.Util.dameConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                int idSugerencia = rs.getInt("IdSugerencia");
                String texto = rs.getString("Texto");
                boolean denunciada = rs.getBoolean("denunciada"); // Obtener el estado denunciada

                // Validar si el texto es nulo
                if (texto == null) {
                    texto = "Texto vacío"; // Asigna un valor por defecto si está vacío
                }

                Sugerencias sugerencia = new Sugerencias(idSugerencia, texto, denunciada);
                sugerencias.add(sugerencia);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sugerencias;
    }

    // Método para obtener solo las sugerencias denunciadas
    public List<Sugerencias> leerSugerenciaDenunciada() {
        String sql = "SELECT * FROM comunidad.sugerencias WHERE denunciada = TRUE";
        List<Sugerencias> sugerenciasDenunciadas = new ArrayList<>();

        try (Connection conn = Utilidades.Util.dameConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                int idSugerencia = rs.getInt("IdSugerencia");
                String texto = rs.getString("Texto");

                if (texto == null) {
                    texto = "Texto vacío"; // Asigna un valor por defecto si está vacío
                }

                Sugerencias sugerencia = new Sugerencias(idSugerencia, texto);
                sugerenciasDenunciadas.add(sugerencia);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener las sugerencias denunciadas: " + e.getMessage());
            e.printStackTrace();
        }

        return sugerenciasDenunciadas;
    }

    // Método para denunciar una sugerencia
    public boolean denunciarSugerencia(int idSugerencia) {
        String sql = "UPDATE comunidad.sugerencias SET Denunciada = TRUE WHERE IdSugerencia = ?";

        try (Connection conn = Utilidades.Util.dameConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idSugerencia);
            int filasAfectadas = pst.executeUpdate();

            return filasAfectadas > 0; // Devuelve true si al menos una fila fue actualizada

        } catch (SQLException e) {
            System.err.println("Error al denunciar la sugerencia: " + e.getMessage());
            e.printStackTrace();
        }
        return false; // En caso de error, devuelve false
    }

    // Método para eliminar una sugerencia (cuando la denuncia es aceptada)
    public boolean eliminarSugerencia(int idSugerencia) {
        String sql = "DELETE FROM comunidad.sugerencias WHERE IdSugerencia = ?";

        try (Connection conn = Utilidades.Util.dameConexion(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idSugerencia);
            int filasAfectadas = pst.executeUpdate();

            return filasAfectadas > 0; // Devuelve true si al menos una fila fue eliminada

        } catch (SQLException e) {
            System.err.println("Error al eliminar la sugerencia: " + e.getMessage());
            e.printStackTrace();
        }
        return false; // En caso de error, devuelve false
    }

 // Método para marcar una sugerencia como denunciada
 	public boolean cargarDenunciarSugerencia(int idSugerencia) {
 		String sql = "UPDATE comunidad.sugerencias SET Denunciada = TRUE WHERE IdSugerencia = ?";
 		try (Connection conn = Utilidades.Util.dameConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

 			ps.setInt(1, idSugerencia);
 			int rowsUpdated = ps.executeUpdate();
 			return rowsUpdated > 0; // Devuelve true si se actualizó correctamente

 		} catch (SQLException e) {
 			System.err.println("Error al denunciar la sugerencia: " + e.getMessage());
 			e.printStackTrace();
 			return false;
 		}
 	}

}