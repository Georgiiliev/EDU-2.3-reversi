import connection.ServerConnection;
import controller.CommandHandler;
import model.StateHandler;
import view.GameView;

public class Main {
    public static void main(String[] args) {
        StateHandler stateHandler = new StateHandler();
        stateHandler.setGameState(stateHandler.getConnectingToServer());
        stateHandler.establishConnection();
    }
}