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
	public void initialize() {
		if (App.usuarioParaEditar != null) {
			cargarUsuario(App.usuarioParaEditar); // Carga el usuario para editar en los campos de texto
			usuarioSeleccionado = App.usuarioParaEditar;
			App.usuarioParaEditar = null; // Limpia la referencia después de cargar el usuario
		}
	}

	@FXML
	private void AggUsuario() throws IOException {
		String nombre = txtNombre.getText();
		String apellidos = txtApellidos.getText();
		String vivienda = txtVivienda.getText();
		String nombreUsuario = txtVivienda.getText(); 
		String contrasena = txtPassword.getText();
		String repeatContrasena = txtRepeatPassword.getText();
		
		// Verificar  campos no esten vacios
		if (nombre.isEmpty() || apellidos.isEmpty() || vivienda.isEmpty()|| nombreUsuario.isEmpty()|| contrasena.isEmpty()|| repeatContrasena.isEmpty()) {
			Alert alerta = new Alert(Alert.AlertType.WARNING);
			alerta.setHeaderText("Todos los campos son obligatorios");
			alerta.showAndWait();
			return;
		}

		if (!contrasena.equals(repeatContrasena)) {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setTitle("Error al guardar");
			alerta.setHeaderText("Credenciales incorrectas");
			alerta.setContentText("Las contraseñas no coinciden");
			alerta.showAndWait();
			return;
		}

		AgregarUsuarios au = new AgregarUsuarios();

		if (usuarioSeleccionado == null) {
			Usuarios nuevoUsuario = new Usuarios(nombre, apellidos, vivienda, nombreUsuario, contrasena);
			au.AgregarUsuario(nuevoUsuario);

			Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
			alerta.setTitle("Agregar Usuario");
			alerta.setHeaderText("Usuario añadido correctamente");
			alerta.showAndWait();
		} else {
			usuarioSeleccionado.setNombre(nombre);
			usuarioSeleccionado.setApellidos(apellidos);
			usuarioSeleccionado.setVivienda(vivienda);
			usuarioSeleccionado.setNombreUsuario(nombreUsuario);
			usuarioSeleccionado.setContrasena(contrasena);

			au.modificarUsuario(usuarioSeleccionado);

			Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
			alerta.setTitle("Modificar Usuario");
			alerta.setHeaderText("Usuario modificado correctamente");
			alerta.showAndWait();
		}

		borrarCampos();
		usuarioSeleccionado = null;
		App.setRoot("P_CRUDusuarios"); // Volver a la vista CRUD de usuarios
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
			// Cambiar a la vista de edición y cargar el usuario seleccionado en los campos
			// de texto
			App.setRoot("P_Usuario");
			cargarUsuario(usuarioSeleccionado);
		} else {
			// Mostrar advertencia si no hay usuario seleccionado para modificar
			Alert alerta = new Alert(Alert.AlertType.WARNING);
			alerta.setTitle("Modificar Usuario");
			alerta.setHeaderText("No hay usuario seleccionado");
			alerta.setContentText("Seleccione un usuario para modificar.");
			alerta.showAndWait();
		}
	}
}
