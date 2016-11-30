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
        <spring:message code="advert.edit"/>
    </title>

    <c:url var="home" value="/" scope="request" />

    <spring:url value="/resources/css/main.css" var="coreCss"/>
    <spring:url value="/resources/css/bootstrap.css"
                var="bootstrapCss"/>
    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${coreCss}" rel="stylesheet"/>

    <spring:url value="/resources/css/font-awesome.css" var="fontAwesomeCss" />
    <link href="${fontAwesomeCss}" rel="stylesheet" />
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">

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
    <script src="/resources/js/i18n/ru.js"></script>
    <script>
        $( document ).ready(function() {
            window.Parsley.setLocale($("#locale").val());
        });
    </script>

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
        <h3><spring:message code="msg.edit"/></h3>
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
                        <legend><spring:message code="advert"/></legend>
                        <label class="control-label" for="advertDescription">
                            <spring:message code="advert.description"/>
                        </label>
                        <input class="form-control" name="advertDescription" id="advertDescription" type="text"/>
                    </fieldset>
                </div>
                <div class="form-group">
                    <fieldset>
                        <legend><spring:message code="car"/></legend>
                        <label class="control-label" for="carMake">
                            <spring:message code="car.make"/>
                        </label>
                        <input class="form-control" name="carMake" id="carMake" type="text" required="required"/>
                        <label class="control-label" for="carModel" >
                            <spring:message code="car.model"/>
                        </label>
                        <input class="form-control" name="carModel" id="carModel" type="text" required="required"/>
                        <label class="control-label" for="carPrice">
                            <spring:message code="car.price"/>
                        </label>
                        <input class="form-control" id="carPrice" name="carPrice" type="text"
                               data-parsley-type="digits" data-parsley-max="2000000000"/>
                        <label class="control-label" for="carYear">
                            <spring:message code="car.year"/>
                        </label>
                        <input class="form-control" id="carYear" name="carPrice" type="text"
                               data-parsley-type="digits" data-parsley-length="[4, 4]" required="required"/>
                        <label class="control-label" for="carCondition">
                            <spring:message code="car.condition"/>
                        </label>
                        <input class="form-control" id="carCondition" name="carCondition" type="text" />
                        <label class="control-label" for="carDescription">
                            <spring:message code="car.description"/>
                        </label>
                        <input class="form-control" id="carDescription" name="carDescription" type="text" />
                    </fieldset>
                </div>

            <c:if test="${not empty advertId}">
                <input type="submit" class="btn btn-primary" value="<spring:message code='msg.edit'/>">
            </c:if>
            <c:if test="${empty advertId}">
                <input type="submit" class="btn btn-primary" value="<spring:message code='msg.add'/>">
            </c:if>
            </form>

        </div>
        <div class="col-md-4 col-lg-3" id="rateContainer">

        </div>
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

</body>
</html>