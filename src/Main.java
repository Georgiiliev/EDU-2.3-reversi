import controller.StartController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.StateHandler;
import view.GameView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        GameView gameView = new GameView();
    }


    public static void main(String[] args) {
        StateHandler stateHandler = new StateHandler();
        stateHandler.setGameState(stateHandler.getConnectingToServer());
        System.out.println(stateHandler.getState());

        launch(args);
    }
}