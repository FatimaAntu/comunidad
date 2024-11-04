package comunidad.comunidadVecinos;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import modelo_DAO.AgregarUsuarios;
import modelo_DTO.Usuarios;

public class ControladorP_AgregarUsuario {
	@FXML
    private TextField txtNombre;
	@FXML
    private TextField txtApellidos;
	@FXML
    private TextField txtVivienda;   
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtRepeatPassword;
		
	@FXML
    private void AggUsuario() throws IOException {
		String nombre = txtNombre.getText();
	    String apellidos = txtApellidos.getText();
	    String vivienda = txtVivienda.getText();
	    String nombreUsuario = txtVivienda.getText();
	    String contrasena = txtPassword.getText();
	    String repeatContrasena = txtRepeatPassword.getText();
	    
		Usuarios user = new Usuarios(nombre, apellidos, vivienda, nombreUsuario, contrasena);
		
		AgregarUsuarios au = new AgregarUsuarios();
		if (contrasena.equals(repeatContrasena)) {
		au.AgregarUsuario(user);
		Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Agregar Usuario");
        alerta.setHeaderText("Usuario añadido correctamente");
        alerta.showAndWait();
        
        borrarCampos();
		}else {
			// Muestra un mensaje de error
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error para agregar el usuario");
            alerta.setHeaderText("Credenciales incorrectas");
            alerta.setContentText("Las contraseñas no coinciden");
            alerta.showAndWait();
		}
    }
	
		@FXML
		public void borrarCampos() {
			txtNombre.setText("");
			txtApellidos.setText("");
			txtVivienda.setText("");
			txtPassword.setText("");
			txtRepeatPassword.setText("");
		}
	
		@FXML
	    private void cargarCRUD() throws IOException {
	        App.setRoot("P_CRUDusuarios");
	    }
}
