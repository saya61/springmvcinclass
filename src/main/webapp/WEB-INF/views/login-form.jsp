<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Login Form</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/req-data-check/example2-submit" method="post">
    <label>Email:</label>
    <input type="text" name="email"><br>
    <label>Password:</label>
    <input type="password" name="password"><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>