package comunidad.comunidadVecinos;

import java.io.IOException;
import javafx.fxml.FXML;

public class ControladorP_Inicio {
	@FXML
	private void cargarCRUD() throws IOException {
		App.setRoot("P_CRUDusuarios");
	}

	@FXML
	private void cargarInicioSesion() throws IOException {

		App.setRoot("P_InicioSesion");
	}

}