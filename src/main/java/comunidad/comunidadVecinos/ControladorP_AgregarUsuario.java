package comunidad.comunidadVecinos;

import java.io.IOException;

import javafx.fxml.FXML;
import modelo.Usuarios;

public class ControladorP_AgregarUsuario {
	Usuarios user = new Usuarios();
	
	@FXML
	    private void cargarCRUD() throws IOException {
	        App.setRoot("P_CRUDusuarios");
	    }
}
