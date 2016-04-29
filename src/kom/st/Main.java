package kom.st;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kom.st.view.MainController;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Sleep tracker");
        primaryStage.setScene(new Scene(MainController.createView()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
