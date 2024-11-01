module comunidad.comunidadVecinos {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires javafx.graphics;

    opens comunidad.comunidadVecinos to javafx.fxml;
    exports comunidad.comunidadVecinos;
}