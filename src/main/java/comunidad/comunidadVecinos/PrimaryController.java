package comunidad.comunidadVecinos;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {
    @FXML
    private void switchToSecondary() throws IOException {
    	
        App.setRoot("secondary");
    	
    	Utilidades.Util.dameConexion();
    	if ((Utilidades.Util.dameConexion())!=null) {
    		System.out.println("Conexion establecida");
    	}else {
    		System.out.println("No se ha podido establecer la conexion");
    	}
    }
}
