package modelo_DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo_DTO.Sugerencias;

public class ListarSugerencias {

    // Cambié el tipo de retorno a List<Sugerencias> para mayor flexibilidad
    public List<Sugerencias> leerSugerencia() {
        String sql = "SELECT * FROM comunidad.sugerencias";
        List<Sugerencias> sugerencias = new ArrayList<>();  
        try (Connection conn = Utilidades.Util.dameConexion();  // Asumiendo que la conexión se obtiene de esta clase
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            // Iteramos a través de los resultados de la consulta
            while (rs.next()) {
                // Obtener los valores de cada columna
                int IdSugerencia = rs.getInt("IdSugerencia");
                String Nombre = rs.getString("Nombre");
                String Apellidos = rs.getString("Apellidos");
                String Texto = rs.getString("Texto");
                
                // Crear un objeto Sugerencias con los datos obtenidos
                Sugerencias sugerencia = new Sugerencias(IdSugerencia, Nombre, Apellidos, Texto);
                
                // Añadir la sugerencia a la lista
                sugerencias.add(sugerencia);
            }

        } catch (SQLException e) {
            // Puedes agregar más detalles de la excepción si lo deseas
            System.err.println("Error al obtener las sugerencias: " + e.getMessage());
            e.printStackTrace();
        }
        
        return sugerencias;  // Devolver la lista de sugerencias
    }
}

