<html>
    <body>
        <jsp:include page="joint.jsp" flush="true"/>
        <h2>What to look for?</h2>
        <form method=Post action="search" >
            <p> Search 
                <input name="Search_TB" type="text"/>
                <Input name="Find_B" type="submit" value="Find" />  
            </p>
        </form>

        <form action=new>
            <Input name="CreateNew_B" type="submit" value="New" />  
        </form>

        <form action=list>
            <Input name="List_B" type="submit" value="List" />  
        </form>

        <br>
        <form action="LogoutServlet" method="post">
            <input type="submit" value="Logout" >
        </form>
    </body>
</html>