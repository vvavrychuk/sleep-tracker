package kom.st.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SleepTrackerRepository {
  private static final String URL = "jdbc:postgresql:SleepTracker?user=postgres&password=postgres";
  private Connection conn;

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
}
