package kom.st.view;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class TextInputDialog extends javafx.scene.control.TextInputDialog {
  private Label label;

  public TextInputDialog() {
    getDialogPane().lookupButton(ButtonType.OK).disableProperty().bind(
      getEditor().textProperty().length().isEqualTo(0));
  }

  public void setError(String message) {
    if (label == null) {
      label = new Label();
      label.setTextFill(Color.RED);
      GridPane container = (GridPane) getDialogPane().getContent();
      container.add(label, 0, 1);
    }

    label.setText(message);
    getDialogPane().getScene().getWindow().sizeToScene();
  }
}
