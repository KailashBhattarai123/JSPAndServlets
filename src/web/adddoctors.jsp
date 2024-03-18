<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hello JSP</title>
</head>
<body>
    <h1>Hello, this is page ADDS/Edits data.</h1>
    <p>Welcome to the Add edit page.</p>

    <form action="adddoctors" method="post">

        <input type="hidden" name="id" value="${id}">

        <label for="name">Name:</label><br>
        <input type="text" id="name" name="name" value="${name}" required><br><br>

        <label for="email">Email:</label><br>
        <input type="email" id="email" name="email" value="${email}" required></input<br><br>

        <label for="phone">Phone:</label><br>
        <input type="text" id="phone" name="phone" value="${phone}" require></input><br><br>

        <label for="address">Address:</label><br>
        <textarea id="address" name="address" rows="4" cols="50" required>${address}</textarea><br><br>

        <label for="description">Description:</label><br>
        <textarea id="description" name="description" rows="4" cols="50" required>${description}</textarea><br><br>

        <input type="submit" value="Submit">

    </form>

</body>
</html>