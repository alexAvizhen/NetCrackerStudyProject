<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 28.10.2016
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Users</title>

    <c:url var="home" value="/" scope="request" />

    <spring:url value="/resources/css/main.css" var="coreCss" />
    <spring:url value="/resources/css/bootstrap.css"
                var="bootstrapCss" />
    <link href="${bootstrapCss}" rel="stylesheet" />
    <link href="${coreCss}" rel="stylesheet" />

    <spring:url value="/resources/js/lib/jquery.1.10.2.min.js"
                var="jqueryJs" />
    <script src="${jqueryJs}"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <div class="row">
        <h1>Site name</h1>
        <div class="navbar navbar-inverse">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#responsive-menu">
                        <span class="sr-only">Open navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#"> Company logo</a>
                </div>
                <div class="collapse navbar-collapse" id="responsive-menu">
                    <ul class="nav navbar-nav">
                        <li><a href="#">Главная</a> </li>
                        <li><a href="#">Объявления</a> </li>
                        <li><a href="#">Акции и скидки</a> </li>
                        <li><a href="#">Контакты</a> </li>
                        <li><a href="#">Корзина</a> </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="container">
    <div class="row">
        <h3>Users</h3>
    </div>
</div>
<c:if test="${not empty users}">
        <table class="table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Surname</th>
                <th>Login</th>
                <th>Emai</th>
                <th>Orders</th>
            </tr>
            </thead>
            <tbody>
            <input type="hidden" id="usr" name="usr" value="${users}">
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.name}</td>
                    <td>${user.surname}</td>
                    <td>${user.login}</td>
                    <td>${user.email}</td>
                    <td>
                        <div id="feedback${user.id}"></div>
                        <button  class="btn btn-primary" type="button" id="${user.id}"
                                 data-pid="${user.id}"> Get orders</button>
                    </td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
</c:if>

<script>
    $(document).ready(function() {
        $("[data-pid]").bind("click", function(event) {
            event.preventDefault();
            var pid = $(this).data("pid");
            alert(pid);
            var search = {};
            search["userId"] = pid;
            alert(search);
            alert(search.userId);
            $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "${home}search/api/getUserOrders",
                data : JSON.stringify(search),
                dataType : 'json',
                timeout : 100000,
                success : function(data) {
                    console.log("SUCCESS: ", data);
                    display(data, pid);
                },
                error : function(e) {
                    console.log("ERROR: ", e);
                    display(e, pid);
                },
                done : function(e) {
                    console.log("DONE");
                }
            });
        });


    });

    function display(data, pid) {
        alert(data.result);
        alert(data.result.length);
        var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
        var feedbackId = "#feedback" + pid;
        $(feedbackId).html(json);

    }

</script>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/resources/js/lib/bootstrap.js"></script>
</body>
</html>