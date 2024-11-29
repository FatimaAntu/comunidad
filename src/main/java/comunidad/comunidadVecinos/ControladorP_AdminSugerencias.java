package comunidad.comunidadVecinos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo_DAO.ListarSugerencias;
import modelo_DAO.EliminarSugerencias;
import modelo_DAO.ActualizarSugerencias;
import modelo_DTO.Sugerencias;
import java.util.List;
import java.util.Optional;

public class ControladorP_AdminSugerencias {

	@FXML
	private TableView<Sugerencias> tablaSugerencias;
	@FXML
	private TableColumn<Sugerencias, String> colTexto;

	private ObservableList<Sugerencias> listaSugerencias;

	public void initialize() {
		listaSugerencias = FXCollections.observableArrayList();
		tablaSugerencias.setItems(listaSugerencias);

		colTexto.setCellValueFactory(new PropertyValueFactory<>("texto"));

		// Cargar las sugerencias denunciadas
		cargarSugerenciasDenunciadas();

	}

	private void cargarSugerenciasDenunciadas() {
		ListarSugerencias ls = new ListarSugerencias();
		List<Sugerencias> sugerenciasBBDD = ls.leerSugerenciaDenunciada(); // Llamada correcta al DAO

		listaSugerencias.clear(); // Limpiar la lista antes de agregar las sugerencias denunciadas
		listaSugerencias.addAll(sugerenciasBBDD);
	}

	// Método para aceptar la denuncia (borrar la sugerencia)
	@FXML
	private void aceptarDenuncia() {
		Sugerencias sugerenciaSeleccionada = tablaSugerencias.getSelectionModel().getSelectedItem();

		if (sugerenciaSeleccionada != null) {
			// Confirmar la acción
			Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
			confirmacion.setHeaderText(null);
			confirmacion.setTitle("Confirmación");
			confirmacion.setContentText("¿Estás seguro de que deseas aceptar la denuncia y eliminar esta sugerencia?");
			Optional<ButtonType> resultado = confirmacion.showAndWait();

			if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
				// Eliminar la sugerencia de la base de datos
				EliminarSugerencias eliminar = new EliminarSugerencias();
				boolean eliminado = eliminar.eliminarSugerencia(sugerenciaSeleccionada);

				if (eliminado) {
					// Actualizar la tabla
					listaSugerencias.remove(sugerenciaSeleccionada);
				} else {
					Alert alertaError = new Alert(Alert.AlertType.ERROR);
					alertaError.setTitle("Error");
					alertaError.setContentText("No se pudo eliminar la sugerencia.");
					alertaError.showAndWait();
				}
			}
		} else {
			Alert alerta = new Alert(Alert.AlertType.WARNING);
			alerta.setTitle("Advertencia");
			alerta.setContentText("Por favor, selecciona una sugerencia para aceptar.");
			alerta.showAndWait();
		}
	}

	// Método para rechazar la denuncia (quitar el resaltado de la sugerencia)
	@FXML
	private void rechazarDenuncia() {
		Sugerencias sugerenciaSeleccionada = tablaSugerencias.getSelectionModel().getSelectedItem();

		if (sugerenciaSeleccionada != null) {
			// Confirmar la acción
			Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
			confirmacion.setHeaderText(null);
			confirmacion.setTitle("Confirmación");
			confirmacion.setContentText("¿Estás seguro de que deseas rechazar la denuncia y quitar el resaltado?");
			Optional<ButtonType> resultado = confirmacion.showAndWait();

			if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
				// Actualizar la sugerencia para quitar el resaltado
				sugerenciaSeleccionada.setDenunciada(false);

				// Actualizar la base de datos
				ActualizarSugerencias actualizar = new ActualizarSugerencias();
				boolean actualizado = actualizar.actualizarDenuncia(sugerenciaSeleccionada);

				if (actualizado) {
					// Actualizar la interfaz
					tablaSugerencias.refresh();
				} else {
					Alert alertaError = new Alert(Alert.AlertType.ERROR);
					alertaError.setTitle("Error");
					alertaError.setContentText("No se pudo rechazar la denuncia de la sugerencia.");
					alertaError.showAndWait();
				}
			}
		} else {
			Alert alerta = new Alert(Alert.AlertType.WARNING);
			alerta.setTitle("Advertencia");
			alerta.setContentText("Por favor, selecciona una sugerencia para rechazar.");
			alerta.showAndWait();
		}
	}
}