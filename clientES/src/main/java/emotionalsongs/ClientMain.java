package emotionalsongs;

import Util.Canzone;
import Util.Login;
import Util.Utente;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ClientMain extends Thread{
    private Socket socket;
    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    private static HashMap<String, Object> hashMap;
    private static Utente utenteLogin;

    public static void Registrazione(Utente utente) {
        hashMap = new HashMap<>();
        hashMap.put("Utente", utente);
        hashMap.put("Ruolo", "registrazione");
        try{
            out.writeObject(hashMap);
            utenteLogin = utente;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void Login(Login login){
        hashMap = new HashMap<>();
        hashMap.put("Login", login);
        hashMap.put("Ruolo", "login");
        try {
            out.writeObject(hashMap);
            while(true){
                HashMap<String, Object> ricevuto = (HashMap<String, Object>) in.readObject();
                Boolean risultato = (Boolean) ricevuto.get("Risultato");
                if(risultato){
                    utenteLogin = (Utente) ricevuto.get("Utente");
                } else {
                    App.AlertLogin();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void CercaBranoMusicale(String titolo){
        hashMap = new HashMap<>();
        hashMap.put("Titolo", titolo);
        hashMap.put("Ruolo", "ricercaTitolo");
        try{
            out.writeObject(hashMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void CercaBranoMusicale(String autore, int anno){
        hashMap = new HashMap<>();
        hashMap.put("Autore", autore);
        hashMap.put("Anno", anno);
        hashMap.put("Ruolo", "ricercaAutoreAnno");
        try{
            out.writeObject(hashMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void RegistraPlaylist(String nomePlaylist, ArrayList<Canzone> lista){
        hashMap = new HashMap<>();
        hashMap.put("NomePlaylist", nomePlaylist);
        hashMap.put("ListaCanzoni", lista);
        hashMap.put("AutorePlaylist", utenteLogin.getUserId());
        hashMap.put("Ruolo", "registraPlaylist");
        try{
            out.writeObject(hashMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void inserisciEmozioniBrano(){

    }

    @Override
    public void run() {

        try {
            socket = new Socket("LBHost.ns0.it", 14000);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}