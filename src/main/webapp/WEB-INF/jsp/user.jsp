<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 28.10.2016
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <thead>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>login</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>${user.name}</td>
            <td>${user.surname}</td>
            <td>${user.login}</td>
        </tr>
    </tbody>
    </table>

    </body>
</html>