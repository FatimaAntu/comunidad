package comunidad.comunidadVecinos;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import modelo_DAO.InicioSesion;

public class ControladorP_Verano {
	@FXML
    private Label labelNombre;
	
	private void actualizarLabelNombre() {
		InicioSesion is = new InicioSesion();
        String nombre = is.extraerN();
        labelNombre.setText(nombre);
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
	private void cargarPanterior() throws IOException {
		App.setRoot("P_Actividades");
	}

}
