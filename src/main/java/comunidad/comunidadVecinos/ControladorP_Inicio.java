package comunidad.comunidadVecinos;

import java.io.IOException;
import javafx.fxml.FXML;

public class ControladorP_Inicio {
    @FXML
    private void cargarCRUD() throws IOException {
    	
        App.setRoot("P_CRUDusuarios");
    	
    	Utilidades.Util.dameConexion();
    	if ((Utilidades.Util.dameConexion())!=null) {
    		System.out.println("Conexion establecida");
    	}else {
    		System.out.println("No se ha podido establecer la conexion");
    	}
    }
}
