package comunidad.comunidadVecinos;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import modelo_DAO.AgregarSugerencia;
import modelo_DAO.Alertas;
import modelo_DAO.ListarSugerencias;
import modelo_DTO.Sugerencias;

public class ControladorP_ListaSugerencias {

	@FXML
	private TextField txtNombre;
	@FXML
	private TextField txtApellido;
	@FXML
	private TextField txtTexto;

	@FXML
	private Label labelNombre;
	@FXML
	private TableView<Sugerencias> tablaSugerencias;
	@FXML
	private TableColumn<Sugerencias, String> colTexto;

	private ObservableList<Sugerencias> listaSugerencias;

	@FXML

	public void agregarSugerenciaATabla(Sugerencias nuevaSugerencia) {
		// Agregar la sugerencia a la tabla 
		tablaSugerencias.getItems().add(nuevaSugerencia);
	}

	@FXML

	private void leerSugerencias() {
		ListarSugerencias ls = new ListarSugerencias();
		List<Sugerencias> sugerenciasBBDD = ls.leerSugerencia();

		listaSugerencias.addAll(sugerenciasBBDD);

	}

	public void initialize() {
		listaSugerencias = FXCollections.observableArrayList();
		tablaSugerencias.setItems(listaSugerencias);

		leerSugerencias();
	}

	@FXML
	private void eliminarSugerencias() {
		Sugerencias sugerenciaSeleccionada = tablaSugerencias.getSelectionModel().getSelectedItem();

		if (sugerenciaSeleccionada != null) {

			Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
			confirmacion.setHeaderText(null);
			confirmacion.setTitle("Confirmación");
			confirmacion.setContentText("¿Estás seguro de que deseas eliminar la sugerencia seleccionada?");
			Optional<ButtonType> resultado = confirmacion.showAndWait();

			if (resultado.isPresent() && resultado.get() == ButtonType.OK) {

				listaSugerencias.remove(sugerenciaSeleccionada);
				tablaSugerencias.getSelectionModel().clearSelection();

				AgregarSugerencia eliminar = new AgregarSugerencia();
				eliminar.eliminarSugerencia(sugerenciaSeleccionada);
			} else {
				tablaSugerencias.getSelectionModel().clearSelection();
			}
		} else {
			Alertas a = new Alertas();
			a.alertaWarning("Por favor, selecciona una sugerencia para eliminar");
		}
	}

	@FXML
	private void cargarAgregarSugerencia() throws IOException {
		App.setRoot("P_CrearSugerencia");
	}

	@FXML
	private void mostrarMisSugerencias() throws IOException {
		App.setRoot("P_CrearSugerencia");
	}

	@FXML
	private void cargarPinicio() throws IOException {
		App.setRoot("P_Inicio");
	}

	@FXML
	private void cerrarSesion() throws IOException {
		App.setRoot("P_Inicio");
	}

	@FXML
	private void cargarCalendario() throws IOException {
		App.setRoot("P_Calendario");
	}

}
