<%@ include file="../fragments/header.jsp" %>
<form method="post" action="${pageContext.request.contextPath}/staff/employees/edit/${employee.id}">
  <input type="hidden" name="id" value="${employee.id}" />
  <label>First name: <input name="firstName" value="${employee.firstName}" required/></label><br/>
  <label>Last name: <input name="lastName" value="${employee.lastName}"/></label><br/>
  <label>Email: <input name="email" type="email" value="${employee.email}"/></label><br/>
  <label>Department: <input name="department" value="${employee.department}"/></label><br/>
  <button type="submit">Save</button>
</form>
<%@ include file="../fragments/footer.jsp" %>
