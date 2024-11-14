package comunidad.comunidadVecinos;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import modelo_DAO.AgregarUsuarios;
import modelo_DAO.Alertas;
import modelo_DTO.Usuarios;

public class ControladorP_AgregarUsuario {
	@FXML
	private TextField txtNombre;
	@FXML
	private TextField txtApellidos;
	@FXML
	private TextField txtVivienda;
	@FXML
	private TextField txtNumHijos;
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
		String numHijos = txtNumHijos.getText(); 
		String nombreUsuario = txtVivienda.getText(); 
		String contrasena = txtPassword.getText();
		String repeatContrasena = txtRepeatPassword.getText();
		
		// Verificar  campos no esten vacios
		if (nombre.isEmpty() || apellidos.isEmpty() || vivienda.isEmpty() || numHijos.isEmpty()|| nombreUsuario.isEmpty()|| contrasena.isEmpty()|| repeatContrasena.isEmpty()) {
			Alertas a = new Alertas();
            a.alertaWarning("Todos los campos son obligatorios");
            return;
		}

		if (!contrasena.equals(repeatContrasena)) {
			Alertas a = new Alertas();
			a.alertaError("Credenciales incorrectas, las contraseñas no coinciden");
			return;
		}

		AgregarUsuarios au = new AgregarUsuarios();

		if (usuarioSeleccionado == null) {
			Usuarios nuevoUsuario = new Usuarios(nombre, apellidos, vivienda, numHijos, nombreUsuario, contrasena);
			au.AgregarUsuario(nuevoUsuario);

			Alertas a = new Alertas();
			a.alertaConfirmation("Usuario añadido correctamente");
		} else {
			usuarioSeleccionado.setNombre(nombre);
			usuarioSeleccionado.setApellidos(apellidos);
			usuarioSeleccionado.setVivienda(vivienda);
			usuarioSeleccionado.setNumHijos(numHijos);
			usuarioSeleccionado.setNombreUsuario(nombreUsuario);
			usuarioSeleccionado.setContrasena(contrasena);

			au.modificarUsuario(usuarioSeleccionado);

			Alertas a = new Alertas();
			a.alertaConfirmation("Usuario modificado correctamente");
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
		txtNumHijos.setText("");
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
		txtNumHijos.setText(usuario.getNumHijos());
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
			Alertas a = new Alertas();
			a.alertaWarning("No hay usuario seleccionado, seleccione un usuario para modificar.");
		}
	}
}
