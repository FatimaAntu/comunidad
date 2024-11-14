package comunidad.comunidadVecinos;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import modelo_DAO.InicioSesion;
import modelo_DTO.Usuario_global;

public class ControladorP_Cumpleanos {
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
	private void cargarPanterior() throws IOException {
		App.setRoot("P_Actividades");
	}
	
}