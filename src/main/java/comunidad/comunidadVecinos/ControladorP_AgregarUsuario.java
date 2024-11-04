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

	// metodo para cargar los datos del usuario en el formulario para poder
	// modificarlos
	public void cargarDatosUsuarioEnFormulario(Usuarios usuario) {
		if (usuario != null) {
			usuarioSeleccionado = usuario;
			txtNombre.setText(usuario.getNombre());
			txtApellidos.setText(usuario.getApellidos());
			txtVivienda.setText(usuario.getVivienda());
			txtPassword.setText(usuario.getContrasena());
			txtRepeatPassword.setText(usuario.getContrasena());
		}
	}

	// metodo modificar
	@FXML
	private void modificarUsuario() {
		if (usuarioSeleccionado != null) {
			String nombre = txtNombre.getText();
			String apellidos = txtApellidos.getText();
			String vivienda = txtVivienda.getText();
			String nombreUsuario = txtVivienda.getText();
			String contrasena = txtPassword.getText();

			Usuarios user = new Usuarios(nombre, apellidos, vivienda, nombreUsuario, contrasena);
			user.setIdUsuario(usuarioSeleccionado.getIdUsuario());

			AgregarUsuarios au = new AgregarUsuarios();
			boolean exito = au.modificarUsuario(user);
			if (exito) {
				Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
				alerta.setTitle("Modificar Usuario");
				alerta.setHeaderText("Usuario modificado correctamente");
				alerta.showAndWait();

				borrarCampos();
			} else {
				
				Alert alerta = new Alert(Alert.AlertType.ERROR);
				alerta.setTitle("Error para modificar el usuario");
				alerta.setHeaderText("No se pudo modificar el usuario");
				alerta.showAndWait();
			}
		} else {
			Alert alerta = new Alert(Alert.AlertType.WARNING);
			alerta.setTitle("Advertencia");
			alerta.setHeaderText("No hay usuario seleccionado para modificar");
			alerta.showAndWait();
		}
	}

}
