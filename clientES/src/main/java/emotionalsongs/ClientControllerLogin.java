package emotionalsongs;

import java.io.IOException;

import Util.Login;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ClientControllerLogin {
    @FXML
    TextField username;
    @FXML
    PasswordField password;

    @FXML
    private void Login(){
        Login login = new Login(username.getText(), password.getText());
        ClientMain.Login(login);
    }
}