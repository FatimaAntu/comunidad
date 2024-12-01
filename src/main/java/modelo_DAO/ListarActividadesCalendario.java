package modelo_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import modelo_DTO.Actividades;

public class ListarActividadesCalendario {
    public ArrayList<Actividades> ActividadesReservadasPorDia(String Vivienda, LocalDate diaactual) {
    	 String sql = "SELECT rv.IdRelacion, rv.IdHorario, rv.Plazasreservadas, h.Fecha, h.HoraInicio, h.HoraFin, h.Plazas, a.NombreActividad " +
                 "FROM rel_vivienda rv " +
                 "JOIN horario h ON rv.IdHorario = h.IdHorario " +
                 "JOIN actividades a ON h.IdActividad = a.IdActividad " +
                 "WHERE rv.Vivienda = ? AND h.Fecha = ?";

        ArrayList<Actividades> actividades = new ArrayList<>();

        try (Connection conn = Utilidades.Util.dameConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setString(1, Vivienda);
            ps.setDate(2, java.sql.Date.valueOf(diaactual));
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Actividades a = new Actividades();
                a.setIdActividad(rs.getInt("IdHorario"));
                a.setPlazas(rs.getInt("Plazas"));
                a.setFecha(rs.getDate("Fecha"));
                a.setHora(rs.getString("HoraInicio"));
                a.setHoraFin(rs.getString("HoraFin"));
                a.setNombreActividad(rs.getString("NombreActividad"));
                a.setPlazasReservadas(rs.getInt("Plazasreservadas"));
                a.setIdRelacion(rs.getInt("IdRelacion"));
                a.setIdHorario(rs.getInt("IdHorario"));
                actividades.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return actividades;
    }
    
    public boolean eliminarActividades(Actividades act) {
	    String sql = "DELETE FROM rel_vivienda WHERE idRelacion = ?";
	    
	    try (Connection conn = Utilidades.Util.dameConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
	        int idRelacion = act.getIdRelacion();
			ps.setInt(1, idRelacion);
	        int filasEliminadas = ps.executeUpdate();
	        	        
	        ActualizarPlazas(act.getPlazas(), act.getPlazasReservadas(), act.getIdHorario());
	        
	        return filasEliminadas > 0; 
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	    
	}
    
    public void ActualizarPlazas(int plazas, int plazasreservadas, int idhorario) {
    	String sql1 = "UPDATE comunidad.horario SET Plazas = ? WHERE IdHorario = ?";

	    try (Connection conn = Utilidades.Util.dameConexion(); PreparedStatement ps = conn.prepareStatement(sql1)) {
			int plazastot = plazas + plazasreservadas;
	    	ps.setInt(1, plazastot);
			ps.setInt(2, idhorario);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}
