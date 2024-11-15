package comunidad.comunidadVecinos;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Utilidades.Util;

public class ControladorP_RecuperacionContrasenia {

    @FXML
    private TextField nombreUsuarioTextField;
    @FXML
    private Button mostrarPreguntaButton;
    @FXML
    private Label preguntaLabel;
    @FXML
    private TextField respuestaTextField;
    @FXML
    private PasswordField nuevaContrasenaTextField;
    @FXML
    private Button cambiarContrasenaButton;

    

    @FXML
    private void mostrarPreguntaSeguridad() {
        String nombreUsuario = nombreUsuarioTextField.getText();

        try (Connection conn = Util.dameConexion()) {
            //JOIN entre usuarios y preguntas_seguridad
            String sql = "SELECT p.pregunta FROM preguntas_seguridad p " +
                         "JOIN usuarios u ON u.id_pregunta_seguridad = p.id " +
                         "WHERE u.NombreUsuario = ?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombreUsuario);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                // Si encontramos la pregunta de seguridad
                String pregunta = rs.getString("pregunta");
                preguntaLabel.setText(pregunta);
            } else {
                // Si no se encuentra el usuario o no tiene pregunta de seguridad
                mostrarAlerta( "Usuario no encontrado o no tiene pregunta de seguridad.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            mostrarAlerta("Hubo un problema al recuperar la pregunta de seguridad.");
        }
    }

    // Método para cambiar la contraseña
    @FXML
    private void cambiarContrasena() {
        String nombreUsuario = nombreUsuarioTextField.getText();
        String respuesta = respuestaTextField.getText();
        String nuevaContrasena = nuevaContrasenaTextField.getText();

        // Verificar que los campos no estén vacíos
        if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) {
            mostrarAlerta( "Por favor ingresa un nombre de usuario.");
            return;
        }
        if (respuesta == null || respuesta.trim().isEmpty()) {
            mostrarAlerta( "Por favor ingresa la respuesta a la pregunta de seguridad.");
            return;
        }
        if (nuevaContrasena == null || nuevaContrasena.trim().isEmpty()) {
            mostrarAlerta("Por favor ingresa una nueva contraseña.");
            return;
        }

        try (Connection conn = Util.dameConexion()) {
            // Verificar la respuesta a la pregunta de seguridad
            String sql = "SELECT * FROM usuarios WHERE NombreUsuario = ? AND respuesta_seguridad = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombreUsuario);
            stmt.setString(2, respuesta);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Si la respuesta es correcta, actualizar la contraseña
                String updateSql = "UPDATE usuarios SET Contrasena = ? WHERE NombreUsuario = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setString(1, nuevaContrasena);
                updateStmt.setString(2, nombreUsuario);
                updateStmt.executeUpdate();

                mostrarAlerta("Contraseña cambiada correctamente.");
                try {
					cargarPanterior();
				} catch (IOException e) {
					e.printStackTrace();
				}
            } else {
                mostrarAlerta( "Respuesta incorrecta.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            mostrarAlerta("Hubo un problema al cambiar la contraseña.");
        }
    }

    // Método para mostrar alertas
    private void mostrarAlerta( String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    @FXML
	private void cargarPanterior() throws IOException {
		App.setRoot("P_InicioSesion");
	}

}
