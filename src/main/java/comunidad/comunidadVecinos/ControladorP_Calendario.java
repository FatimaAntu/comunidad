package comunidad.comunidadVecinos;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import modelo_DAO.InicioSesion;

public class ControladorP_Calendario {
	@FXML
    private GridPane gridCalendario;
    @FXML
    private Label labelMes;
    @FXML
    private Label labelNombre;
    
    private YearMonth mesActual;
    
    public ControladorP_Calendario() {
        mesActual = YearMonth.now();
    }
	
	private void actualizarLabelNombre() {
		InicioSesion is = new InicioSesion();
        String nombre = is.extraerN(); // Obtener el nombre extraído de la base de datos
        labelNombre.setText(nombre); // Establecer el nombre en el label
	}
    @FXML
    public void initialize() {
        // Configura las restricciones solo una vez
        configurarRestricciones();
        // Método de inicialización donde se llama a actualizarMes para cargar el mes actual
        actualizarMes();
        actualizarLabelNombre();
    }

    private void actualizarMes() {
        // Actualiza el título del mes y año actual
        labelMes.setText(mesActual.getMonth().name() + " " + mesActual.getYear());

        // Muestra el calendario del mes actual
        mostrarCalendario(mesActual);
    }

    public void mostrarCalendario(YearMonth mes) {
    	 Locale.setDefault(new Locale("es", "ES"));
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy", new Locale("es", "ES"));
         
         // Actualiza el nombre del mes en el Label
         labelMes.setText(mes.format(formatter));
        gridCalendario.getChildren().clear();  // Limpiar el calendario antes de volver a mostrarlo

        // Días de la semana
        String[] diasSemana = {"Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom"};
        for (int i = 0; i < diasSemana.length; i++) {
            Label labelDia = new Label(diasSemana[i]);
            labelDia.setStyle("-fx-font-weight: bold; -fx-alignment: center; -fx-pref-width: 100;");
            gridCalendario.add(labelDia, i, 0);  //primera fila para los días de la semana
        }
        

        // Días del mes
        LocalDate primerDiaMes = mes.atDay(1);
        int diaInicio = primerDiaMes.getDayOfWeek().getValue() % 7;  // Ajuste para que lunes sea el inicio (0 = lunes)
        int diasEnMes = mes.lengthOfMonth();

        for (int i = 0; i < diasEnMes; i++) {
            LocalDate dia = mes.atDay(i + 1);

            // Cada día en un StackPane
            StackPane diaPane = new StackPane(new Label(String.valueOf(i + 1)));
            diaPane.setStyle("-fx-border-color: #CCCCCC; -fx-padding: 5; -fx-alignment: center;");

            // Colocando el día en el calendario
            int col = (i + diaInicio) % 7;  
            int fila = (i + diaInicio) / 7 + 1; 
            gridCalendario.add(diaPane, col, fila);
        }
    }

    private void configurarRestricciones() {
        //ajuste de las columnas 
        for (int i = 0; i < 7; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(100.0 / 7); 
            gridCalendario.getColumnConstraints().add(columnConstraints);
        }

       //ajuste de las filas
        int numFilas = 6; 
        for (int i = 0; i < numFilas; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(javafx.scene.layout.Priority.ALWAYS); 
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
	private void cerrarSesion() throws IOException {
		App.setRoot("P_Inicio");
	}
	
	@FXML
	private void cargarListaSugerencias() throws IOException {
		App.setRoot("P_ListaSugerencias");
	}
	
	@FXML
	private void cargarActividades() throws IOException {
		App.setRoot("P_Actividades");
	}
}
