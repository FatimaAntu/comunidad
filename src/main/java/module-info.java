module comunidad.comunidadVecinos {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires javafx.graphics;
	requires java.desktop;

    opens comunidad.comunidadVecinos to javafx.fxml;
    exports comunidad.comunidadVecinos;
}