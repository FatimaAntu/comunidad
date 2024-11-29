package comunidad.comunidadVecinos;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modelo_DAO.AgregarSugerencia;
import modelo_DAO.Alertas;
import modelo_DTO.Sugerencias;
import modelo_DTO.Usuario_global;

public class ControladorP_CrearSugerencia {

	@FXML
	private Button botonBorrar;

	@FXML
	private Button botonGuardar;

	@FXML
	private TextField txtTexto;

	@FXML
	private Label labelNombre;

	@FXML
	public void initialize() {
		// Establece el nombre del usuario global en la etiqueta
		labelNombre.setText(Usuario_global.getInstance().getNombreusuarioglobal());
	}

	// Método para agregar una nueva sugerencia
	@FXML
	private void agregarSugerencia() throws IOException {
		// Obtener el texto de la sugerencia
		String texto = txtTexto.getText();

		// Verificar si el texto está vacío
		if (texto.isEmpty()) {
			// Mostrar alerta si el campo está vacío
			new Alertas().alertaWarning("Todos los campos son obligatorios.");
			return;
		}

		// Crear la nueva sugerencia
		Sugerencias nuevaSugerencia = new Sugerencias(texto);

		// Llamar al método DAO para agregar la sugerencia a la base de datos
		AgregarSugerencia agregarSugerenciaDAO = new AgregarSugerencia();
		agregarSugerenciaDAO.agregarSugerencia(nuevaSugerencia);

		// Mostrar mensaje de éxito
		new Alertas().alertaConfirmation("Sugerencia guardada con éxito");

		// Limpiar el campo de texto
		txtTexto.clear();

		// Volver a la vista anterior
		cargarPanterior();
	}

	// Método para borrar el texto en el campo de sugerencia
	@FXML
	private void borrar() {
		txtTexto.clear();
	}

	// Método para cargar la vista actual (P_CrearSugerencia)
	@FXML
	private void cargarCrearSugerencias() throws IOException {
		App.setRoot("P_CrearSugerencia");
	}

	// Método para cerrar sesión
	@FXML
	private void cerrarSesion() throws IOException {
		App.setRoot("P_Inicio");
	}

	// Método para cargar la vista de sugerencias
	@FXML
	private void cargarPanterior() throws IOException {
		App.setRoot("P_ListaSugerencias");
	}
}