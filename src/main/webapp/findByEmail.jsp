<%--
  Created by IntelliJ IDEA.
  User: THINKPADX240
  Date: 2/4/2021
  Time: 4:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find by name</title>
</head>
<body>
<form method="post">
   <input type="text" name="email" value="${user.getEmail()}"><br>
    <input type="submit" value="Find">
</form>
<p>Result</p>
<table>
    <tr>
        <td>${user.getName()}</td>
        <td>${user.getEmail()}</td>
    </tr>
</table>
</body>
</html>
