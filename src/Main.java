import connection.ServerConnection;
import controller.CommandHandler;
import model.StateHandler;
import view.GameView;

public class Main {
    public static void main(String[] args) {
        StateHandler stateHandler = new StateHandler();
        ServerConnection connect = new ServerConnection(stateHandler);

        if (connect.connectionSucceed()) {
            CommandHandler commandHandler = new CommandHandler(connect, stateHandler);
            GameView gameView = new GameView(stateHandler,connect, commandHandler);

            Thread thread = new Thread(commandHandler);
            thread.start();
        } else {
            System.exit(0);
        }
    }
}