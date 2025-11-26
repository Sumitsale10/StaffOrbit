console.log("app.js loaded");

// Build API URL safely with context path
function api(path) {
  const base = (typeof BASE_PATH !== 'undefined' && BASE_PATH !== '/' ? BASE_PATH : '');
  return base + path;
}

// Load employees on page load
document.addEventListener('DOMContentLoaded', () => {
  loadEmployees();
});

// -------------- LOAD EMPLOYEES --------------
async function loadEmployees() {
  try {
    const res = await fetch(api('/api/employees'));
    const list = await res.json();
    renderTable(list);
  } catch (err) {
    console.error("Failed to load employees", err);
  }
}

// -------------- RENDER TABLE ----------------
function renderTable(list) {
  const tbody = document.querySelector('#employeesTable tbody');
  if (!tbody) return;

  if (!list || list.length === 0) {
    tbody.innerHTML = `
      <tr><td colspan="6" class="text-center py-4">No employees found</td></tr>
    `;
    return;
  }

  tbody.innerHTML = list.map((emp, idx) => `
    <tr>
      <td>${idx + 1}</td>                    <!-- <-- row number (1-based) -->
      <td>${escape(emp.firstName)}</td>
      <td>${escape(emp.lastName)}</td>
      <td>${escape(emp.email)}</td>
      <td>${escape(emp.department)}</td>
      <td>
        <button class="btn btn-sm btn-outline-secondary" onclick="viewDetails(${emp.id})">View</button>
        <button class="btn btn-sm btn-outline-info" onclick="showEditModal(${emp.id})">Edit</button>
        <button class="btn btn-sm btn-outline-danger" onclick="deleteEmployee(${emp.id})">Delete</button>
      </td>
    </tr>
  `).join('');
}
// Escape HTML to prevent XSS
function escape(str) {
  if (!str) return "";
  return str.replace(/[&<>"]/g, t => ({ "&": "&amp;", "<": "&lt;", ">": "&gt;", '"': "&quot;" }[t]));
}

// -------------- DELETE EMPLOYEE --------------
async function deleteEmployee(id) {
  if (!confirm("Delete employee #" + id + "?")) return;

  try {
    const res = await fetch(api('/api/employees/' + id), {
      method: 'DELETE'
    });

    if (res.status === 204) {
      loadEmployees();
    } else {
      const body = await res.json();
      alert("Delete failed: " + (body.message || JSON.stringify(body)));
    }
  } catch (err) {
    alert("Error deleting employee: " + err);
  }
}

// -------------- SEARCH EMPLOYEES --------------
async function doSearch(event) {
  event.preventDefault();
  const q = document.getElementById('q').value.trim();

  try {
    const res = await fetch(api('/api/employees/search?q=' + encodeURIComponent(q)));
    const list = await res.json();
    renderTable(list);
  } catch (err) {
    console.error("Search error", err);
  }
}

// -------------- VIEW EMPLOYEE --------------
function viewDetails(id) {
  window.location.href = api('/staff/employees/' + id);
}

// -------------- CREATE MODAL ----------------
function showCreateModal() {
  clearForm();
  document.getElementById("employeeModalTitle").innerText = "Add Employee";

  const modal = new bootstrap.Modal(document.getElementById('employeeModal'));
  modal.show();
}

// -------------- EDIT MODAL ------------------
async function showEditModal(id) {
  try {
    const res = await fetch(api('/api/employees/' + id));
    const emp = await res.json();

    document.getElementById("empId").value = emp.id;
    document.getElementById("firstName").value = emp.firstName;
    document.getElementById("lastName").value = emp.lastName;
    document.getElementById("email").value = emp.email;
    document.getElementById("department").value = emp.department;

    document.getElementById("employeeModalTitle").innerText = "Edit Employee";

    const modal = new bootstrap.Modal(document.getElementById('employeeModal'));
    modal.show();

  } catch (err) {
    alert("Failed to load employee");
  }
}

// -------------- SAVE EMPLOYEE (CREATE + UPDATE) --------------
async function saveEmployee(event) {
  event.preventDefault();

  const id = document.getElementById("empId").value;

  const payload = {
    firstName: document.getElementById("firstName").value,
    lastName: document.getElementById("lastName").value,
    email: document.getElementById("email").value,
    department: document.getElementById("department").value
  };

  try {
    let url = api('/api/employees');
    let method = 'POST';

    if (id) {
      url = api('/api/employees/' + id);
      method = 'PUT';
    }

    const res = await fetch(url, {
      method: method,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload)
    });

    if (res.ok) {
      bootstrap.Modal.getInstance(document.getElementById('employeeModal')).hide();
      loadEmployees();
    } else {
      const body = await res.json();
      showFormError(body.message || "Validation error");
    }

  } catch (err) {
    showFormError(err.message);
  }
}

function showFormError(msg) {
  const alertBox = document.getElementById("formAlert");
  alertBox.classList.remove("d-none");
  alertBox.innerText = msg;
}

function clearForm() {
  document.getElementById("empId").value = "";
  document.getElementById("firstName").value = "";
  document.getElementById("lastName").value = "";
  document.getElementById("email").value = "";
  document.getElementById("department").value = "";
  document.getElementById("formAlert").classList.add("d-none");
}
