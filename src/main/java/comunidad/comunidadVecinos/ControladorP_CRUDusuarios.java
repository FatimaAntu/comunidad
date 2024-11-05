package comunidad.comunidadVecinos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo_DAO.ListarUsuarios;
import modelo_DTO.Usuarios;

public class ControladorP_CRUDusuarios {

	@FXML
	private TableView<Usuarios> tablaUsuarios;  
	@FXML
	private TableColumn<Usuarios, String> colNombre;
	@FXML
	private TableColumn<Usuarios, String> colApellidos;
	@FXML
	private TableColumn<Usuarios, String> colVivienda;
	
	private ObservableList<Usuarios> listaUsuarios;
	@FXML
	
	
	private void leerUsuarios() {
		ListarUsuarios ls = new ListarUsuarios();
		List<Usuarios> usuariosBBDD = ls.leerUsuarios();
	    
	    // Agrega los usuarios a la lista observable, que automáticamente actualiza la tabla.
	    listaUsuarios.addAll(usuariosBBDD);
	    }
	
	public void initialize() {
		listaUsuarios = FXCollections.observableArrayList();
		tablaUsuarios.setItems(listaUsuarios);
		
		colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
		colVivienda.setCellValueFactory(new PropertyValueFactory<>("vivienda"));
		
	    leerUsuarios();
	}
    @FXML
    private void cargarAgregarUsuario() throws IOException {
        App.setRoot("P_AgregarUsuario");
    }
    
    @FXML
    private void cargarPinicio() throws IOException {
        App.setRoot("P_Inicio");
    }
}