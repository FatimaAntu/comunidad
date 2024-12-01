package comunidad.comunidadVecinos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.paint.Color;
import modelo_DAO.ListarActividadesCalendario;
import modelo_DTO.Actividades;
import modelo_DTO.Usuario_global;

public class ControladorP_Calendario {

    // DECLARACIONES TABLA MIS ACTIVIDADES
    @FXML
    private TableView<Actividades> tablamisActividades;
    @FXML
    private TableColumn<Actividades, String> MisActividades;
    @FXML
    private TableColumn<Actividades, String> columnaHoraInicio;
    @FXML
    private TableColumn<Actividades, String> columnaHoraFin;
    @FXML
    private TableColumn<Actividades, Integer> columnaPlazas;
    @FXML
    private TableColumn<Actividades, Integer> columnaPlazasDisponibles;

    private ObservableList<Actividades> listaActividades;

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
    private List<String> cumpleaños; // Para almacenar las fechas de los cumpleaños

    @FXML
    public void initialize() {
        cumpleaños = new ArrayList<>();
        misactividades.setVisible(false);
        configurarRestricciones();
        actualizarMes();
        labelNombre.setText(Usuario_global.getInstance().getNombreusuarioglobal());
        cargarCumpleaños(); // Llamada para cargar los cumpleaños
    }

    private void actualizarMes() {
        // Actualiza el mes y año actual
        labelMes.setText(mesActual.getMonth().name() + " " + mesActual.getYear());

        // Muestra el calendario del mes actual
        mostrarCalendario(mesActual);
    }

    public void mostrarCalendario(YearMonth mes) {
        Locale.setDefault(new Locale("es", "ES"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy", new Locale("es", "ES"));

        // Actualiza el nombre del mes en el Label
        labelMes.setText(mes.format(formatter));
        gridCalendario.getChildren().clear(); // Limpiar el calendario antes de volver a mostrarlo

        // Días de la semana
        String[] diasSemana = {"Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom"};
        for (int i = 0; i < diasSemana.length; i++) {
            Label labelDia = new Label(diasSemana[i]);
            labelDia.setStyle("-fx-font-weight: bold; -fx-alignment: center; -fx-pref-width: 100;");
            gridCalendario.add(labelDia, i, 0); // Primera fila para los días de la semana
        }

        // Días del mes
        LocalDate primerDiaMes = mes.atDay(1);
        int diaInicio = primerDiaMes.getDayOfWeek().getValue(); // 1=lunes, 7=domingo

        // Ajuste: si el primer día es domingo (7), lo pondremos en la última columna (6)
        int columnaInicio = (diaInicio == 7) ? 6 : diaInicio - 1;

        int diasEnMes = mes.lengthOfMonth();

        for (int i = 0; i < diasEnMes; i++) {
            LocalDate diaactual = mes.atDay(i + 1);
            ListarActividadesCalendario listaractividades = new ListarActividadesCalendario();
            ArrayList<Actividades> act = listaractividades.ActividadesReservadasPorDia(Usuario_global.getInstance().getVivienda(), diaactual);
            StringBuilder actividadesDia = new StringBuilder();

            // Procesar las actividades de ese día
            for (Actividades a : act) {
                if (actividadesDia.length() > 1) {
                    actividadesDia.append("\n");
                }
                actividadesDia.append(a.getNombreActividad());
            }

            String textoDia = actividadesDia.toString();

            StackPane diaPane = new StackPane();
            if (!textoDia.isEmpty() && diaactual.isAfter(LocalDate.now())) {
                Label labelDia = new Label((i + 1) + "\n" + textoDia);
                Button botonDetalles = new Button("Ver más");
                botonDetalles.setStyle("-fx-background-color: #D26192; -fx-background-radius: 100; -fx-text-fill: white;");
                botonDetalles.setOnMouseClicked(event -> mostrarMiActividad(act));

                StackPane.setAlignment(labelDia, Pos.TOP_CENTER);
                StackPane.setAlignment(botonDetalles, Pos.BOTTOM_CENTER);
                diaPane.getChildren().addAll(labelDia, botonDetalles);
            } else {
                Label labelDia = new Label((i + 1) + "\n");
                diaPane.getChildren().addAll(labelDia);
            }

            // Verifica si es un cumpleaños
            for (String cumple : cumpleaños) {
                LocalDate fechaCumple = LocalDate.parse(cumple);
                int diaCumple = fechaCumple.getDayOfMonth();

                if (diaCumple == i + 1) {
                    Label cumpleLabel = new Label("\n\nCumpleaños");
                    cumpleLabel.setTextFill(Color.RED);
                    diaPane.getChildren().add(cumpleLabel);
                    break;
                }
            }

            // Estilo del día
            if (diaactual.isBefore(LocalDate.now())) {
                diaPane.setStyle("-fx-border-color: #CCCCCC; -fx-background-color: #d5d5d5;");
            } else if (diaactual.isEqual(LocalDate.now())) {
                diaPane.setStyle("-fx-border-color: #3859ff; -fx-background-color: #fff1b6;");
            } else {
                diaPane.setStyle("-fx-border-color: #CCCCCC;");
            }

            int col = (i + columnaInicio) % 7;
            int fila = (i + columnaInicio) / 7 + 1;
            gridCalendario.add(diaPane, col, fila);
        }
    }

    private void configurarRestricciones() {
        for (int i = 0; i < 7; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(100.0 / 7);
            gridCalendario.getColumnConstraints().add(columnConstraints);
        }

        int numFilas = 6;
        for (int i = 0; i < numFilas; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(javafx.scene.layout.Priority.ALWAYS);
            gridCalendario.getRowConstraints().add(rowConstraints);
        }
    }

    private void cargarCumpleaños() {
        cumpleaños = new ArrayList<>();
        try (Connection connection = Utilidades.Util.dameConexion()) {
            String query = "SELECT fecha FROM reservas WHERE MONTH(fecha) = ? AND YEAR(fecha) = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setInt(1, mesActual.getMonthValue());
                pstmt.setInt(2, mesActual.getYear());
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        String fecha = rs.getString("fecha");
                        cumpleaños.add(fecha);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void mostrarMiActividad(ArrayList<Actividades> act) {
        misactividades.setVisible(true);
        listaActividades = FXCollections.observableArrayList();
        tablamisActividades.setItems(listaActividades);

        MisActividades.setCellValueFactory(new PropertyValueFactory<>("NombreActividad"));
        columnaHoraInicio.setCellValueFactory(new PropertyValueFactory<>("Hora"));
        columnaHoraFin.setCellValueFactory(new PropertyValueFactory<>("HoraFin"));
        columnaPlazas.setCellValueFactory(new PropertyValueFactory<>("PlazasReservadas"));
        columnaPlazasDisponibles.setCellValueFactory(new PropertyValueFactory<>("Plazas"));

        listaActividades.addAll(act);
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
        cargarCumpleaños();
        actualizarMes();
    }

    @FXML
    private void anteriorMes() {
        mesActual = mesActual.minusMonths(1);
        cargarCumpleaños();
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
            } else {
                tablamisActividades.getSelectionModel().clearSelection();
                
            }
            
        }
        
    }
    
}

    

   
