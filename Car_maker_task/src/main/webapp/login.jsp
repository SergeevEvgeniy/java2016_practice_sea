<title>Login Page</title> 
<jsp:include page="joint.jsp"/>
<link href="//netdna.bootstrapcdn.com/bootswatch/3.1.0/cerulean/bootstrap.min.css" rel="stylesheet">
<div class="row centered-form">
    <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Please Login</h3>
            </div>
            <div class="panel-body">
                <form role="form" action="LoginController" method="post">
                    <div class="form-group">
                        <input type="text" name="user" class="form-control input-sm" placeholder="User">
                    </div>
                    <div class="form-group">
                        <input type="password" name="pwd" class="form-control input-sm" placeholder="Password">
                    </div>
                    <input type="submit" value="Login" class="btn btn-info btn-block">
                </form>
            </div>
            <c:choose>
                <c:when test="${nonFault==false}">
                    <div class="form-group has-error">
                        <label class="control-label" for="inputError1">Fault of Authentification</label>
                    </div>
                </c:when>    
            </c:choose>
        </div>
    </div>
</div>