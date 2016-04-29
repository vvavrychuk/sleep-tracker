package kom.st;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kom.st.view.MainController;
import kom.st.view.ViewController;

public class Main extends Application {
  ViewController<MainController> main;

  @Override
  public void start(Stage primaryStage) throws Exception {
    main = MainController.createViewController();
    primaryStage.setTitle("Sleep tracker");
    primaryStage.setScene(new Scene(main.view));
    primaryStage.show();
  }

  @Override
  public void stop() throws Exception {
    main.controller.shutdown();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
