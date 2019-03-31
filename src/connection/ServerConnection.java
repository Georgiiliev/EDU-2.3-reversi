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

    public void connect() {
        try {
            socket = new Socket(host, port);
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println(receive());
            System.out.println(receive());
        }
        catch (IOException e){
            System.out.println("Kan geen verbinding maken met de server! \nControleer de host naam.");
        }
    }
    public void send(String action, String value){
        String command = action + " " + value;

        System.out.println(command);
        out.println(command);
    }

    public String receive(){
        String response = in.nextLine();
        return response;
    }

    // TEST MAIN METHODE!
    public static void main(String[] args){
        ServerConnection connect = new ServerConnection("localhost");
        boolean startGame = true;

        if (startGame == true){
//            while(true){ // je hebt nog niet gewonnen of verloren?
//
//            }
            connect.send("login", "klaas");
            System.out.println(connect.receive());
            startGame = false;
            // Wil je nog een spelletje doen?
            // nee? startGame = false. Ja? Start x game.
        }
        //uitloggen etc.
    }
}
