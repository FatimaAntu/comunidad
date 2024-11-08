package comunidad.comunidadVecinos;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import modelo_DTO.Actividades;

public class ControladorP_Invierno {
	@FXML
    private TableView<Actividades> miTabla;

    @FXML
    private TableColumn<Actividades, Void> botones36;

    @FXML
    public void initialize() {
        // Configura la columna de botones
        botones36.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Actividades, Void> call(final TableColumn<Actividades, Void> param) {
                return new TableCell<>() {
                    private final Button boton = new Button("Acción");

                    {
                        boton.setOnAction(event -> {
                        	Actividades actividad = getTableView().getItems().get(getIndex());
                            // Acción cuando se presiona el botón
                            System.out.println("Botón presionado para: " + actividad);
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(boton);
                        }
                    }
                };
            }
        });
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
