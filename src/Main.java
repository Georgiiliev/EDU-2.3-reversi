import model.StateHandler;
import view.ConnectionView;

public class Main {
    public static void main(String[] args) {
        StateHandler stateHandler = new StateHandler();
        ConnectionView connectionView = new ConnectionView(stateHandler);
    }
}