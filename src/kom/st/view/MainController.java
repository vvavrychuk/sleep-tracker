package kom.st.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import kom.st.model.SleepTrackerModel;

import java.io.IOException;
import java.util.Optional;

public class MainController {
  private SleepTrackerModel model = new SleepTrackerModel();

  @FXML
  private ListView<String> vatinList;

  @FXML
  private void initialize() {
    vatinList.setItems(model.getVatinList());
  }

  public static Parent createView() {
    try {
      return FXMLLoader.load(MainController.class.getResource("Main.fxml"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void handleNewUser(ActionEvent actionEvent) {
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("New user");
    dialog.setContentText("Please enter user name:");

    Optional<String> result = dialog.showAndWait();
    if (result.isPresent()) {
      model.getVatinList().add(result.get());
    }
  }
}
