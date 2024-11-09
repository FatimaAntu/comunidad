package comunidad.comunidadVecinos;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import modelo_DAO.InicioSesion;
import modelo_DTO.Actividades;

public class ControladorP_Invierno {
	@FXML
    private TableView<Actividades> miTabla;

    @FXML
    private TableColumn<Actividades, Void> botones36;

    @FXML
    private Label labelNombre;
	
	private void actualizarLabelNombre() {
		InicioSesion is = new InicioSesion();
        String nombre = is.extraerN();
        labelNombre.setText(nombre);
	}
	
	@FXML
	public void initialize() {
		actualizarLabelNombre();
	}
	
	@FXML
	private void cerrarSesion() throws IOException {
		App.setRoot("P_Inicio");
	}
	@FXML
	private void cargarPanterior() throws IOException {
		App.setRoot("P_Actividades");
	}

}
