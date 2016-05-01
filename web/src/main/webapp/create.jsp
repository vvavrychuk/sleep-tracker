<form action="${pageContext.servletContext.contextPath}/create" method="post">
  <label for="start">Start:</label>
  <input id="start" name="start" type="datetime-local">

  <label for="duration">Duration:</label>
  <input id="duration" name="duration">

  <input type="submit" value="add">
</form>
