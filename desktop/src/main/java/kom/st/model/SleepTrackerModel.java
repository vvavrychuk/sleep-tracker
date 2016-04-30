package kom.st.model;

import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.beans.value.ObservableBooleanValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.regex.Pattern;

public class SleepTrackerModel {
  private SleepTrackerRepositoryRx repository = new SleepTrackerRepositoryAsync();

  private ObservableList<String> vatinList = repository.getVatinList();
  private StringProperty selectedVatin = new SimpleStringProperty();
  private ListProperty<SleepRecord> vatinSleepRecords = new SimpleListProperty<>();

  private ObjectProperty<LocalDateTime> startTime = new SimpleObjectProperty<>();

  public SleepTrackerModel() {
    vatinSleepRecords.bind(Bindings.createObjectBinding(
      () -> getSleepRecords(selectedVatin.get()), selectedVatin));
  }

  public ObservableList<String> getVatinList() {
    return FXCollections.unmodifiableObservableList(vatinList);
  }

  public void addVatin(String vatin) throws ApplicationException {
    vatin = vatin.trim();
    if (vatinList.contains(vatin))
      throw new ApplicationException("VATIN already exists!");
    if (!Pattern.matches("\\d{10}", vatin))
      throw new ApplicationException("VATIN should contain exactly 10 digits!");
    vatinList.add(vatin);
  }

  public StringProperty selectedVatinProperty() {
    return selectedVatin;
  }

  public ListProperty<SleepRecord> vatinSleepRecordsProperty() {
    return vatinSleepRecords;
  }

  public ObservableList<SleepRecord> getSleepRecords(String vatin) {
    return repository.getSleepRecords(vatin);
  }

  public void startSleeping() {
    startTime.set(LocalDateTime.now());
  }

  public ObservableBooleanValue isStartedSleeping() {
    return startTime.isNotNull();
  }

  public void stopSleepingAndUpdateData() throws ApplicationException {
    if (!isStartedSleeping().get())
      throw new IllegalStateException();

    try {
      SleepRecord record = new SleepRecord();
      record.setVatin(selectedVatin.get());
      record.setStart(startTime.get());
      record.setDuration((int)startTime.get().until(LocalDateTime.now(), ChronoUnit.SECONDS));
      repository.addSleepRecord(record);
      if (!vatinList.contains(selectedVatin.get()))
        vatinList.add(selectedVatin.get());
      vatinSleepRecords.add(record);
    } finally {
      startTime.set(null);
    }
  }

  public void shutdown() {
    repository.shutdown();
  }
}
