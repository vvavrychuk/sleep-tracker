package kom.st.controller;

import kom.st.model.SleepRecordsManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    SleepRecordsManager manager = (SleepRecordsManager) req.getSession().getAttribute("sleepRecordsManager");
    LocalDateTime start = LocalDateTime.parse(req.getParameter("start"));
    manager.removeSleepRecord(start);
    resp.sendRedirect(getServletContext().getContextPath() + "/list.jsp");
  }
}
