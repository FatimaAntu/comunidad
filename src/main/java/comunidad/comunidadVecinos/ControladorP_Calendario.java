package comunidad.comunidadVecinos;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import modelo_DAO.Alertas;
import modelo_DAO.ListarActividadesCalendario;
import modelo_DTO.Actividades;
import modelo_DTO.Usuario_global;

public class ControladorP_Calendario {
	
	//DECLARACIONES TABLA MIS ACTIVIDADES
	@FXML
    private TableView<Actividades> tablamisActividades;
    @FXML
    private TableColumn<Actividades, Void> MisActividades, columnaHoraInicio, columnaHoraFin, columnaPlazas, columnaPlazasDisponibles;
   
    private ObservableList<Actividades> listaActividades;
    //FIN DECLARACIONES TABLA
	
	
	@FXML
    private GridPane gridCalendario;
    @FXML
    private Label labelMes;
    @FXML
    private Label labelNombre;
    
    @FXML
    private AnchorPane misactividades;
    
    private YearMonth mesActual;
    
    public ControladorP_Calendario() {
        mesActual = YearMonth.now();
    }
	
    @FXML
    public void initialize() {
    	misactividades.setVisible(false);
    	
        configurarRestricciones();
        
        actualizarMes();
        labelNombre.setText(Usuario_global.getInstance().getNombreusuarioglobal());
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
            LocalDate diaactual = mes.atDay(i + 1);
        	ListarActividadesCalendario listaractividades = new ListarActividadesCalendario();
            // Cada día en un StackPane
        	ArrayList<Actividades> act = listaractividades.ActividadesReservadasPorDia(Usuario_global.getInstance().getVivienda(), diaactual);
        	//String NAct = "";
        	StringBuilder actividadesDia = new StringBuilder();
        	for (Actividades a : act) {
        		if (actividadesDia.length() > 1) {
        	        actividadesDia.append("\n"); // Añade un salto de línea entre actividades
        	    }
        	    actividadesDia.append(a.getNombreActividad());
        	}
        	String textoDia = actividadesDia.toString();

        	StackPane diaPane = null;
            //StackPane diaPane = new StackPane(new Label(String.valueOf(i + 1)+"\n"));
            //String textoDia = String.valueOf(i + 1) + "\n" + actividadesDia;
            if (textoDia != "") {
            Label labelDia = new Label((i+1)+"\n"+textoDia);
            Button botonDetalles = new Button("Ver más");
            botonDetalles.setStyle("-fx-background-color: #D26192; -fx-background-radious: 100;-fx-border-radius: 100; -fx-text-fill: white; -fx-alignment: center;");
            //botonDetalles.setOnAction(mostrarTabla());
            botonDetalles.setOnMouseClicked(event -> mostrarMiActividad(act));
            // Ajustar posiciones
            StackPane.setAlignment(labelDia, Pos.TOP_CENTER);
            StackPane.setAlignment(botonDetalles, Pos.BOTTOM_CENTER);

            // Crear el StackPane del día
            diaPane = new StackPane();
            	if (diaactual.isEqual(LocalDate.now()) || diaactual.isAfter(LocalDate.now())) {
            		diaPane.getChildren().addAll(labelDia, botonDetalles);
            	} else {
            		diaPane.getChildren().addAll(labelDia);
            	}
            } else {
            	  textoDia = String.valueOf(i + 1) + "\n";
            	  Label labelDia = new Label(textoDia);
            	  diaPane = new StackPane();
                  diaPane.getChildren().addAll(labelDia);
            }
            // Añadir estilos al StackPane
            if (diaactual.isBefore(LocalDate.now())) {
                diaPane.setStyle("-fx-border-color: #CCCCCC; -fx-background-color: #d5d5d5; -fx-alignment: center;");
            } else if (diaactual.isEqual(LocalDate.now())) {
                diaPane.setStyle("-fx-border-color: #3859ff; -fx-background-color: #fff1b6; -fx-alignment: center;");
            } else {
                diaPane.setStyle("-fx-border-color: #CCCCCC; -fx-alignment: center;");
            }

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

    private void mostrarMiActividad(ArrayList<Actividades> act) {
    	misactividades.setVisible(true);
    	
    	listaActividades = FXCollections.observableArrayList();
        tablamisActividades.setItems(listaActividades);
    	for (Actividades a : act) {
    	MisActividades.setCellValueFactory(new PropertyValueFactory<>("NombreActividad"));
        columnaHoraInicio.setCellValueFactory(new PropertyValueFactory<>("Hora"));
        columnaHoraFin.setCellValueFactory(new PropertyValueFactory<>("HoraFin"));
        columnaPlazas.setCellValueFactory(new PropertyValueFactory<>("PlazasReservadas"));
        columnaPlazasDisponibles.setCellValueFactory(new PropertyValueFactory<>("Plazas"));
        
        
        listaActividades.addAll((a));
    	}
    }
    @FXML
    private void cerrarMiActividad() {
    	misactividades.setVisible(false);
    	siguienteMes();
    	anteriorMes();
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
	
	@FXML
    private void eliminarActividad() {
        Actividades miactividad = tablamisActividades.getSelectionModel().getSelectedItem();

        if (miactividad != null) {
            // Confirmar si el usuario desea eliminar la actividad seleccionada
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setHeaderText(null);
            confirmacion.setTitle("Confirmación");
            confirmacion.setContentText("¿Estás seguro de que deseas eliminar tu reserva en la actividad?");
            Optional<ButtonType> resultado = confirmacion.showAndWait();

            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                // Proceder con la eliminación
                tablamisActividades.getSelectionModel().clearSelection();

                ListarActividadesCalendario listar = new ListarActividadesCalendario();
                listar.eliminarActividades(miactividad);
                misactividades.setVisible(false);
                anteriorMes();
                siguienteMes();
            }else {
            	  tablamisActividades.getSelectionModel().clearSelection();
            }
        } else {
        	Alertas a = new Alertas();
            a.alertaWarning("Por favor, selecciona una actividad para eliminar");
        }
    }
}
