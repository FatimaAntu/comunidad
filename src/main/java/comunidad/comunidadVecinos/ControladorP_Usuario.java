package comunidad.comunidadVecinos;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import modelo_DAO.AgregarUsuarios;
import modelo_DTO.Usuarios;

public class ControladorP_Usuario {
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

	private Usuarios usuarioSeleccionado;

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
		} else {
			// Muestra un mensaje de error
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setTitle("Error para agregar el usuario");
			alerta.setHeaderText("Credenciales incorrectas");
			alerta.setContentText("Las contraseñas no coinciden");
			alerta.showAndWait();
		}
		App.setRoot("P_CRUDusuarios");
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
	
	public void cargarUsuario(Usuarios usuario) {
	    txtNombre.setText(usuario.getNombre());
	    txtApellidos.setText(usuario.getApellidos());
	    txtVivienda.setText(usuario.getVivienda());
	    txtPassword.setText(usuario.getContrasena()); 
	    txtRepeatPassword.setText(usuario.getContrasena()); 
	    this.usuarioSeleccionado = usuario; 
	}

	@FXML
	private void modificarUsuario() throws IOException {
	    if (usuarioSeleccionado != null) {
	        // Recuperar los datos de los campos de texto
	        usuarioSeleccionado.setNombre(txtNombre.getText());
	        usuarioSeleccionado.setApellidos(txtApellidos.getText());
	        usuarioSeleccionado.setVivienda(txtVivienda.getText());
	        usuarioSeleccionado.setNombreUsuario(txtVivienda.getText());
	        usuarioSeleccionado.setContrasena(txtPassword.getText());

	        String contrasena = txtPassword.getText();
	        String repeatContrasena = txtRepeatPassword.getText();

	        // Comprobar si las contraseñas coinciden
	        if (contrasena.equals(repeatContrasena)) {
	            AgregarUsuarios au = new AgregarUsuarios();
	            au.modificarUsuario(usuarioSeleccionado); 
	            
	            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
	            alerta.setTitle("Modificar Usuario");
	            alerta.setHeaderText("Usuario modificado correctamente");
	            alerta.showAndWait();

	            borrarCampos();
	        } else {
	            // Mostrar un mensaje de error
	            Alert alerta = new Alert(Alert.AlertType.ERROR);
	            alerta.setTitle("Error al modificar el usuario");
	            alerta.setHeaderText("Credenciales incorrectas");
	            alerta.setContentText("Las contraseñas no coinciden");
	            alerta.showAndWait();
	        }
	        App.setRoot("P_CRUDusuarios");
	    } else {
	        Alert alerta = new Alert(Alert.AlertType.WARNING);
	        alerta.setTitle("Modificar Usuario");
	        alerta.setHeaderText("No hay usuario seleccionado");
	        alerta.setContentText("Seleccione un usuario para modificar.");
	        alerta.showAndWait();
	    }
	}






}
