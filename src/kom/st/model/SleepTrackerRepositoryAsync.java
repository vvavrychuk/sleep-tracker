package kom.st.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SleepTrackerRepositoryAsync implements SleepTrackerRepositoryRx {
  private SleepTrackerRepository repository;
  private ExecutorService executor = Executors.newSingleThreadExecutor();

  private SleepTrackerRepository getRepository() {
    if (repository == null) {
      repository = new SleepTrackerRepository();
    }
    return repository;
  }

  @Override
  public ObservableList<String> getVatinList() {
    ObservableList<String> vatinList = FXCollections.observableArrayList();
    executor.execute(() -> vatinList.addAll(getRepository().getVatinList()));
    return vatinList;
  }

  @Override
  public ObservableList<SleepRecord> getSleepRecords(String vatin) {
    ObservableList<SleepRecord> sleepRecords = FXCollections.observableArrayList();
    executor.execute(() -> sleepRecords.addAll(getRepository().getSleepRecords(vatin)));
    return sleepRecords;
  }

  @Override
  public void addSleepRecord(SleepRecord record) throws Exception {
    executor.execute(() -> {
      try {
        getRepository().addSleepRecord(record);
      } catch (Exception e) {
        e.printStackTrace(); //TODO: how to propagate exception?
      }
    });
  }

  @Override
  public void shutdown() {
    executor.shutdown();
  }
}
