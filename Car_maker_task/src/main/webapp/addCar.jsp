<%@page contentType="text/html" pageEncoding="windows-1251"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<html>
    <head>
        <title>Create a car</title>
    </head>
    <body>
        <h1>Create car</h1>
        <form action=list method=post>
            <h2>Concern</h2>
            <p> Concern
                <select name = "concerns">
                    <c:forEach var ="maker" items="${makers}">
                        <option value="${maker.id}">${maker.name}</option>
                    </c:forEach>
                </select>
            </p>

            <h2>Car</h2>
            <p> Model 
                <input name="Car_Model_TB" type="text"/>
            </p>
            <p> Color 
                <input name="Car_Color_TB" type="text"/>
            </p>
            <p> Year 
                <input name="Car_Year_TB" type="text"/>
            </p>
            <p> 
                <Input name="Create_B" type="submit" value="Create!" />  
        </form>
    </body>
</html>
