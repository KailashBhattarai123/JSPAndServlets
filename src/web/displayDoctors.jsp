<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Confirmation</title>
</head>
<body>
<h1>Display Doctors</h1>
<p>This is the display doctors page.</p>
<table border="1">
<thead>
<tr>
    <th>Name</th>
    <th>Email</th>
    <th>Phone</th>
    <th>Address</th>
    <th>Description</th>
    <th>Edit</th>
    <th>Delete</th>
</tr>
</thead>
<tbody>
    <c:forEach items="${doctors}" var="doctor">
        <tr>
            <td>${doctor.name}</td>
            <td>${doctor.email}</td>
            <td>${doctor.phone}</td>
            <td>${doctor.address}</td>
            <td>${doctor.description}</td>
            <td><a href="displayDoctors?action=edit&id=${doctor.doctorId}">Edit</a></td>
            <td><a href="displayDoctors?action=delete&id=${doctor.doctorId}">Delete</a></td>
        </tr>
    </c:forEach>
</tbody>
<table>
</body>
</html>