package kom.st.controller;

import kom.st.model.SleepRecordsManager;
import kom.st.model.SleepTrackerRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if (getServletContext().getAttribute("repository") == null)
      getServletContext().setAttribute("repository", new SleepTrackerRepository());

    String vatin = req.getParameter("vatin");
    SleepTrackerRepository repository = (SleepTrackerRepository) getServletContext().getAttribute("repository");
    SleepRecordsManager manager = new SleepRecordsManager(repository, vatin);
    req.getSession().setAttribute("sleepRecordsManager", manager);
    getServletContext().getRequestDispatcher("/list.jsp").forward(req, resp);
  }
}
