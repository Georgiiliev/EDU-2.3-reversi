import controller.StartController;
import javafx.application.Application;
import javafx.stage.Stage;
import view.BoardView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
        BoardView boardView = new BoardView();
        boardView.paint(boardView);
    }


    public static void main(String[] args) {
        new StartController("localhost");
        launch(args);
    }
}
