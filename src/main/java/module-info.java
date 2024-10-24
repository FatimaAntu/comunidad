module comunidad.comunidadVecinos {
    requires javafx.controls;
    requires javafx.fxml;

    opens comunidad.comunidadVecinos to javafx.fxml;
    exports comunidad.comunidadVecinos;
}