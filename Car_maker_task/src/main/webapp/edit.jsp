<%@page contentType="text/html" pageEncoding="windows-1251"%>
<%@ page isELIgnored="false"%>
<html>
    <head>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Edit car</h1>
        <form action=edit method=post>
            <h2>Concern</h2>
            <p> Name
                <input name="Concern_Name_TB" type="text" value="${car.maker}"/>
            </p>
            <p> Adres 
                <input name="Concern_Adres_TB" type="text" value="${car.maker.name}"/>
            </p>
            <p> FoundYear 
                <input name="Concern_FoundYear_TB" type="text" value="${car.getMaker().getFoundYear()}"/>
            </p>

            <h2>Car</h2>
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
