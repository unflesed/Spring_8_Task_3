
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>

    <h1>Login Page</h1>
    <h2>Enter your credentials</h2>
    <form action="/Samples_war_exploded/login" method="post">
        <p>Login: </p>
        <input type="text" name="username"/><br/>
        <br/>
        <p>Password: </p>
        <input type="password" name="password">
        <br/>
        <input type="submit" value="Login">
    </form>

</body>
</html>
