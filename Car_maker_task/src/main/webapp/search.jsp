<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <body>
        <h2>What to look for?</h2>
        <form method=Post action="SSearch.java" >
            <p> Search 
                <input name="Search_TB" type="text"/>
                <Input name="Find_B" type="submit" value="Find" />  
            </p>
        </form>

        <form type=post action=addCar.jsp>
            <Input name="CreateNew_B" type="submit" value="New" />  
        </form>

        <form type=post action=list>
            <Input name="List_B" type="submit" value="List" />  
        </form>
    </body>
</html>