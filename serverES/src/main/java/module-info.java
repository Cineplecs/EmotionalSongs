module es {
    requires javafx.controls;
    requires javafx.fxml;
    requires SharedLibrary;
    requires java.sql;

    opens serverES to javafx.fxml;
    exports serverES;
}
