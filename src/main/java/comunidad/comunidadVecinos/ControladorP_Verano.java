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
import modelo_DAO.Alertas;
import modelo_DAO.ListarActividades;
import modelo_DTO.Actividades;
import modelo_DTO.Usuario_global;

public class ControladorP_Verano {
	@FXML
	private TableView<Actividades> tablaActividades36;
	@FXML
	private TableView<Actividades> tablaActividades710;
	@FXML
	private TableColumn<Actividades, Void> actividades36, actividades710, columnaHoraInicio36, columnaHoraFin36,
			columnaFecha36, columnaHoraInicio710, columnaHoraFin710, columnaFecha710, columnaPlazas36, columnaPlazas710;

	@FXML
	private Label labelNombre;

	private ObservableList<Actividades> listaActividades36, listaActividades710;

	@FXML
	private Label labelContador;

	private int contador = 0;

	public void initialize() {
		// Tabla 3-6 años
		actividades36.setCellValueFactory(new PropertyValueFactory<>("NombreActividad"));
		columnaHoraInicio36.setCellValueFactory(new PropertyValueFactory<>("Hora"));
		columnaHoraFin36.setCellValueFactory(new PropertyValueFactory<>("HoraFin"));
		columnaFecha36.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
		columnaPlazas36.setCellValueFactory(new PropertyValueFactory<>("Plazas"));

		listaActividades36 = FXCollections.observableArrayList();
		tablaActividades36.setItems(listaActividades36);

		listaActividades36.addAll(leerActividades(36710));
		listaActividades36.addAll(leerActividades(36));

		// Tabla 7-10 años
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

	public void ReservarPlaza() {
		Alertas a = new Alertas();
		Actividades actividadSeleccionada36 = tablaActividades36.getSelectionModel().getSelectedItem();
		if (actividadSeleccionada36 == null) {
			Actividades actividadSeleccionada710 = tablaActividades710.getSelectionModel().getSelectedItem();
			modelo_DAO.ReservarPlaza rp = new modelo_DAO.ReservarPlaza();
			if ((rp.comprobarReservas(actividadSeleccionada710.getIdHorario(),
					Usuario_global.getInstance().getVivienda())) == 0) {
				int idHorario710 = actividadSeleccionada710.getIdHorario();
				int plazas710 = contador;
				String vivienda710 = Usuario_global.getInstance().getVivienda();

				modelo_DAO.ReservarPlaza rp710 = new modelo_DAO.ReservarPlaza();
				if (contador <= 0 || contador > Usuario_global.getInstance().getNumHijos()) {
					a.alertaError("No puedes reservar 0 plazas");
				} else {
					if (actividadSeleccionada710.getPlazas() == 0) {
						a.alertaError("No queda ninguna plaza disponibles para la actividad "
								+ actividadSeleccionada710.getNombreActividad() + " el día "
								+ actividadSeleccionada710.getFecha());
					} else if (actividadSeleccionada710.getPlazas() < contador) {
						a.alertaError("No quedan suficientes plazas disponibles para la actividad "
								+ actividadSeleccionada710.getNombreActividad() + " el día "
								+ actividadSeleccionada710.getFecha());
					} else {
						rp710.ReservarPlazas(idHorario710, plazas710, vivienda710);
						if (plazas710 == 1) {
							a.alertaConfirmation("Has reservado " + plazas710 + " plaza correctamente!");
						} else {
							a.alertaConfirmation("Has reservado " + plazas710 + " plazas correctamente!");
						}
						try {
							App.setRoot("P_Invierno");
							App.setRoot("P_Verano");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			} else {
				Alertas al = new Alertas();
				al.alertaError("Ya tienes plazas reservadas para esta actividad");
			}
		} else {
			modelo_DAO.ReservarPlaza rp = new modelo_DAO.ReservarPlaza();
			if ((rp.comprobarReservas(actividadSeleccionada36.getIdHorario(),
					Usuario_global.getInstance().getVivienda())) == 0) {
				int idHorario36 = actividadSeleccionada36.getIdHorario();
				int plazas36 = contador;
				String vivienda36 = Usuario_global.getInstance().getVivienda();

				modelo_DAO.ReservarPlaza rp36 = new modelo_DAO.ReservarPlaza();
				if (contador <= 0 || contador > Usuario_global.getInstance().getNumHijos()) {
					a.alertaError("No puedes reservar 0 plazas");
				} else {
					if (actividadSeleccionada36.getPlazas() == 0) {
						a.alertaError("No queda ninguna plaza disponibles para la actividad "
								+ actividadSeleccionada36.getNombreActividad() + " el día "
								+ actividadSeleccionada36.getFecha());
					} else if (actividadSeleccionada36.getPlazas() < contador) {
						a.alertaError("No quedan suficientes plazas disponibles para la actividad "
								+ actividadSeleccionada36.getNombreActividad() + " el día "
								+ actividadSeleccionada36.getFecha());
					} else {
						rp36.ReservarPlazas(idHorario36, plazas36, vivienda36);
						if (plazas36 == 1) {
							a.alertaConfirmation("Has reservado " + plazas36 + " plaza correctamente!");
						} else {
							a.alertaConfirmation("Has reservado " + plazas36 + " plazas correctamente!");
						}
						try {
							App.setRoot("P_Invierno");
							App.setRoot("P_Verano");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			} else {
				Alertas al = new Alertas();
				al.alertaError("Ya tienes plazas reservadas para esta actividad");
			}
		}
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
