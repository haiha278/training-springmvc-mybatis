<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="${pageContext.request.contextPath}/css/user-list.css" rel="stylesheet">
    <title>Hello</title>
</head>
<body class="container">
<h1 class="header">User List</h1>
<table>
    <thead>
    <tr>
        <th>Number</th>
        <th>Username</th>
        <th>Name</th>
        <th>Age</th>
        <th>Gender</th>
    </tr>
    </thead>
    <tbody>
    <c:set var="count" value="1"/>
    <c:forEach var="user" items="${allUsers}">
        <tr>
            <td>${count}</td>
            <td>${user.username}</td>
            <td>${user.name}</td>
            <td>${user.age}</td>
            <td>${user.gender}</td>
        </tr>
        <c:set var="count" value="${count + 1}"/>
    </c:forEach>
    </tbody>
</table>
</body>
</html>