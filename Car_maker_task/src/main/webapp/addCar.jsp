<%@page contentType="text/html" pageEncoding="windows-1251"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <title>Create a car</title>
    </head>
    <body>
        <h1>Create carrrrR</h1>
        <link rel="alternate" href="writer.jsp">
        <h2>Concern</h2>
        <p> Name
            <input name="Concern_Name_TB" type="text"/>
        </p>
        <p> Adres 
            <input name="Concern_Adres_TB" type="text"/>
        </p>
        <p> FoundYear 
            <input name="Concern_FoundYear_TB" type="text"/>
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
        <form type=post action=list  method=post >
            <Input name="Create_B" type="submit" value="Create!" />  
        </form>
    </p>
</body>
</html>