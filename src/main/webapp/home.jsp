<%@ page import="java.util.List" %>
<%@ page import="model.Student" %><%--
  Created by IntelliJ IDEA.
  User: johntoan98gmail.com
  Date: 01/07/2022
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Grayscale - Start Bootstrap Theme</title>
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/styles.css" rel="stylesheet" />
</head>
<body id="page-top">
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand" href="#page-top">Start Bootstrap</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            Menu
            <i class="fas fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link" href="#about">About</a></li>
                <li class="nav-item"><a class="nav-link" href="#projects">Projects</a></li>
                <li class="nav-item"><a class="nav-link" href="#signup">Contact</a></li>
            </ul>
            <button type="button" class="btn btn-success"><a href="/student?action=singout">SingOut</a></button>
        </div>
    </div>
</nav>
<!-- Masthead-->
<header class="masthead">
    <div class="container px-4 px-lg-5 d-flex h-100 align-items-center justify-content-center">
        <div class="d-flex justify-content-center">
            <div class="text-center">
                <h1 class="mx-auto my-0 text-uppercase">Grayscale</h1>
                <h2 class="text-white-50 mx-auto mt-2 mb-5">A free, responsive, one page Bootstrap theme created by Start Bootstrap.</h2>
                <a class="btn btn-primary" href="#about">Get Started</a>
            </div>
        </div>
    </div>
</header>
<h2>Table Student</h2>  <button type="button" class="btn btn-success"><a href="/student?action=create">Create</a></button>


<form action="/student">
    <input name="action" value="search" hidden>
    <input name="search"><button type="submit">Search</button>
</form>


<table class="container table table-striped">
    <thead>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>DateOfBirth</th>
        <th>address</th>
        <th>email</th>
        <th>phoneNumber</th>
        <th>Class Name</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="p" items="${students}">
        <tr>
            <td>${p.getId()}</td>
            <td>${p.name}</td>
            <td>${p.dateOfBirth}</td>
            <td>${p.address}</td>
            <td>${p.email}</td>
            <td>${p.phoneNumber}</td>
            <td>${p.classStudent.name}</td>
            <td><button type="button" class="btn btn-warning"><a href="/student?id=${p.id}&action=edit">edit</a></button></td>
            <td><button type="button" class="btn btn-danger"><a href="/student?id=${p.id}&action=delete">Delete</a></button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<!-- Footer-->
<footer class="footer bg-black small text-center text-white-50"><div class="container px-4 px-lg-5">Copyright &copy; Your Website 2022</div></footer>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="js/scripts.js"></script>
<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
<!-- * *                               SB Forms JS                               * *-->
<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</body>
</html>
