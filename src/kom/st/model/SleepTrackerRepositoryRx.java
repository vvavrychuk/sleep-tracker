package kom.st.model;

import javafx.collections.ObservableList;

public interface SleepTrackerRepositoryRx {
  ObservableList<String> getVatinList();
  ObservableList<SleepRecord> getSleepRecords(String vatin);
  void addSleepRecord(SleepRecord record) throws Exception;
}
