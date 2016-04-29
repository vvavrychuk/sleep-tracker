<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

User ${sessionScope.sleepRecordsManager.vatin}

<c:forEach items="${sessionScope.sleepRecordsManager.sleepRecords}" var="sleepRecord">
  <tr>
    <td>${sleepRecord.duration}</td>
  </tr>
</c:forEach>
