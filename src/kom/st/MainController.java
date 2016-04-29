package kom.st;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class MainController {
  static Parent createView() {
    try {
      return FXMLLoader.load(MainController.class.getResource("Main.fxml"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
