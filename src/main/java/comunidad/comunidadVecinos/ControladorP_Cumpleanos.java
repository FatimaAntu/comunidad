package comunidad.comunidadVecinos;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modelo_DAO.InicioSesion;
import modelo_DAO.ReservarPlaza;
import modelo_DTO.Usuario_global;

public class ControladorP_Cumpleanos {
	@FXML
    private Label labelNombre;
	@FXML
	private TextField txtFecha;
	@FXML
	private TextField txtHora;
	@FXML
	private TextField txtNumeroAsistentes;
	
	private ReservarPlaza modeloReservar = new ReservarPlaza();
	
	@FXML
	public void initialize() {	
    labelNombre.setText(Usuario_global.getInstance().getNombreusuarioglobal());
	}
	@FXML
	private void cerrarSesion() throws IOException {
		App.setRoot("P_Inicio");
	}
	
	@FXML
	private void cargarPanterior() throws IOException {
		App.setRoot("P_Actividades");
	}
	
	@FXML
	public void borrarCampos() {
		txtFecha.clear();
		txtHora.clear();
		txtNumeroAsistentes.clear();
		
	}
	
	@FXML
    private void reserva(ActionEvent event) {
        // Validar los campos
        String fechaStr = txtFecha.getText();
        String horaStr = txtHora.getText();
        String asistentesStr = txtNumeroAsistentes.getText();

        if (fechaStr.isEmpty() || horaStr.isEmpty() || asistentesStr.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos deben ser completados.", Alert.AlertType.ERROR);
            return;
        }

        try {
            LocalDate fecha = LocalDate.parse(fechaStr, DateTimeFormatter.ISO_DATE);
            LocalTime hora = LocalTime.parse(horaStr, DateTimeFormatter.ISO_TIME);
            int asistentes = Integer.parseInt(asistentesStr);

            if (asistentes <= 0) {
                mostrarAlerta("Error", "El número de asistentes debe ser mayor que 0.", Alert.AlertType.ERROR);
                return;
            }

            // Llamar al modelo DAO para almacenar la reserva
            modeloReservar.reservarCumpleanos(fecha, hora, asistentes, "ViviendaPrueba");

            mostrarAlerta("Éxito", "Reserva realizada correctamente.", Alert.AlertType.INFORMATION);

        } catch (DateTimeParseException e) {
            mostrarAlerta("Error", "La fecha u hora tienen un formato incorrecto.", Alert.AlertType.ERROR);
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El número de asistentes debe ser un número válido.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            mostrarAlerta("Error", "Ocurrió un error al procesar la reserva.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
	
