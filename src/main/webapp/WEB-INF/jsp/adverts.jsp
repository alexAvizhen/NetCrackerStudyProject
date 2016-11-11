<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 28.10.2016
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Adverts</title>

    <c:url var="home" value="/" scope="request"/>

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

    <script type="text/javascript" src="<c:url value="/resources/js/app/service/AdvertService.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/app/service/RateService.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/app/service/CarService.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/app/controller/AdvertsController.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/app/controller/RateController.js" />"></script>

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
                        <li><a href="/">Главная</a></li>
                        <li><a href="/advert">Объявления</a></li>
                        <li><a href="#">Акции и скидки</a></li>
                        <li><a href="#">Контакты</a></li>
                        <li><a href="#">Корзина<span class="badge">3</span></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="container">
    <div class="row">
        <h3>Adverts</h3>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-8 col-lg-9" id="advertContainer">

        </div>
        <div class="col-md-4 col-lg-3">

            <div id="chooseCarContainer">
                <form id="findCarsForm">
                    <div class="form-group">
                        <label for="carMakesSelect">Марка машины</label>
                        <select class="form-control" id="carMakesSelect">
                        </select>
                    </div>
                    <div class="form-group row">
                        <div class="col-lg-6 col-md-6">
                            <label for="priceFrom">Цена от</label>
                            <input type="text" id="priceFrom" placeholder="цена от" class="positive-integer">
                        </div>
                        <div class="col-lg-6 col-md-6">
                            <label for="priceTo">до (БР)</label>
                            <input type="text" id="priceTo" placeholder="цена до" class="positive-integer">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-lg-6 col-md-6">
                            <label for="yearFrom">Год от</label>
                            <input type="text" id="yearFrom" placeholder="год от" class="positive-integer">
                        </div>
                        <div class="col-lg-6 col-md-6">
                            <label for="yearTo">до (БР)</label>
                            <input type="text" id="yearTo" placeholder="год до" class="positive-integer">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-6 col-md-6">
                            <button type="submit" class="btn btn-primary">Подобрать</button>
                        </div>
                        <div class="col-lg-6 col-md-6">
                            <button type="button" class="btn btn-primary" id="clearBtn">Очистить</button>
                        </div>
                    </div>
                </form>
            </div>
            <hr>

            <div id="rateContainer">

            </div>
        </div>
    </div>
</div>

</body>
</html>