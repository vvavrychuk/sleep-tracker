package kom.st.controller;

import kom.st.model.ApplicationException;
import kom.st.model.SleepRecord;
import kom.st.model.SleepRecordsManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    LocalDateTime start = LocalDateTime.parse(req.getParameter("start"));
    int duration = Integer.parseInt(req.getParameter("duration"));
    SleepRecord record = new SleepRecord();
    record.setStart(start);
    record.setDuration(duration);

    SleepRecordsManager manager = (SleepRecordsManager) req.getSession().getAttribute("sleepRecordsManager");
    try {
      manager.addSleepRecord(record);
    } catch (ApplicationException e) {
      throw new ServletException(e);
    }

    resp.sendRedirect(getServletContext().getContextPath() + "/list.jsp");
  }
}
