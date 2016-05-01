<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

User ${sessionScope.sleepRecordsManager.vatin}

<table border="1">
  <c:forEach items="${sessionScope.sleepRecordsManager.sleepRecords}" var="sleepRecord">
    <tr>
      <td>${sleepRecord.start}</td>
      <td>${sleepRecord.duration}</td>
      <td><a href="${pageContext.servletContext.contextPath}/delete?start=${sleepRecord.start}">delete</a></td>
    </tr>
  </c:forEach>
</table>

<a href="create.jsp">add</a>
