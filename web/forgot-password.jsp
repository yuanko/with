<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Forgot Password</title>
        <%@ include file="include.jsp" %>
    </head>
    <body>
        <div id="header-container">
            <jsp:include page="WEB-INF/header.jsp" />
        </div>

         <form action="forgot-password.jsp" method="POST">
            <p>
                <label for="Email">Email</label>
                <input type="text" name="userid" id="uid" required>
            </p>
           
            <input type="submit" value="Reset" name="submit">
            
            </p>Don't have an account? <a href="register.jsp">Sign up</a>
            
            <hr>
        </form>
    </body>
</html>
