<%@ page isELIgnored="false"%>
<html>
    <head>
        <title>Login Success Page</title>
    </head>
    <body>
        <h3>Login successful. 
            <br>Hi ${user}!
            <br>Your Session ID=${sessionID}
        </h3>
        <br>
        <a href="search">Search Page</a>
        <br>
        <form action="LogoutServlet" method="post">
            <input type="submit" value="Logout" >
        </form>
    </body>
</html>