<%--
  Created by IntelliJ IDEA.
  User: THINKPADX240
  Date: 2/4/2021
  Time: 3:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>update</title>
</head>
<body>
<form method="post">
    <table>
        <tr>
            <td>input name</td>
            <td><input type="text" name="name" value="${user.getName()}"></td>
        </tr>
        <tr>
            <td>input email</td>
            <td><input type="text" name="email" value="${user.getEmail()}"></td>
        </tr>
    </table>
    <input type="submit" value="Update">
</form>
</body>
</html>
