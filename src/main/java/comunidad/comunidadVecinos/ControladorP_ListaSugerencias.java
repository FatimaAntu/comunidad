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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo_DAO.Alertas;
import modelo_DAO.ListarSugerencias;
import modelo_DTO.Sugerencias;
import modelo_DTO.Usuario_global;

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
	public void agregarSugerenciaTabla(Sugerencias nuevaSugerencia) {
		// Agregar la sugerencia a la tabla
		tablaSugerencias.getItems().add(nuevaSugerencia);
	}

	public void initialize() {

		listaSugerencias = FXCollections.observableArrayList();
		tablaSugerencias.setItems(listaSugerencias);
		colTexto.setCellValueFactory(new PropertyValueFactory<>("texto"));

		// Cambiar el color de las filas denunciadas
		tablaSugerencias.setRowFactory(tv -> new TableRow<Sugerencias>() {
			@Override
			protected void updateItem(Sugerencias sugerencia, boolean empty) {
				super.updateItem(sugerencia, empty);

				if (sugerencia != null && sugerencia.isDenunciada()) {
					setStyle("-fx-background-color: #ffcccc;"); // Rojo claro para denunciadas
				} else {
					setStyle(""); // Restaurar estilo por defecto
				}
			}
		});
		// Mostrar el nombre del usuario en la etiqueta
		labelNombre.setText(Usuario_global.getInstance().getNombreusuarioglobal());
		//la tabla está configurada, cargar las sugerencias desde la bbdd
		leerSugerencias();

	}

	public void leerSugerencias() {
		ListarSugerencias ls = new ListarSugerencias();
		List<Sugerencias> sugerenciasBBDD = ls.leerSugerencia();

		if (sugerenciasBBDD != null && !sugerenciasBBDD.isEmpty()) {
			listaSugerencias.clear(); // Limpiar la lista antes de cargar nuevas sugerencias
			listaSugerencias.addAll(sugerenciasBBDD);
			tablaSugerencias.refresh(); // Refrescar la tabla
		}

		// Aplicar un RowFactory para manejar los colores basados en el estado denunciada
		tablaSugerencias.setRowFactory(tv -> new TableRow<Sugerencias>() {
			@Override
			protected void updateItem(Sugerencias sugerencia, boolean empty) {
				super.updateItem(sugerencia, empty);

				if (empty || sugerencia == null) {
					setStyle(""); // Sin estilo para filas vacías
				} else if (sugerencia.isDenunciada()) { // Comprueba si está denunciada
					setStyle("-fx-background-color: lightcoral;"); // Color para denunciadas
				} else {
					setStyle(""); // Restablecer estilo para las demás
				}
			}
		});
	}

	@FXML
	private void cargarDenunciarSugerencia() {
		Sugerencias sugerenciaSeleccionada = tablaSugerencias.getSelectionModel().getSelectedItem();

		if (sugerenciaSeleccionada != null) {
			Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
			confirmacion.setHeaderText(null);
			confirmacion.setTitle("Denunciar Sugerencia");
			confirmacion.setContentText("¿Estás seguro de que deseas denunciar esta sugerencia?");
			Optional<ButtonType> resultado = confirmacion.showAndWait();

			if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
				// Denunciar la sugerencia en la base de datos
				ListarSugerencias dao = new ListarSugerencias();
				boolean exito = dao.denunciarSugerencia(sugerenciaSeleccionada.getIdSugerencia());

				if (exito) {
					Alert exitoAlerta = new Alert(Alert.AlertType.INFORMATION);
					exitoAlerta.setHeaderText(null);
					exitoAlerta.setTitle("Denuncia Enviada");
					exitoAlerta.setContentText("La sugerencia ha sido denunciada correctamente.");
					exitoAlerta.showAndWait();

					// Actualizar el estado de denuncia en la lista observable
					sugerenciaSeleccionada.setDenunciada(true);
					tablaSugerencias.refresh();
				} else {
					Alert errorAlerta = new Alert(Alert.AlertType.ERROR);
					errorAlerta.setHeaderText(null);
					errorAlerta.setTitle("Error");
					errorAlerta.setContentText("No se pudo denunciar la sugerencia. Inténtalo de nuevo.");
					errorAlerta.showAndWait();
				}
			}
		} else {
			Alertas a = new Alertas();
			a.alertaWarning("Por favor, selecciona una sugerencia para denunciar.");
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