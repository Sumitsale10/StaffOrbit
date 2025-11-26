<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>StaffOrbit</title>

  <!-- favicon -->
  <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/LOGO.png">

  <!-- Bootstrap 5 -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom CSS -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

  <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
  <script defer src="${pageContext.request.contextPath}/js/app.js"></script>
</head>
<body class="bg-light">
<nav class="navbar navbar-expand-lg navbar-dark" style="background: var(--brand);">
  <div class="container">
    <a class="navbar-brand d-flex align-items-center" href="${pageContext.request.contextPath}/staff/employees">
      <img src="${pageContext.request.contextPath}/images/LOGO.png" alt="StaffOrbit logo"
           style="height:34px; width:auto; margin-right:10px; display:inline-block;">
      <span class="fw-bold">StaffOrbit</span>
    </a>

    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsMain"
            aria-controls="navbarsMain" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarsMain">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/staff/employees">Employees</a>
        </li>
        <!-- add more nav links here as needed -->
      </ul>
    </div>
  </div>
</nav>

<main class="container my-4">
