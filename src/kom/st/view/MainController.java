package kom.st.view;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import kom.st.model.ApplicationException;
import kom.st.model.SleepRecord;
import kom.st.model.SleepTrackerModel;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

public class MainController {
  private SleepTrackerModel model = new SleepTrackerModel();

  @FXML
  private ListView<String> vatinList;

  @FXML
  private TableView<SleepRecord> sleepRecordsTable;
  @FXML
  private TableColumn<SleepRecord, LocalDateTime> startColumn;
  @FXML
  private TableColumn<SleepRecord, Number> durationColumn;

  @FXML
  private Button startButton;
  @FXML
  private Button stopButton;

  @FXML
  private void initialize() {
    vatinList.setItems(model.getVatinList());
    model.selectedVatinProperty().bind(vatinList.getSelectionModel().selectedItemProperty());
    sleepRecordsTable.itemsProperty().bind(model.vatinSleepRecordsProperty());

    startColumn.setCellValueFactory(record -> new SimpleObjectProperty<>(record.getValue().start));
    durationColumn.setCellValueFactory(record -> new SimpleIntegerProperty(record.getValue().duration));

    startButton.disableProperty().bind(model.selectedVatinProperty().isNull().or(model.isStartedSleeping()));
    stopButton.disableProperty().bind(Bindings.not(model.isStartedSleeping()));
  }

  public static ViewController<MainController> createViewController() {
    try {
      FXMLLoader loader = new FXMLLoader(MainController.class.getResource("Main.fxml"));
      return new ViewController<MainController>() {{
        view = loader.load();
        controller = loader.getController();
      }};
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void handleNewUser(ActionEvent actionEvent) {
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("New user");
    dialog.setHeaderText("New vatin");
    dialog.setContentText("Please enter user name:");

    dialog.setOnCloseRequest(event -> {
      if (dialog.getResult() == null)
        return;

      try {
        model.addVatin(dialog.getResult());
      } catch (ApplicationException e) {
        dialog.setError(e.getMessage());
        event.consume();
      }
    });

    dialog.showAndWait();
  }

  public void handleStart(ActionEvent event) {
    model.startSleeping();
  }

  public void handleStop(ActionEvent event) {
    try {
      model.stopSleepingAndUpdateData();
    } catch (Exception e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error Dialog");
      alert.setContentText(e.getMessage());
      alert.showAndWait();
    }
  }

  public void shutdown() {
    model.shutdown();
  }
}
