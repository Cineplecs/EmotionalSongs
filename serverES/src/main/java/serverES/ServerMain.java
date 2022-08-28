package serverES;

import Util.Canzone;
import Util.Indirizzo;
import Util.Login;
import Util.Utente;
import javafx.scene.Camera;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ServerMain implements Runnable{

    private ServerSocket serverSocket;
    private String databaseUrl = "jdbc:postgresql://localhost:5432/EmotionalSongs";
    private String dbUser = "postgres";
    private String dbPassword = "postgres";
    private static Connection conn;
    ServerMain(){
        try {
            serverSocket = new ServerSocket(14000);
            conn = DriverManager.getConnection(databaseUrl, dbUser, dbPassword);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized static void Registrazione(Utente utente){
        Indirizzo indirizzo = utente.getInd();
        String query = String.format("insert into utentiregistrati " +
                "values ('%s', '%s', '%s', '%s', '%s', " +
                "'%s', '%s', '%s', %d, %d, '%s', '%s')",
                utente.getNome(), utente.getCognome(), utente.getCodFisc(), utente.getMail(),
                utente.getUserId(), utente.getPassword(), indirizzo.getIdentificatore(), indirizzo.getNomeVia(),
                indirizzo.getNumeroCiv(), indirizzo.getCap(), indirizzo.getComune(), indirizzo.getProvincia());
        try{
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Boolean Login(Login login){
        Boolean result = false;
        ResultSet rs;
        String query = String.format("select exists (select 1 from utentiregistrati where userid = '%s' and password = '%s')",
                login.getUserId(), login.getPassword());
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            while(rs.next()){
                if(rs.getBoolean(1)){
                    result = true;
                    System.out.println("Login riuscito");
                } else {
                    result = false;
                    System.out.println("Login non riuscito");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static ArrayList<Canzone> CercaBranoMusicale(String titolo) throws SQLException {
        ArrayList<Canzone> canzoni = new ArrayList<>();
        ResultSet rs;
        String query = String.format("select * from canzoni where titolo='%s'", titolo);
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while(rs.next()){
            Canzone canzone = new Canzone(rs.getString(1), rs.getString(2),
                    rs.getInt(3), rs.getString(4), rs.getString(5),
                    rs.getString(6));
            canzoni.add(canzone);
        }
        return canzoni;
    }

    public static ArrayList<Canzone> CercaBranoMusicale(String autore, int anno){
        ArrayList<Canzone> canzoni = new ArrayList<>();
        ResultSet rs;
        String query = String.format("select * from canzoni where autore='%s' and anno='%d'", autore, anno);
        try{
            PreparedStatement stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            while(rs.next()){
                Canzone canzone = new Canzone(rs.getString(1), rs.getString(2),
                        rs.getInt(3), rs.getString(4), rs.getString(5),
                        rs.getString(6));
                canzoni.add(canzone);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  canzoni;
    }

    public synchronized static void RegistraPlaylist(HashMap<String, Object> map){
        ArrayList<Canzone> listaCanzoni = (ArrayList<Canzone>) map.get("ListaCanzoni");
        int dimensioneArray = listaCanzoni.size()*2;
        int counter = 0;
        String[] arrayCanzoni = new String[dimensioneArray];
        for(int i = 0; i < listaCanzoni.size(); i++){
            arrayCanzoni[counter++] = listaCanzoni.get(i).getTitolo();
            arrayCanzoni[counter++] = listaCanzoni.get(i).getAutore();
        }

        try {
            Array array = conn.createArrayOf("VARCHAR", arrayCanzoni);
            String query = String.format("insert into playlist values");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Utente recuperaInfo(Login login){
        ResultSet user;
        Utente utente = null;
        String queryUser = String.format("select * from utentiregistrati where userid='%s' and password='%s",
                login.getUserId(), login.getPassword());
        try {
            PreparedStatement stmt2 = conn.prepareStatement(queryUser);
            user = stmt2.executeQuery();
            while (user.next()) {
                //TODO completare dati da recuperare per utente
                utente = new Utente();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return utente;
    }
}
