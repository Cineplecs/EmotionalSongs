package emotionalsongs;

import Util.Indirizzo;
import Util.Utente;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class ClientControllerRegistrazione {

    @FXML
    TextField nome, cognome, mail, userId, codFisc;
    @FXML
    PasswordField password;
    @FXML
    TextField nomeVia, numeroCiv, cap, comune, provincia;
    @FXML
    Spinner identificativo;

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

    private void riempiSpinner(){

    }

    @FXML
    private void initialize(){

    }
}
