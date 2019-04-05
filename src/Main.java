import model.StateHandler;

public class Main {
    public static void main(String[] args) {
        StateHandler stateHandler = new StateHandler();
        stateHandler.setGameState(stateHandler.getConnectingToServer());
        stateHandler.establishConnection();
    }
}