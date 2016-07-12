<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <title>Car List</title>
    </head>
    <body>
        <p>Car List</p>
        <ul>
            <c:forEach var="car" items="${cars}">
                <li>
                    <c:out value="${car.maker.name} ${car.model} ${car.year} ${car.color}"/>

                </li>
            </c:forEach>
        </ul>
    </body>
</html>
