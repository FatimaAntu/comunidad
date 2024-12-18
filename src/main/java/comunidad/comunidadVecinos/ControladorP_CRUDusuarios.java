package comunidad.comunidadVecinos;

import java.io.IOException;

import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo_DAO.AgregarUsuarios;
import modelo_DAO.Alertas;
import modelo_DAO.ListarUsuarios;
import modelo_DTO.Usuarios;

public class ControladorP_CRUDusuarios {

	@FXML
	private TableView<Usuarios> tablaUsuarios;
	@FXML
	private TableColumn<Usuarios, String> colNombre;
	@FXML
	private TableColumn<Usuarios, String> colApellidos;
	@FXML
	private TableColumn<Usuarios, String> colVivienda;

	private ObservableList<Usuarios> listaUsuarios;

	@FXML

	private void leerUsuarios() {
		ListarUsuarios ls = new ListarUsuarios();
		List<Usuarios> usuariosBBDD = ls.leerUsuarios();

		// Agrega los usuarios a la lista observable, que automáticamente actualiza la
		// tabla.
		listaUsuarios.addAll(usuariosBBDD);

	}

	public void initialize() {
		listaUsuarios = FXCollections.observableArrayList();
		tablaUsuarios.setItems(listaUsuarios);

		colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
		colVivienda.setCellValueFactory(new PropertyValueFactory<>("vivienda"));

		leerUsuarios();
	}

	@FXML
	private void cargarAgregarUsuarioParaModificar() throws IOException {
		// Obtiene el usuario seleccionado en la tabla
		Usuarios usuarioSeleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();

		if (usuarioSeleccionado != null) {
			App.usuarioParaEditar = usuarioSeleccionado;
			App.setRoot("P_AgregarUsuario");
		} else {
			Alertas a = new Alertas();
			a.alertaWarning("No has seleccionado ningún usuario");
		}
	}

	@FXML
	private void eliminarUsuario() {
		Usuarios usuarioSeleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();

		if (usuarioSeleccionado != null) {
			// Confirmar si el usuario desea eliminar el usuario seleccionado
			Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
			confirmacion.setHeaderText(null);
			confirmacion.setTitle("Confirmación");
			confirmacion.setContentText("¿Estás seguro de que deseas eliminar el usuario seleccionado?");
			Optional<ButtonType> resultado = confirmacion.showAndWait();

			if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
				// Proceder con la eliminación
				listaUsuarios.remove(usuarioSeleccionado);
				tablaUsuarios.getSelectionModel().clearSelection();

				AgregarUsuarios eliminar = new AgregarUsuarios();
				eliminar.eliminarUsuario(usuarioSeleccionado);
			} else {
				tablaUsuarios.getSelectionModel().clearSelection();
			}
		} else {
			Alertas a = new Alertas();
			a.alertaWarning("Por favor, selecciona un usuario para eliminar");
		}
	}

	// Método para abrir la ventana de revisión de sugerencias
	@FXML
	private void abrirVentanaRevisionSugerencias() {
		try {
			// Cargar la vista de administración de sugerencias
			FXMLLoader loader = new FXMLLoader(getClass().getResource("P_Admin.fxml"));
			Scene scene = new Scene(loader.load());

			// Crear y mostrar la nueva ventana (Stage)
			Stage stage = new Stage();
			stage.setTitle("Revisión de Sugerencias");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void cargarAgregarUsuario() throws IOException {
		App.setRoot("P_AgregarUsuario");
	}

	@FXML
	private void cargarPinicio() throws IOException {
		App.setRoot("P_Inicio");
	}

}