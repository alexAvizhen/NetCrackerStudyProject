<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 15.11.2016
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<body>
<h1><spring:message code="msg.403"/></h1>

<c:choose>
    <c:when test="${empty username}">
        <h2><spring:message code="msg.haveNotPermission"/></h2>
    </c:when>
    <c:otherwise>
        <h2><spring:message code="msg.username"/>: ${username} <br/>
            <spring:message code="msg.haveNotPermission"/></h2>
    </c:otherwise>
</c:choose>

</body>
</html>
