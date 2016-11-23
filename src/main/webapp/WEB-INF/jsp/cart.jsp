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
        <spring:message code="msg.cart"/>
    </title>

    <c:url var="home" value="/" scope="request" />

    <spring:url value="/resources/css/main.css" var="coreCss" />
    <spring:url value="/resources/css/bootstrap.css"
                var="bootstrapCss" />
    <link href="${bootstrapCss}" rel="stylesheet" />
    <link href="${coreCss}" rel="stylesheet" />

    <spring:url value="/resources/js/lib/jquery.1.10.2.min.js"
                var="jqueryJs" />
    <script src="${jqueryJs}"></script>

    <script type="text/javascript" src="<c:url value="/resources/js/app/service/RateService.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/app/service/CartService.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/app/controller/RateController.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/app/controller/CartController.js" />"></script>

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
                        <li>
                            <a href="/cart">
                                <spring:message code="msg.cart"/>
                                <span id="cartSize" class="badge">${cart.size()}</span>
                            </a>
                        </li>
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
                                    <a>${user.userName} </a>
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
        <h3><spring:message code="msg.cart"/></h3>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-8 col-lg-9" id="contentContainer">
            <c:forEach items="${cart}" var="advert">
                <div class="row panel panel-default" id="advertDiv${advert.id}">
                    <div class="col-md-8 col-lg-9">
                        <spring:message code="car.make"/>: ${advert.car.make}<br>
                        <spring:message code="car.model"/>: ${advert.car.model}<br>
                        <spring:message code="car.price"/>: ${advert.car.price}<br>
                        <spring:message code="car.year"/>: ${advert.car.year}<br>
                        <spring:message code="car.condition"/>: ${advert.car.condition}<br>
                        ${advert.description}
                    </div>
                    <div class="col-md-4 col-lg-3">
                        <a href="/advert/${advert.id}"><spring:message code="msg.more"/></a>
                        <button class="btn btn-primary removeAdvertFromCart" advert-id="${advert.id}">
                            <spring:message code="advert.remove"/>
                        </button>
                        <div id="removeAdvertFromCartMsg${advert.id}"></div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="col-md-4 col-lg-3" id="rateContainer">

        </div>
    </div>
</div>



<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/resources/js/lib/bootstrap.js"></script>
</body>
</html>