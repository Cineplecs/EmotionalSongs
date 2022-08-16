module emotionalsongs {
    requires javafx.controls;
    requires javafx.fxml;
    requires SharedLibrary;

    opens emotionalsongs to javafx.fxml;
    exports emotionalsongs;
}
