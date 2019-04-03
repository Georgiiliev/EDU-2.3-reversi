import controller.StartController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.StateHandler;
import view.BoardView;
import view.GameView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
        GameView gameView = new GameView();
    }


    public static void main(String[] args) {
        StateHandler stateHandler = new StateHandler();
        stateHandler.setGameState(stateHandler.getGameStarted());
//        new StartController("localhost");
        launch(args);
    }
}
