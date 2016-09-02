<%@ page isELIgnored="false"%>
<jsp:include page="joint.jsp"/>
<link rel="stylesheet" href="style.css">

<title>Login Success Page</title>
<span id="textAlign">
    <h3>Login successful! 
        <br>Hi, ${user}!
        <br>Your Session ID = ${sessionID}
    </h3>
    <div  class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
        <div id="divButtons">
            <form action="search">
                <button type="submit" class="btn btn-sm btn-success"> Search Page</button>
            </form>
        </div>
        <div id="divButtons">
            <jsp:include page="logoutField.jsp"/>
        </div>
    </div>
</span>
