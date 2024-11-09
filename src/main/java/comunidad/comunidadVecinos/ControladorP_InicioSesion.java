package comunidad.comunidadVecinos;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
				if ("adm".equals(usuario) && "123".equals(password)) {
					
				cargarPCRUDusuarios ();
			}else {
				cargarPCalendario();}
				}
			 catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setTitle("Error de inicio de sesión");
			alerta.setHeaderText("Credenciales incorrectas");
			alerta.setContentText("Por favor, verifica tu usuario y contraseña.");
			alerta.showAndWait();
		}
	}
	
	private boolean validarCredenciales(String usuario, String password) {
		InicioSesion is = new InicioSesion();
		is.extraerNombr(usuario);
		return is.validarUsuario(usuario).equals(password);
	}
	
	@FXML
	public void borrarCampos() {
		txtUsuario.setText("");
		txtPassword.setText("");
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
	private void cargarPCRUDusuarios()throws IOException {
		App.setRoot("P_CRUDusuarios");
	}
}
