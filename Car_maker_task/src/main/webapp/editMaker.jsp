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
                <input name="Concern_Name_TB" type="text" value="${car.maker.name}"/>
            </p>
            <p> Adres 
                <input name="Concern_Adres_TB" type="text" value="${car.maker.adress}"/>
            </p>
            <p> FoundYear 
                <input name="Concern_FoundYear_TB" type="text" value="${car.maker.foundYear}"/>
            </p>

            <p> 
                <input type="hidden" name="Id_H_TB" value="${Id}" />
            </p>
        </form>
    </body>
</html>
