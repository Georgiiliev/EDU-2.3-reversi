package connection;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class ServerConnection {
    private static final int port = 7789;
    private static String host;

    private Socket socket;
    private Scanner in;
    private PrintWriter out;

    public ServerConnection(String host) {
        this.host = host;
        connect();
    }

    private void connect() {
        try {
            socket = new Socket(host, port);
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);

            receive(); // haal welkom regel 1 op
            receive(); // haal welkom regel 2 op
        }
        catch (IOException e){
            System.out.println("Kan geen verbinding maken met de server! \n - Controleer de host naam.");
            return;
        }
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
}