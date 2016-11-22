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
    <title>Main</title>

    <c:url var="home" value="/" scope="request" />

    <spring:url value="/resources/css/main.css" var="coreCss"/>
    <spring:url value="/resources/css/bootstrap.css"
                var="bootstrapCss"/>
    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${coreCss}" rel="stylesheet"/>

    <spring:url value="/resources/js/lib/jquery.1.10.2.min.js"
                var="jqueryJs"/>
    <spring:url value="/resources/js/lib/jquery.numeric.js"
                var="jqueryNumeric"/>
    <script src="${jqueryJs}"></script>

    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/resources/js/lib/bootstrap.js"></script>

    <script src='http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js'></script>

    <script src="${jqueryNumeric}"></script>

    <script src="/resources/js/lib/parsley.min.js"></script>

    <script type="text/javascript" src="<c:url value="/resources/js/app/service/AdvertService.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/app/controller/EditAdvertController.js" />"></script>

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
                        <li><a href="/">Главная</a> </li>
                        <li><a href="/advert">Объявления</a></li>
                        <li><a href="#">Акции и скидки</a> </li>
                        <li><a href="#">Контакты</a> </li>
                        <li><a href="#">Корзина<span class="badge">3</span></a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <sec:authorize access="!isAuthenticated()">
                            <li>
                                <a href="/login"><span class="glyphicon glyphicon-log-in"></span> Login</a>
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
                                        Logout
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
        <h3>Edit advert</h3>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-8 col-lg-9" id="contentContainer">
            <c:if test="${not empty msg}">
                <div id="msg">
                    <h3>${msg}</h3>
                </div>
            </c:if>
            <div class="msg" id="msg">

            </div>
            <form action="/admin/advert/save" method="post" id="editForm" data-parsley-validate>
                <c:if test="${not empty advertId}">
                    <input type="hidden" name="advertId" id="advertId" value="${advertId}">
                </c:if>
                <c:if test="${empty advertId}">
                    <input type="hidden" name="advertId" id="advertId" value="-1">
                </c:if>
                <c:if test="${not empty carId}">
                    <input type="hidden" name="carId" id="carId" value="${carId}">
                </c:if>
                <c:if test="${empty carId}">
                    <input type="hidden" name="carId" id="carId" value="-1">
                </c:if>
                <div class="form-group">
                    <fieldset>
                        <legend>Advert</legend>
                        <label class="control-label" for="advertDescription">
                            Advert description
                        </label>
                        <input class="form-control" name="advertDescription" id="advertDescription" type="text"/>
                    </fieldset>
                </div>
                <div class="form-group">
                    <fieldset>
                        <legend>Car</legend>
                        <label class="control-label" for="carMake">
                            Make
                        </label>
                        <input class="form-control" name="carMake" id="carMake" type="text" required="required"/>
                        <label class="control-label" for="carModel" >
                            Model
                        </label>
                        <input class="form-control" name="carModel" id="carModel" type="text" required="required"/>
                        <label class="control-label" for="carPrice">
                            Price
                        </label>
                        <input class="form-control" id="carPrice" name="carPrice" type="text"
                               data-parsley-type="digits" data-parsley-max="2000000000"/>
                        <label class="control-label" for="carYear">
                            Year
                        </label>
                        <input class="form-control" id="carYear" name="carPrice" type="text"
                               data-parsley-type="digits" data-parsley-length="[4, 4]" required="required"/>
                        <label class="control-label" for="carCondition">
                            Condition
                        </label>
                        <input class="form-control" id="carCondition" name="carCondition" type="text" />
                        <label class="control-label" for="carDescription">
                            Description
                        </label>
                        <input class="form-control" id="carDescription" name="carDescription" type="text" />
                    </fieldset>
                </div>

            <c:if test="${not empty advertId}">
                <input type="submit" class="btn btn-primary" value="Edit">
            </c:if>
            <c:if test="${empty advertId}">
                <input type="submit" class="btn btn-primary" value="Add">
            </c:if>
            </form>

        </div>
        <div class="col-md-4 col-lg-3" id="rateContainer">

        </div>
    </div>
</div>

</body>
</html>