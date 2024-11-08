package comunidad.comunidadVecinos;

import java.io.IOException;

import javafx.fxml.FXML;

public class ControladorP_Invierno {
	@FXML
	private void cerrarSesion() throws IOException {
		App.setRoot("P_Inicio");
	}
	@FXML
	private void cargarPanterior() throws IOException {
		App.setRoot("P_Actividades");
	}

}
