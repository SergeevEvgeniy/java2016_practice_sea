<jsp:include page="joint.jsp"/>

<div  class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
    <h2>What to look for?</h2>
    <form method=Post action="search" >
        <p> Search 
            <input name="Search_TB" type="text"/>
            <Input name="Find_B" type="submit" value="Find" />  
        </p>
    </form>

    <div id="divButtons">
        <form action=new>
            <button name="CreateNew_B" type="submit" class="btn btn-sm btn-success"> Create new!</button>
        </form>
        <form action=list>
            <button name="List_B" type="submit" class="btn btn-sm "> Show all</button>
        </form>
    </div>
</div>
<jsp:include page="logoutField.jsp"/>   
<form action=octopus.jsp>
    <button type="submit" class="btn btn-sm btn-warning"> Don't push me!</button>
</form>
