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
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta firstName="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>
        <spring:message code="msg.login"/>
    </title>

    <c:url var="home" value="/" scope="request" />

    <spring:url value="/resources/css/main.css" var="coreCss" />
    <spring:url value="/resources/css/bootstrap.css"
                var="bootstrapCss" />
    <link href="${bootstrapCss}" rel="stylesheet" />
    <link href="${coreCss}" rel="stylesheet" />
    <spring:url value="/resources/css/font-awesome.css" var="fontAwesomeCss" />
    <link href="${fontAwesomeCss}" rel="stylesheet" />
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">

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
                    <a class="navbar-brand" href="/"> Company logo</a>
                </div>
                <div class="collapse navbar-collapse" id="responsive-menu">
                    <ul class="nav navbar-nav">
                        <li><a href="/"><spring:message code="msg.main"/></a> </li>
                        <li><a href="/advert"><spring:message code="msg.adverts"/></a></li>
                        <li><a href="#"><spring:message code="msg.discounts"/></a> </li>
                        <li><a href="#"><spring:message code="msg.contacts"/></a> </li>
                        <li><a href="/cart"><spring:message code="msg.cart"/><span class="badge">${cart.size()}</span></a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <a href="?locale=en">
                            <spring:message code="msg.en"/>
                        </a>
                        |
                        <a href="?locale=ru">
                            <spring:message code="msg.ru"/>
                        </a>
                        <sec:authorize access="!isAuthenticated()">
                            <li>
                                <a href="/login">
                                    <span class="glyphicon glyphicon-log-in"></span>
                                    <spring:message code="msg.login"/>
                                </a>
                            </li>
                        </sec:authorize>
                        <sec:authorize access="isAuthenticated()">
                            <sec:authentication var="user" property="principal" />
                            <c:if test="${user.userName != null}">
                                <li id="user-name-label">
                                    <a>
                                        <span class="glyphicon glyphicon-user"></span>
                                        ${user.userName}
                                    </a>
                                </li>
                            </c:if>

                            <li>
                                <form action="<c:url value="/j_spring_security_logout"/>" method="post" class="navbar-form">
                                    <button type="submit" class="btn btn-link navbar-btn">
                                        <span class="glyphicon glyphicon-log-out"></span>
                                        <spring:message code="msg.logout"/>
                                    </button>
                                </form>
                            </li>
                        </sec:authorize>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="container">
    <div class="row">
        <h3><spring:message code="msg.login"/></h3>
    </div>
</div>

<div class="container">
    <div class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4">
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <c:if test="${not empty msg}">
            <div class="msg">${msg}</div>
        </c:if>
        <form method="post" action="<c:url value='/j_spring_security_check' />">
            <div class="panel panel-default ir-login-panel">
                <div class="panel-body">
                    <div class="form-group">
                        <label class="control-label" for="username">
                            <spring:message code="msg.username"/>
                        </label>
                        <input class="form-control" id="username" name="username" required="required" title="" type="text" />
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="password">
                            <spring:message code="msg.password"/>
                        </label>
                        <input class="form-control" id="password" name="password" required="required" title="" type="password" />
                    </div>
                    <button class="btn btn-lg btn-primary btn-block" type="submit" value="login">
                        <spring:message code="msg.login"/>
                    </button>
                </div>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
    </div>
</div>

<div class="container" id="footer">
    <hr />
    <div class="text-center center-block">
        <p class="txt-railway">- avizhen.com -</p>
        <br />
        <a href="https://vk.com/alex_avizhen"><i class="fa fa fa-vk fa-3x social"></i></a>
        <a href="https://plus.google.com/116724968968879958223"><i class="fa fa-google-plus-square fa-3x social"></i></a>
        <a href="mailto:alex.avizhen97@gmail.com"><i class="fa fa-envelope-square fa-3x social"></i></a>
    </div>
    <hr />
</div>



<script>
    $(document).ready(function() {
        $("#username").focus();
    });

</script>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/resources/js/lib/bootstrap.js"></script>
</body>
</html>