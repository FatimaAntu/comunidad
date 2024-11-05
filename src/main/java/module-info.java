module comunidad.comunidadVecinos {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires javafx.graphics;
	requires java.desktop;

    opens comunidad.comunidadVecinos to javafx.fxml;
    exports comunidad.comunidadVecinos;
    
    opens modelo_DAO to javafx.fxml;
    exports modelo_DAO;
    
 // Abre el paquete modelo_DTO a javafx.base para que PropertyValueFactory pueda acceder a sus propiedades
    opens modelo_DTO to javafx.base;
    
    exports modelo_DTO; // Exporta el paquete para que otros módulos puedan usarlo
}