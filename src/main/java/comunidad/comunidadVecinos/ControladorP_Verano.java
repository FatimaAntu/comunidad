package comunidad.comunidadVecinos;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo_DAO.ListarActividades;
import modelo_DTO.Actividades;
import modelo_DTO.Usuario_global;

public class ControladorP_Verano {
    @FXML
    private TableView<Actividades> tablaActividades36;
    @FXML
    private TableView<Actividades> tablaActividades710;
    @FXML
    private TableColumn<Actividades, Void> actividades36, actividades710, columnaHoraInicio36, columnaHoraFin36, columnaFecha36, columnaHoraInicio710, columnaHoraFin710, columnaFecha710, columnaPlazas36, columnaPlazas710;
    private ObservableList<Actividades> listaActividades36, listaActividades710;
    
    @FXML
    private Label labelNombre;
    @FXML
    private Label labelContador;

    private int contador = 0;

    @FXML
    public void initialize() {        
        //Tabla 3-6 años
    	actividades36.setCellValueFactory(new PropertyValueFactory<>("NombreActividad"));
        columnaHoraInicio36.setCellValueFactory(new PropertyValueFactory<>("Hora"));
        columnaHoraFin36.setCellValueFactory(new PropertyValueFactory<>("HoraFin"));
        columnaFecha36.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
        columnaPlazas36.setCellValueFactory(new PropertyValueFactory<>("Plazas"));
        
        listaActividades36 = FXCollections.observableArrayList();
        tablaActividades36.setItems(listaActividades36);

        listaActividades36.addAll(leerActividades(36710));
        listaActividades36.addAll(leerActividades(36));
        
        //Tabla 7-10 años
        actividades710.setCellValueFactory(new PropertyValueFactory<>("NombreActividad"));
        columnaHoraInicio710.setCellValueFactory(new PropertyValueFactory<>("Hora"));
        columnaHoraFin710.setCellValueFactory(new PropertyValueFactory<>("HoraFin"));
        columnaFecha710.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
        columnaPlazas710.setCellValueFactory(new PropertyValueFactory<>("Plazas"));
        
        listaActividades710 = FXCollections.observableArrayList();
        tablaActividades710.setItems(listaActividades710);
        
        listaActividades710.addAll(leerActividades(36710));
        listaActividades710.addAll(leerActividades(710));
        
        
        labelNombre.setText(Usuario_global.getInstance().getNombreusuarioglobal());
    }
    
    public void incrementar() {
        if (contador < Usuario_global.getInstance().getNumHijos()) {
        	contador++;
        }
        	labelContador.setText(String.valueOf(contador));
        }

    public void decrementar() {
        if (contador > 0 && contador <= Usuario_global.getInstance().getNumHijos()) {
            contador--;
            labelContador.setText(String.valueOf(contador));
        }
    }
    
    private List<Actividades> leerActividades(int edad) {
        ListarActividades la = new ListarActividades();
        return la.leerActividades(1, edad);
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
