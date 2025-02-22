package connection;

import model.StateHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerConnection implements Runnable{
    private ServerConnection serverConnection;
    private StateHandler stateHandler;

    private int port = 7789;
    private String host = "localhost";
    // gameserver.edu.janyksteenbeek.nl

    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    private boolean connection;

    public ServerConnection(StateHandler stateHandler, String host, int port) {
        connection = false;
        serverConnection = this;
        this.stateHandler = stateHandler;
        this.host = host;
        this.port = port;
        connect();
    }

    private void connect() {
        try {
            socket = new Socket(host, port);
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);

            receive(); // haal welkom regel 1 op
            receive(); // haal welkom regel 2 op

            connection = true;
            System.out.println("Connection established.");

        }
        catch (IOException e){
            System.out.println("Kan geen verbinding maken met de server! \n - Controleer de host naam.");
        }
    }

    public boolean connectionSucceed(){
        return connection;
    }

    public void send(String action, String value){
        out.println(action + " " + value);
    }

    public String receive(){
        return in.nextLine();
    }

    public boolean hasNext(){
        return in.hasNextLine();
    }

    public void close() {
        send("logout","");
        try {
            socket.close();
        }
        catch (IOException e){
            System.out.println("Er is iets mis gegaam met afsluiten van het spel.");
        }
    }
    public void fetchData(){
        send("get", "playerlist");
    }

    public ServerConnection getServerConnection(){
        return serverConnection;
    }

    @Override
    public void run() {
        fetchData();
    }
}
