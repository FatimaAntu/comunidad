package comunidad.comunidadVecinos;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {
//comentario para probar un push
	//ssssssss
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
