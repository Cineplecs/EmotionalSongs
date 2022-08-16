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

    public static void Login(Login login){
        ResultSet rs;
        String query = String.format("select exists (select 1 from utentiregistrati where userid = '%s' and password = '%s')",
                login.getUserId(), login.getPassword());
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            while(rs.next()){
                if(rs.getBoolean(1)){
                    System.out.println("Login riuscito");
                } else {
                    System.out.println("Login non riuscito");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        ArrayList<Canzone> canzoni = null;



        return  canzoni;
    }

    public synchronized static void RegistraPlaylist(){

    }

}
