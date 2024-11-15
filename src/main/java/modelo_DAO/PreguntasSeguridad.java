//package modelo_DAO;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class PreguntasSeguridad {
//    private Connection connection;
//
//    public PreguntasSeguridad(Connection connection) {
//        this.connection = connection;
//    }
//
//    public List<String> obtenerPreguntas() throws SQLException {
//        List<String> preguntas = new ArrayList<>();
//        String query = "SELECT pregunta FROM preguntas_seguridad";
//        try (PreparedStatement ps = connection.prepareStatement(query);
//             ResultSet rs = ps.executeQuery()) {
//            while (rs.next()) {
//                preguntas.add(rs.getString("pregunta"));
//            }
//        }
//        return preguntas;
//    }
//}

