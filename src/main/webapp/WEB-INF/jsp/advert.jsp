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
        <spring:message code="advert"/>
    </title>

    <c:url var="home" value="/" scope="request" />

    <spring:url value="/resources/css/main.css" var="coreCss" />
    <spring:url value="/resources/css/bootstrap.css"
                var="bootstrapCss" />
    <link href="${bootstrapCss}" rel="stylesheet" />
    <link href="${coreCss}" rel="stylesheet" />

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/resources/js/lib/bootstrap.js"></script>

    <spring:url value="/resources/js/lib/jquery.1.10.2.min.js" var="jqueryJs" />
    <spring:url value="/resources/js/lib/jquery.i18n.properties-min-1.0.9.js" var="jqueryi18n"/>

    <script src="${jqueryJs}"></script>

    <script src="${jqueryi18n}"></script>

    <script type="text/javascript" src="<c:url value="/resources/js/app/service/CarService.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/app/service/AdvertService.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/app/service/CartService.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/app/controller/AdvertController.js" />"></script>



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
                        <input type="hidden" id="locale" value="${pageContext.response.locale}"/>
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
        <h3><spring:message code="advert"/></h3>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-8 col-lg-9" id="advertContainer" advert-id="${advertId}">

        </div>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <form action="/admin/advert/edit" method="post">
                <input type="hidden" name="editAdvertId" id="editAdvertId" value="${advertId}">
                <button type="submit" class="btn btn-primary" id="editAdvert">
                    <spring:message code="advert.edit"/>
                </button>
            </form>
            <form action="/admin/advert/delete" method="post">
                <input type="hidden" name="deleteAdvertId" id="deleteAdvertId" value="${advertId}">
                <button type="submit" class="btn btn-danger">
                    <spring:message code="advert.remove"/>
                </button>
            </form>
        </sec:authorize>
        <form id="addAdvertToCartForm">
            <input type="hidden" name="advertId" id="advertId" value="${advertId}">
            <button type="submit" class="btn btn-primary">
                <spring:message code="msg.addToCart"/>
            </button>
            <div id="addAdvertToCartMsg"></div>
        </form>
    </div>
</div>


</body>
</html>