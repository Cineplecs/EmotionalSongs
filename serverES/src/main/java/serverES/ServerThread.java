package serverES;

import Util.Canzone;

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
           System.out.println("in creato");
           out = new ObjectOutputStream(socket.getOutputStream());
           System.out.println("out creato");
           ArrayList<Canzone> prova = ServerMain.CercaBranoMusicale("ABC");
           for(int i = 0; i < prova.size(); i++){
               System.out.println(prova.get(i).getTitolo() + " - " + prova.get(i).getAutore());
           }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
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

    private void checkObject(HashMap hashMap) {
        String ruolo = (String) hashMap.get("Ruolo");
        switch (ruolo){
            case "registrazione":
                System.out.println("Registrazione richiesta");
                System.out.println(hashMap.get("Utente"));
                break;
            case "login":
                System.out.println("Login richiesto");
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
