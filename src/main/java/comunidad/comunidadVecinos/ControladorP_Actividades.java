package comunidad.comunidadVecinos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorP_Actividades {

	 @FXML
	    private void abrirVistaCumpleanos(ActionEvent event) {
	        try {
	            // Cargar la nueva vista desde el archivo FXML
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("P_Cumpleanos.fxml"));
	            Parent root = loader.load();

	            // Obtener el escenario actual desde el evento
	            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	            
	            // Establecer la nueva escena en el escenario
	            stage.setScene(new Scene(root));
	            stage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	            // Manejo de errores, como mostrar una alerta al usuario
	        }
	    }
	}