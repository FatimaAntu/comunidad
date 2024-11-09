package comunidad.comunidadVecinos;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import modelo_DAO.InicioSesion;

import java.io.IOException;

public class ControladorP_Actividades {
	@FXML
    private Label labelNombre;
	
	private void actualizarLabelNombre() {
		InicioSesion is = new InicioSesion();
        String nombre = is.extraerN(); // Obtener el nombre extraído de la base de datos
        labelNombre.setText(nombre); // Establecer el nombre en el label
	}
	
	@FXML
	public void initialize() {	
	actualizarLabelNombre();
	}
	
	
	@FXML
	private void cerrarSesion() throws IOException {
		App.setRoot("P_Inicio");
	}

	 @FXML
		private void abrirVistaCumpleanos() throws IOException {
			App.setRoot("P_Cumpleanos");
		}
	 
	 @FXML
		private void cargarCalendario() throws IOException {
			App.setRoot("P_Calendario");
		}
	 @FXML
		private void cargarInvierno() throws IOException {
			App.setRoot("P_Invierno");
		}
	 @FXML
		private void cargarVerano() throws IOException {
			App.setRoot("P_Verano");
		}
	 
	 
	}
