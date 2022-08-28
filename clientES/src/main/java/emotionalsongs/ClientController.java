package emotionalsongs;

import Util.Indirizzo;
import Util.Login;
import Util.Utente;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientController {

    @FXML
    TextField nome, cognome, mail, userId, codFisc, userLogin;
    @FXML
    PasswordField password, passwordLogin;
    @FXML
    TextField nomeVia, numeroCiv, cap, comune, provincia;
    @FXML
    ChoiceBox identificativo;

    Utente utente;
    Indirizzo indirizzo;

    @FXML
    private void Registrazione(){
        indirizzo = new Indirizzo(identificativo.getValue().toString(), nomeVia.getText(),
                                comune.getText(), provincia.getText(),
                                Integer.parseInt(numeroCiv.getText()),
                                Integer.parseInt(cap.getText()));
        utente  = new Utente(nome.getText(), cognome.getText(),
                            codFisc.getText(), mail.getText(),
                            userId.getText(), password.getText(),
                            indirizzo);
        ClientMain.Registrazione(utente);
    }

    @FXML
    private void Login(){
        Login login = new Login(userLogin.getText(), passwordLogin.getText());
        ClientMain.Login(login);
    }

    private void riempiSpinner(){
        identificativo.getItems().add("Via");
        identificativo.getItems().add("Viale");
        identificativo.getItems().add("Piazza");
        //TODO
    }

    @FXML
    private void initialize(){

    }

    @FXML
    private void MainMenu() throws IOException {
        App.setRoot("mainmenu");
    }
}
