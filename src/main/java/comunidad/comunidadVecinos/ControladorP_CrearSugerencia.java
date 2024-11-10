package comunidad.comunidadVecinos;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo_DAO.AgregarSugerencia;
import modelo_DAO.Alertas;
import modelo_DTO.Sugerencias;

public class ControladorP_CrearSugerencia {

	@FXML
	private Button botonBorrar;

	@FXML
	private Button botonGuardar;

	@FXML
	private TextField txtNombre;

	@FXML
	private TextField txtApellido;

	@FXML
	private TextField txtTexto;

	@FXML
	private void agregarSugerencia() throws IOException {
		String nombre = txtNombre.getText().trim();
		String apellido = txtApellido.getText().trim();
		String texto = txtTexto.getText().trim();

		if (nombre.isEmpty() || apellido.isEmpty() || texto.isEmpty()) {
			new Alertas().alertaWarning("Todos los campos son obligatorios.");
			return;
		}

		Sugerencias nuevaSugerencia = new Sugerencias(nombre, apellido, texto);
		AgregarSugerencia agregarSugerenciaDAO = new AgregarSugerencia();
		agregarSugerenciaDAO.agregarSugerencia(nuevaSugerencia);

		Alert alerta = new Alert(Alert.AlertType.INFORMATION);
		alerta.setHeaderText(null);
		alerta.setContentText("Sugerencia guardada con éxito.");
		alerta.showAndWait();

		txtNombre.clear();
		txtApellido.clear();
		txtTexto.clear();

		// Cerrar la ventana actual
		Stage stage = (Stage) botonGuardar.getScene().getWindow();
		stage.close();

		// Regresar a la vista anterior y pasar la sugerencia para que se muestre en la tabla
		cargarListaSugerencias(nuevaSugerencia);
	}

	@FXML
	private void borrar() {
		Stage stage = (Stage) botonBorrar.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void cargarListaSugerencias(Sugerencias nuevaSugerencia) throws IOException {

		App.setRoot("P_ListaSugerencias");
	}

	@FXML
	private void cargarCrearSugerencias() throws IOException {
		App.setRoot("P_CrearSugerencia");
	}

	@FXML
	private void cerrarSesion() throws IOException {
		App.setRoot("P_Inicio");
	}

	@FXML
	private void cargarPanterior() throws IOException {
		App.setRoot("P_ListaSugerencias");
	}
}
