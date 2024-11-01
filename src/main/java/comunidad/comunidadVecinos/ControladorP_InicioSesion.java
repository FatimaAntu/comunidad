package comunidad.comunidadVecinos;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ControladorP_InicioSesion {
	   @FXML
	    private TextField usernameField;  

	    @FXML
	    private TextField passwordField;  

	    @FXML
	    private Button guardarButton;  

	    @FXML
	    private ImageView flecha; 

	    @FXML
	    public void initialize() {
	       
	    }
	    
	    @FXML
	    private void cargarInicio() throws IOException {
		
	    App.setRoot("P_Inicio");
	    }

}
