package kom.st;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;

import java.io.IOException;

public class MainController {
  private SleepTrackerModel model = new SleepTrackerModel();

  @FXML
  private ListView<String> vatinList;

  @FXML
  private void initialize() {
    vatinList.setItems(model.getVatinList());
  }

  static Parent createView() {
    try {
      return FXMLLoader.load(MainController.class.getResource("Main.fxml"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
