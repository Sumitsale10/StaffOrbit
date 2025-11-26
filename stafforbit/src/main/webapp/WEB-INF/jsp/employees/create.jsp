<%@ include file="../fragments/header.jsp" %>
<form method="post" action="${pageContext.request.contextPath}/staff/employees">
  <label>First name: <input name="firstName" required/></label><br/>
  <label>Last name: <input name="lastName"/></label><br/>
  <label>Email: <input name="email" type="email"/></label><br/>
  <label>Department: <input name="department"/></label><br/>
  <button type="submit">Create</button>
</form>
<%@ include file="../fragments/footer.jsp" %>
