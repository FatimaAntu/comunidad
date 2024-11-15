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
        labelNombre.setText(Usuario_global.getInstance().getNombreusuarioglobal());
	}
	@FXML
	private void agregarSugerencia() throws IOException {
		String texto = txtTexto.getText();

		if (texto.isEmpty()) {
			new Alertas().alertaWarning("Todos los campos son obligatorios.");
			return;
		}

		Sugerencias nuevaSugerencia = new Sugerencias(texto);
		AgregarSugerencia agregarSugerenciaDAO = new AgregarSugerencia();
		agregarSugerenciaDAO.agregarSugerencia(nuevaSugerencia);

		new Alertas().alertaConfirmation("Sugerencia guardada con éxito");
		txtTexto.clear();
		cargarPanterior();
	}
	

	@FXML
	private void borrar() {
		txtTexto.clear();
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