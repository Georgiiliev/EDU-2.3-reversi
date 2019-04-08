import controller.MoveController;
import model.StateHandler;

public class Main {
    public static void main(String[] args) {
        MoveController.setTimer(6);
        StateHandler stateHandler = new StateHandler();
        stateHandler.setGameState(stateHandler.getConnectingToServer());
        stateHandler.establishConnection();
    }
}