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
                    <a href="/Car_maker_task/edit?Id=${car.key}">
                        <c:out value="${car.value.maker.name} ${car.value.model} ${car.value.year} ${car.value.color}"/>
                    </a>
                </li>
            </c:forEach>
        </ul>
        <form  action="Search.java" >
            <p> 
                <Input name="Back_B" type="submit" value="Back to Main" />  
            </p>
        </form>
    </body>
</html>
