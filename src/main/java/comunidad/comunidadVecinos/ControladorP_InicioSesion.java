package comunidad.comunidadVecinos;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo_DAO.InicioSesion;

public class ControladorP_InicioSesion {

	@FXML
	private TextField txtUsuario;

	@FXML
	private PasswordField txtPassword;

	// metodo que se ejecuta al presionar el boton de login
	@FXML
	private void iniciarSesion() {
		String usuario = txtUsuario.getText();
		String password = txtPassword.getText();

		if (validarCredenciales(usuario, password)) {
			try {
				cargarPprincipal();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// Cerrar la ventana de login y abrir la ventana principal
		} else {
			// Muestra un mensaje de error
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setTitle("Error de inicio de sesión");
			alerta.setHeaderText("Credenciales incorrectas");
			alerta.setContentText("Por favor, verifica tu usuario y contraseña.");
			alerta.showAndWait();
		}
	}

	// metodo que valida las credenciales
	private boolean validarCredenciales(String usuario, String password) {
		InicioSesion is = new InicioSesion();
		return is.validarUsuario(usuario).equals(password);
	}

	@FXML
	private void cargarPprincipal() throws IOException {
		App.setRoot("P_Principal");
	}

	@FXML
	private void cargarPinicio() throws IOException {
		App.setRoot("P_Inicio");
	}
}
