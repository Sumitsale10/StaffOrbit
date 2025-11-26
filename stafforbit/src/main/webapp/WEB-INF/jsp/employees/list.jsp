<%@ include file="../fragments/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="d-flex justify-content-between align-items-center mb-3">
  <h2 class="h4 mb-0">Employees</h2>
  <div>
    <input id="q" type="search" class="form-control form-control-sm d-inline-block" placeholder="Search by name" style="width:220px">
    <button id="searchBtn" class="btn btn-sm btn-outline-primary ms-2" onclick="doSearch(event)">Search</button>
    <button class="btn btn-sm btn-primary ms-2" onclick="showCreateModal()">+ Add</button>
  </div>
</div>

<div class="table-responsive shadow-sm bg-white rounded">
  <table class="table table-hover mb-0" id="employeesTable">
    <thead class="table-light">
      <tr>
        <th style="width:6%">#</th>
        <th style="width:18%">First</th>
        <th style="width:18%">Last</th>
        <th style="width:26%">Email</th>
        <th style="width:12%">Dept</th>
        <th style="width:20%">Actions</th>
      </tr>
    </thead>
    <tbody>
      <!-- Server-side rendered fallback (if any) -->
      <c:forEach var="emp" items="${employees}">
        <tr data-id="${emp.id}">
          <td>${emp.id}</td>
          <td>${emp.firstName}</td>
          <td>${emp.lastName}</td>
          <td>${emp.email}</td>
          <td>${emp.department}</td>
          <td>
            <button class="btn btn-sm btn-outline-secondary" onclick="viewDetails(${emp.id})">View</button>
            <button class="btn btn-sm btn-outline-info" onclick="showEditModal(${emp.id})">Edit</button>
            <button class="btn btn-sm btn-outline-danger" onclick="deleteEmployee(${emp.id})">Delete</button>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>

<!-- Modal (create / edit) -->
<div class="modal fade" id="employeeModal" tabindex="-1" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <form id="employeeForm" onsubmit="saveEmployee(event)">
        <div class="modal-header">
          <h5 class="modal-title" id="employeeModalTitle">Add Employee</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <input type="hidden" id="empId" />
          <div class="mb-2">
            <label class="form-label">First name</label>
            <input id="firstName" class="form-control" required minlength="1" maxlength="100">
          </div>
          <div class="mb-2">
            <label class="form-label">Last name</label>
            <input id="lastName" class="form-control" maxlength="100">
          </div>
          <div class="mb-2">
            <label class="form-label">Email</label>
            <input id="email" type="email" class="form-control" required>
          </div>
          <div class="mb-2">
            <label class="form-label">Department</label>
            <input id="department" class="form-control">
          </div>
          <div id="formAlert" class="alert alert-danger d-none"></div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
          <button type="submit" class="btn btn-primary" id="saveBtn">Save</button>
        </div>
      </form>
    </div>
  </div>
</div>

<%@ include file="../fragments/footer.jsp" %>
