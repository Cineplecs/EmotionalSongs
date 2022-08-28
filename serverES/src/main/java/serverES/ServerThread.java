package serverES;

import Util.Canzone;
import Util.Login;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerThread extends Thread {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ServerThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
           in = new ObjectInputStream(socket.getInputStream());
           out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while(true){
            try {
                HashMap input = (HashMap) in.readObject();
                checkObject(input);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void checkObject(HashMap map) {
        String ruolo = (String) map.get("Ruolo");
        switch (ruolo){
            case "registrazione":
                System.out.println("Registrazione richiesta");
                System.out.println(map.get("Utente"));
                break;
            case "login":
                System.out.println("Login richiesto");
                Boolean result = ServerMain.Login((Login) map.get("Login"));
                if(result){
                    try {
                        map.put("Risultato", result);
                        map.put("Utente", ServerMain.recuperaInfo((Login) map.get("Login")));
                        out.writeObject(map);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    try {
                        map.put("Risultato", result);
                        out.writeObject(map);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;
            case "test":
                System.out.println("Il test è andato a buon fine");
                try {
                    out.writeObject("Test andato a buon fine");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
        }
    }
}
