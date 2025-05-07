
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - Prime Reel</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div class="login-page">
    <div class="form-container">
        <h1>Login to Prime Reel</h1>
        <form action="${pageContext.request.contextPath}/auth/login" method="post">
            <div class="input-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="input-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit" class="btn">Login</button>
        </form>
        <div class="error-message">
            <c:if test="${param.error == 'true'}">
                <p style="color: red;">Invalid username or password. Please try again.</p>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>