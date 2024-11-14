package comunidad.comunidadVecinos;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import modelo_DTO.Usuario_global;

import java.io.IOException;

public class ControladorP_Actividades {
	@FXML
    private Label labelNombre;
	
	@FXML
	public void initialize() {	
    labelNombre.setText(Usuario_global.getInstance().getNombreusuarioglobal());
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
