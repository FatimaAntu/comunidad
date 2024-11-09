package modelo_DAO;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

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
}
