package kom.st.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class SleepTrackerModel {
  private ObservableList<String> vatinList = FXCollections.observableArrayList();

  public SleepTrackerModel() {
    try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }

    Connection conn = null;
    Statement stmt = null;
    try {
      String url = "jdbc:postgresql:SleepTracker?user=postgres&password=postgres";
      conn = DriverManager.getConnection(url);
      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("select distinct vatin from sleeptracker");
      while (rs.next()) {
        vatinList.add(rs.getString(1));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      DBUtils.close(stmt);
      DBUtils.close(conn);
    }
  }

  public ObservableList<String> getVatinList() {
    return vatinList;
  }
}
