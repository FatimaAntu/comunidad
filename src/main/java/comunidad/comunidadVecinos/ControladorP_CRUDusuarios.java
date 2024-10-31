package comunidad.comunidadVecinos;

import java.io.IOException;
import javafx.fxml.FXML;

public class ControladorP_CRUDusuarios {

    @FXML
    private void cargarAgregarUsuario() throws IOException {
        App.setRoot("P_AgregarUsuario");
    }
    
    @FXML
    private void cargarPinicio() throws IOException {
        App.setRoot("P_Inicio");
    }
}