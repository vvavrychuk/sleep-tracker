package kom.st.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SleepTrackerModel {
  private SleepTrackerRepository repository = new SleepTrackerRepository();
  private ObservableList<String> vatinList = FXCollections.observableList(repository.getVatinList());

  public ObservableList<String> getVatinList() {
    return vatinList;
  }
}
