<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Login | Bookboxd</title>
</head>
<body>
    <div align="center">
        <h1>Login to Bookboxd</h1><br>
        <form action="Login" method="post">
            <label for="username">Username :</label>
            <input name="username" size="40" />
            <br><br>
            <label for="password">Password :</label>
            <input name="password" type="password" size="40" />
            <br><br>
            <br>${errorMessage}         
            <button type="submit">Login</button>
            <br>
            <p>Don't have an account? <a href=register.jsp>Register now.</a></p>
        </form>
    </div>
</body>
</html>