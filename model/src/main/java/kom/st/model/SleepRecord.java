package kom.st.model;

import java.time.LocalDateTime;

public class SleepRecord {
  private String vatin;
  private LocalDateTime start;
  private int duration;

  public String getVatin() {
    return vatin;
  }

  public void setVatin(String vatin) {
    this.vatin = vatin;
  }

  public LocalDateTime getStart() {
    return start;
  }

  public void setStart(LocalDateTime start) {
    this.start = start;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }
}
