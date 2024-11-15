package modelo_DAO;



import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class Alertas {
	public void alertaWarning(String texto) {
		Alert alerta = new Alert(Alert.AlertType.WARNING);
		alerta.setTitle("Advertencia");
		alerta.setHeaderText(texto);
		alerta.showAndWait();
	}
	
	public void alertaConfirmation(String texto) {
		Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
		alerta.setTitle("Confirmación");
		alerta.setHeaderText(texto);
		alerta.showAndWait();
	}
	
	public void alertaError(String texto) {
		Alert alerta = new Alert(Alert.AlertType.ERROR);
		alerta.setTitle("Error");
		alerta.setHeaderText(texto);
		alerta.showAndWait();
	}

	public void alertaInfo(String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
	}
		
	}

	

