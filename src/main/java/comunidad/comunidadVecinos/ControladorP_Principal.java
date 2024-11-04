package comunidad.comunidadVecinos;

import java.io.IOException;

import javafx.fxml.FXML;

public class ControladorP_Principal {
	@FXML
    private void cargarPinicio() throws IOException {
        App.setRoot("P_Inicio");
    }
}
