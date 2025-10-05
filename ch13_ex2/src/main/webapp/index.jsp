<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Admin - Murach</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>
<body>

<h1>Users</h1>

<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th colspan="2">Actions</th>
    </tr>

    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.email}</td>
            <td><a href="userAdmin?action=display_user&amp;email=${user.email}">Update</a></td>
            <td><a href="userAdmin?action=delete_user&amp;email=${user.email}"
                   onclick="return confirm('Are you sure you want to delete this user?');">Delete</a></td>
        </tr>
    </c:forEach>
</table>

<p><a href="userAdmin?action=display_users">Refresh</a></p>

</body>
</html>
