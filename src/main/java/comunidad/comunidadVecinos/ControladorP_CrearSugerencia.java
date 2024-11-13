package comunidad.comunidadVecinos;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo_DAO.AgregarSugerencia;
import modelo_DAO.Alertas;
import modelo_DAO.InicioSesion;
import modelo_DTO.Sugerencias;

public class ControladorP_CrearSugerencia {

	@FXML
	private Button botonBorrar;

	@FXML
	private Button botonGuardar;

	@FXML
	private TextField txtNombre;

	@FXML
	private TextField txtApellidos;

	@FXML
	private TextField txtTexto;

	@FXML
    private Label labelNombre;
	
	private void actualizarLabelNombre() {
		InicioSesion is = new InicioSesion();
        String nombre = is.extraerN();
        labelNombre.setText(nombre);
	}
	
	@FXML
	public void initialize() {
		actualizarLabelNombre();
	}
	@FXML
	private void agregarSugerencia() throws IOException {
		String nombre = txtNombre.getText().trim();
		String apellidos = txtApellidos.getText().trim();
		String texto = txtTexto.getText().trim();

		if (nombre.isEmpty() || apellidos.isEmpty() || texto.isEmpty()) {
			new Alertas().alertaWarning("Todos los campos son obligatorios.");
			return;
		}

		Sugerencias nuevaSugerencia = new Sugerencias(nombre, apellidos, texto);
		AgregarSugerencia agregarSugerenciaDAO = new AgregarSugerencia();
		agregarSugerenciaDAO.agregarSugerencia(nuevaSugerencia);

		Alert alerta = new Alert(Alert.AlertType.INFORMATION);
		alerta.setHeaderText(null);
		alerta.setContentText("Sugerencia guardada con éxito.");
		alerta.showAndWait();

		txtNombre.clear();
		txtApellidos.clear();
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