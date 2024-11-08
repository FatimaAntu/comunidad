package comunidad.comunidadVecinos;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

public class ControladorP_Calendario {
	@FXML
    private GridPane gridCalendario;
    @FXML
    private Label labelMes;

    private YearMonth mesActual;

    public ControladorP_Calendario() {
        mesActual = YearMonth.now();
    }

    

    @FXML
    public void initialize() {
        // Configura las restricciones solo una vez
        configurarRestricciones();
        // Método de inicialización donde se llama a actualizarMes para cargar el mes actual
        actualizarMes();
    }

    private void actualizarMes() {
        // Actualiza el título del mes y año actual
        labelMes.setText(mesActual.getMonth().name() + " " + mesActual.getYear());

        // Muestra el calendario del mes actual
        mostrarCalendario(mesActual);
    }

    public void mostrarCalendario(YearMonth mes) {
        gridCalendario.getChildren().clear();  // Limpiar el calendario antes de volver a mostrarlo

        // Días de la semana
        String[] diasSemana = {"Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom"};
        for (int i = 0; i < diasSemana.length; i++) {
            Label labelDia = new Label(diasSemana[i]);
            labelDia.setStyle("-fx-font-weight: bold; -fx-alignment: center; -fx-pref-width: 100;");
            gridCalendario.add(labelDia, i, 0);  // Primera fila para los días de la semana
        }

        // Días del mes
        LocalDate primerDiaMes = mes.atDay(1);
        int diaInicio = primerDiaMes.getDayOfWeek().getValue() % 7;  // Ajuste para que lunes sea el inicio (0 = lunes)
        int diasEnMes = mes.lengthOfMonth();

        for (int i = 0; i < diasEnMes; i++) {
            LocalDate dia = mes.atDay(i + 1);

            // Cada día en un StackPane (para facilitar la adición de actividades en el futuro)
            StackPane diaPane = new StackPane(new Label(String.valueOf(i + 1)));
            diaPane.setStyle("-fx-border-color: #CCCCCC; -fx-padding: 5; -fx-alignment: center;");

            // Colocando el día en el calendario
            int col = (i + diaInicio) % 7;  // Columna en la que va el día
            int fila = (i + diaInicio) / 7 + 1;  // Fila en la que va el día
            gridCalendario.add(diaPane, col, fila);
        }
    }

    private void configurarRestricciones() {
        // Ajusta las columnas para que todas tengan el mismo tamaño
        for (int i = 0; i < 7; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(100.0 / 7); // Dividir el espacio equitativamente
            gridCalendario.getColumnConstraints().add(columnConstraints);
        }

        // Ajusta las filas para que todas tengan el mismo tamaño
        int numFilas = 6;  // El calendario tiene un máximo de 6 filas (aunque no todos los meses las usen)
        for (int i = 0; i < numFilas; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(javafx.scene.layout.Priority.ALWAYS);  // Ajustar altura
            gridCalendario.getRowConstraints().add(rowConstraints);
        }
    }

    @FXML
    private void siguienteMes() {
        mesActual = mesActual.plusMonths(1);
        actualizarMes();  // Actualizar el mes y mostrarlo
    }

    @FXML
    private void anteriorMes() {
        mesActual = mesActual.minusMonths(1);
        actualizarMes();  // Actualizar el mes y mostrarlo
    }
	
	@FXML
	private void cargarPinicio() throws IOException {
		App.setRoot("P_Inicio");
	}
}
