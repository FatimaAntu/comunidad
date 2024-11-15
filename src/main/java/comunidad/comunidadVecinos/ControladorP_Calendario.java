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
        String nombre = is.extraerN(); 
        labelNombre.setText(nombre); 
	}
    @FXML
    public void initialize() {
     
        configurarRestricciones();
        
        actualizarMes();
        actualizarLabelNombre();
    }

    private void actualizarMes() {
        // Actualiza el  mes y año actual
        labelMes.setText(mesActual.getMonth().name() + " " + mesActual.getYear());

        // Muestra el calendario del mes actual
        mostrarCalendario(mesActual);
    }

    public void mostrarCalendario(YearMonth mes) {
        Locale.setDefault(new Locale("es", "ES"));//para que salga en español
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy", new Locale("es", "ES"));

        // Actualiza el nombre del mes en el Label
        labelMes.setText(mes.format(formatter));
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
        int diaInicio = primerDiaMes.getDayOfWeek().getValue();  // 1=lunes, 7=domingo

        // Ajuste: si el primer día es domingo (7), lo pondremos en la última columna (6)
        int columnaInicio = (diaInicio == 7) ? 6 : diaInicio - 1;

        int diasEnMes = mes.lengthOfMonth();

        for (int i = 0; i < diasEnMes; i++) {
            LocalDate dia = mes.atDay(i + 1);

            // Cada día en un StackPane
            StackPane diaPane = new StackPane(new Label(String.valueOf(i + 1)));
            diaPane.setStyle("-fx-border-color: #CCCCCC; -fx-padding: 5; -fx-alignment: center;");

            // Calcular la columna y la fila
            int col = (i + columnaInicio) % 7;  // Determinar la columna (0-6)
            int fila = (i + columnaInicio) / 7 + 1; // Determinar la fila (1-6)

            // Colocar el día en la cuadrícula
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
        actualizarMes();  // actualizar el mes y muestralo
    }

    @FXML
    private void anteriorMes() {
        mesActual = mesActual.minusMonths(1);
        actualizarMes();
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
