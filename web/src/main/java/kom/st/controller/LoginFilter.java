package kom.st.controller;

import kom.st.model.SleepRecordsManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;

    SleepRecordsManager manager = (SleepRecordsManager) req.getSession().getAttribute("sleepRecordsManager");
    if ((manager == null) && (!req.getServletPath().equals("/login.jsp")))
      resp.sendRedirect(req.getContextPath() + "/login.jsp");
    else
      chain.doFilter(req, resp);
  }

  @Override
  public void destroy() {
  }
}
