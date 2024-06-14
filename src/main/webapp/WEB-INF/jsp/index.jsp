<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Home Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css" />
</head>
<body>
<div class="login" id="login-id">
    <!-- login page Html source -->
    <form class="login-form" id="login-form-id" action="/login" method="post" modelAttribute="loginDTO">
        <div class="input-info">
            <label class="label" for="username">Username:</label>
            <input
                    class="input"
                    type="text"
                    id="username"
                    name="username"
                    placeholder="Username"
                    required
            />
        </div>
        <div class="input-info">
            <label class="label" for="password">Password:</label>
            <input
                    class="input"
                    type="password"
                    id="password"
                    name="password"
                    placeholder="Password"
                    required
            />
        </div>
        <div class="submit">
            <button type="submit" class="button">Sign In</button>
            <a href="/register">Sign Up</a>
        </div>
    </form>
</div>
</body>
</html>