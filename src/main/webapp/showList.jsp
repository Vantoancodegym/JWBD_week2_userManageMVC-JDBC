<%--
  Created by IntelliJ IDEA.
  User: THINKPADX240
  Date: 2/4/2021
  Time: 2:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>show list</title>
    <style>
        table,tr,td{
            border: black 1px solid;
        }
    </style>
</head>
<body>
<table>
    <tr>
        <td>Name</td>
        <td>Email</td>
        <td><a href="/user?action=create">Create new</a></td>
    </tr>
    <c:forEach items="${list}" var="user">
    <tr>
        <td>${user.getName()}</td>
        <td>${user.getEmail()}</td>
        <td><a href="/user?action=update&id=${user.getId()}">Update</a></td>
        <td><a href="/user?action=delete&id=${user.getId()}">Delete</a></td>
    </tr>
    </c:forEach>
</table>
</body>
</html>
