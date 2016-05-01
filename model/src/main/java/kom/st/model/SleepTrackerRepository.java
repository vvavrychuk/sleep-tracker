package kom.st.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SleepTrackerRepository {
  private static final String URL = "jdbc:postgresql:SleepTracker?user=postgres&password=postgres";
  private Connection conn;

  static String wrap(String str) {
    return "'" + str + "'";
  }

  public SleepTrackerRepository() {
    try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }

    try {
      conn = DriverManager.getConnection(URL);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public List<String> getVatinList() {
    List<String> vatinList = new ArrayList<>();
    Statement stmt = null;
    try {
      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("select distinct vatin from sleeptracker");
      while (rs.next()) {
        vatinList.add(rs.getString(1));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      DBUtils.close(stmt);
    }
    return vatinList;
  }

  public List<SleepRecord> getSleepRecords(String vatin) {
    List<SleepRecord> sleepRecords = new ArrayList<>();
    Statement stmt = null;
    try {
      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("select start, duration from sleeptracker where vatin = " + wrap(vatin));
      while (rs.next()) {
        SleepRecord record = new SleepRecord();
        record.setStart(rs.getTimestamp(1).toLocalDateTime());
        record.setDuration(rs.getInt(2));
        sleepRecords.add(record);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      DBUtils.close(stmt);
    }
    return sleepRecords;
  }

  public void addSleepRecord(SleepRecord record) throws ApplicationException {
    Statement stmt = null;
    try {
      stmt = conn.createStatement();
      int rows = stmt.executeUpdate("insert into sleeptracker values (" +
        wrap(record.getVatin()) + ", " +
        wrap(record.getStart().toString()) + ", " +
        record.getDuration() + ")");
      if (rows == 0)
        throw new ApplicationException("No rows added!");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      DBUtils.close(stmt);
    }
  }

  public void removeSleepRecord(SleepRecord record) {
    Statement stmt = null;
    try {
      stmt = conn.createStatement();
      stmt.executeUpdate("delete from sleeptracker where (" +
        "(vatin = " + wrap(record.getVatin()) + ") and (start =" + wrap(record.getStart().toString()) + "))");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      DBUtils.close(stmt);
    }
  }
}
