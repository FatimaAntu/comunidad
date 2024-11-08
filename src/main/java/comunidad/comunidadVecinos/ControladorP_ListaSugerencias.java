package comunidad.comunidadVecinos;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import modelo_DAO.AgregarSugerencia;
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
	private TableView<Sugerencias> tablaSugerencias;

	private ObservableList<Sugerencias> listaSugerencias;

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
	private void cargarAgregarSugerencia() throws IOException {
		App.setRoot("P_CrearSugerencia");
	}

	@FXML
	private void AggSugerencia() {
		
		String nombre = txtNombre.getText();
		String apellido = txtApellido.getText();
		String texto = txtTexto.getText();

		// Verificar  campos no esten vacios
		if (nombre.isEmpty() || apellido.isEmpty() || texto.isEmpty()) {
			Alert alerta = new Alert(Alert.AlertType.WARNING);
			alerta.setHeaderText("Todos los campos son obligatorios");
			alerta.showAndWait();
			return;
		}

		//crear nueva sugerencia y agregarla
		Sugerencias nuevaSugerencia = new Sugerencias( nombre, apellido, texto);
		AgregarSugerencia as = new AgregarSugerencia();
		as.AgregarSugerencia(nuevaSugerencia);

		//añadir la sugerencia a la lista y actualizar la tabla
		listaSugerencias.add(nuevaSugerencia);

		//mostrar confirmacion
		Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
		alerta.setHeaderText("Sugerencia añadida");
		alerta.showAndWait();

		//limpiar los campos
		txtNombre.clear();
		txtApellido.clear();
		txtTexto.clear();
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
