package kom.st.model;

import java.time.LocalDateTime;
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

  public void addSleepRecord(SleepRecord record) throws ApplicationException {
    record.setVatin(vatin);
    repository.addSleepRecord(record);
  }

  public void removeSleepRecord(LocalDateTime start) {
    SleepRecord record = new SleepRecord();
    record.setVatin(vatin);
    record.setStart(start);
    repository.removeSleepRecord(record);
  }
}
