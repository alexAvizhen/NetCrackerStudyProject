<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 25.10.2016
  Time: 8:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
</head>
<body>

Show today rate
<form method="GET" action="/rate">
    <td>
        <input type="text" name="currencyAbbreviation" value="RUB">
    </td>
    <td>
        <input type="submit" value="submit"/>
    </td>
</form>
Show all users
<form method="GET" action="/user">
    <td>
        <input type="submit" value="submit"/>
    </td>
</form>


</body>
</html>
