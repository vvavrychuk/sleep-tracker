package kom.st.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SleepTrackerRepositorySync implements SleepTrackerRepositoryRx {
  private SleepTrackerRepository repository = new SleepTrackerRepository();

  @Override
  public ObservableList<String> getVatinList() {
    return FXCollections.observableList(repository.getVatinList());
  }

  @Override
  public ObservableList<SleepRecord> getSleepRecords(String vatin) {
    return FXCollections.observableList(repository.getSleepRecords(vatin));
  }

  @Override
  public void addSleepRecord(SleepRecord record) throws Exception {
    repository.addSleepRecord(record);
  }
}
