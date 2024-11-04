package comunidad.comunidadVecinos;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ControladorP_InicioSesion {

    @FXML
    private TextField txtUsuario;
    
    @FXML
    private PasswordField txtPassword;

    //metodo que se ejecuta al presionar el boton de login
    @FXML
    private void iniciarSesion() {
        String usuario = txtUsuario.getText();
        String password = txtPassword.getText();

        if (validarCredenciales(usuario, password)) {
            
            // Cerrar la ventana de login y abrir la ventana principal
        } else {
            // Muestra un mensaje de error
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error de inicio de sesión");
            alerta.setHeaderText("Credenciales incorrectas");
            alerta.setContentText("Por favor, verifica tu usuario y contraseña.");
            alerta.showAndWait();
        }
    }

    //metodo que simula la validacion de credenciales
    private boolean validarCredenciales(String usuario, String password) {
        //aqui la lógica para validar con la base de datos 
        return "admin".equals(usuario) && "1234".equals(password);//ejemplo de usuario y contraseña
    }
}
