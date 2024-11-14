package comunidad.comunidadVecinos;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import modelo_DAO.Alertas;
import modelo_DAO.InicioSesion;

public class ControladorP_InicioSesion {
	@FXML
	private TextField txtUsuario;

	@FXML
	private PasswordField txtPassword;
	
	private final Alertas alerta = new Alertas();

	@FXML
	private void iniciarSesion() {
		String usuario = txtUsuario.getText();
		String password = txtPassword.getText();

		if (usuario.isBlank() || password.isBlank()) {
			alerta.alertaWarning("Todos los campos son obligatorios");
			return;
		}

		if (validarCredenciales(usuario, password)) {
			try {
				if ("adm".equals(usuario) && "123".equals(password)) {
					cargarPCRUDusuarios();
				} else {
					cargarPCalendario();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			alerta.alertaError("Por favor, verifica tu usuario y contraseña");
		}
	}

	private boolean validarCredenciales(String usuario, String password) {
		InicioSesion inicioSesion = new InicioSesion();
		return password.equals(inicioSesion.validarUsuario(usuario));
	}

	@FXML
	public void borrarCampos() {
		txtUsuario.clear();
		txtPassword.clear();
	}

	@FXML
	private void cargarPCalendario() throws IOException {
		App.setRoot("P_Calendario");
	}

	@FXML
	private void cargarPinicio() throws IOException {
		App.setRoot("P_Inicio");
	}

	@FXML
	private void cargarPCRUDusuarios() throws IOException {
		App.setRoot("P_CRUDusuarios");
	}
}
