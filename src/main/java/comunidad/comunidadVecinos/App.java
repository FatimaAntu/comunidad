package comunidad.comunidadVecinos;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo_DAO.InicioSesion;
import modelo_DTO.Usuarios;

public class App extends Application {
	public static Usuarios usuarioParaEditar; // Usuario temporal para edición
	private static Scene scene;

	
	
	@Override
	public void start(Stage stage) throws IOException {
		scene = new Scene(loadFXML("P_Inicio"));
		stage.setScene(scene);

		stage.sizeToScene();
		stage.setResizable(false);
		stage.show();
	}

	public static void setRoot(String fxml) throws IOException {
		Parent root = loadFXML(fxml);

		scene.setRoot(root);

		Stage stage = (Stage) scene.getWindow();
		stage.sizeToScene();
	}
	FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("comunidad/comunidadVecinos/P_ListaSugerencias.fxml"));
	

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/comunidad/comunidadVecinos/" + fxml + ".fxml"));
		
		return fxmlLoader.load();
	}
	

	public static void main(String[] args) {
		launch();
	}
	

	
}