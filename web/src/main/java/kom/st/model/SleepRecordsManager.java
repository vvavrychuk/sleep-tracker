package kom.st.model;

import java.util.List;

public class SleepRecordsManager {
  private SleepTrackerRepository repository;
  private String vatin;

  public SleepRecordsManager(SleepTrackerRepository repository, String vatin) {
    this.repository = repository;
    this.vatin = vatin;
  }

  public String getVatin() {
    return vatin;
  }

  public List<SleepRecord> getSleepRecords() {
    return repository.getSleepRecords(vatin);
  }
}
