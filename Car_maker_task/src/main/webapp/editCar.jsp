<%@page contentType="text/html" pageEncoding="windows-1251"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<html>
    <head>
        <title>Edit car</title>
    </head>
    <body>
        <h1>Edit car</h1>
        <form action=edit method=post>
            <h2>Car</h2>
            <p> Concern
                <select name = "concerns">
                    <c:forEach var ="maker" items="${makers}">
                        <option value="${maker.id}" 
                                ${maker.id == car.maker.id ? 'selected=""' : ''}>
                            ${maker.name}
                        </option>
                    </c:forEach>
                </select>
            </p>
            <p> Model 
                <input name="Car_Model_TB" type="text" value="${car.model}"/>
            </p>
            <p> Color 
                <input name="Car_Color_TB" type="text" value="${car.color}"/>
            </p>
            <p> Year
                <input name="Car_Year_TB" type="text" value="${car.year}"/>
            </p>
            <p> 
                <Input name="Submit_B" type="submit" value="Edit" />  
            </p>
            <p> 
                <input type="hidden" name="Id_H_TB" value="${Id}" />
            </p>
        </form>
    </body>
</html>
