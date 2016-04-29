package kom.st.model;

import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.beans.value.ObservableBooleanValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class SleepTrackerModel {
  private SleepTrackerRepository repository = new SleepTrackerRepository();

  private ObservableList<String> vatinList = FXCollections.observableList(repository.getVatinList());
  private StringProperty selectedVatin = new SimpleStringProperty();
  private ListProperty<SleepRecord> vatinSleepRecords = new SimpleListProperty<>();

  private ObjectProperty<LocalDateTime> startTime = new SimpleObjectProperty<>();

  public SleepTrackerModel() {
    vatinSleepRecords.bind(Bindings.createObjectBinding(
      () -> getSleepRecords(selectedVatin.get()), selectedVatin));
  }

  public ObservableList<String> getVatinList() {
    return vatinList;
  }

  public StringProperty selectedVatinProperty() {
    return selectedVatin;
  }

  public ListProperty<SleepRecord> vatinSleepRecordsProperty() {
    return vatinSleepRecords;
  }

  public ObservableList<SleepRecord> getSleepRecords(String vatin) {
    return FXCollections.observableList(repository.getSleepRecords(vatin));
  }

  public void startSleeping() {
    startTime.set(LocalDateTime.now());
  }

  public ObservableBooleanValue isStartedSleeping() {
    return startTime.isNotNull();
  }

  public void stopSleepingAndUpdateData(String vatin) throws Exception {
    if (!isStartedSleeping().get())
      throw new IllegalStateException();

    try {
      SleepRecord record = new SleepRecord();
      record.vatin = vatin;
      record.start = startTime.get();
      record.duration = (int)startTime.get().until(LocalDateTime.now(), ChronoUnit.SECONDS);
      repository.addSleepRecord(record);
      if (!vatinList.contains(vatin))
        vatinList.add(vatin);
      vatinSleepRecords.add(record);
    } finally {
      startTime.set(null);
    }
  }
}
