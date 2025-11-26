<%@ include file="../fragments/header.jsp" %>
<h2>Employee details</h2>
<p>ID: ${employee.id}</p>
<p>Name: ${employee.firstName} ${employee.lastName}</p>
<p>Email: ${employee.email}</p>
<p>Department: ${employee.department}</p>
<a href="${pageContext.request.contextPath}/staff/employees">Back</a>
<%@ include file="../fragments/footer.jsp" %>
