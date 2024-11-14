package modelo_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import modelo_DTO.Actividades;

public class ListarActividades {
    public ArrayList<Actividades> leerActividades(int epoca, int edad) {
        String sql = "SELECT a.idActividad, a.NombreActividad, a.Plazas, h.Fecha, h.HoraInicio, h.HoraFin " +
                     "FROM comunidad.actividades a " +
                     "JOIN comunidad.horario h ON a.idActividad = h.idActividad " +
                     "WHERE h.Epoca = ? AND h.Edad = ? AND h.Fecha >= CURDATE() ORDER BY h.Fecha ASC, h.HoraInicio ASC";
        
        ArrayList<Actividades> actividades = new ArrayList<Actividades>();
        
        try (Connection conn = Utilidades.Util.dameConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            // Set parameters
            ps.setInt(1, epoca);
            ps.setInt(2, edad);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idActividad = rs.getInt("idActividad");
                String nombreActividad = rs.getString("NombreActividad");
                Date fecha = rs.getDate("Fecha");
                String horaInicio = rs.getString("HoraInicio");
                String horaFin = rs.getString("HoraFin");
                int plazas = rs.getInt("Plazas");
                
                // Create Actividades object
                Actividades actividad = new Actividades(idActividad, nombreActividad, fecha, horaInicio, horaFin, plazas);
                actividades.add(actividad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return actividades;
    }
}
