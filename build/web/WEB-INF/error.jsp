


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isErrorPage="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body >
        
        <h1>
            Error: ${pageContext.exception}  <br/>
        </h1>
        
         <img src="./assets/img/500.png">
         <br>
         <br>
         
         
         
         <h2>The server has encountered an error, this is the exception stack for debugging</h2>
        Exception stack trace:<br/>

        <c:forEach var="trace" items="${pageContext.exception.stackTrace}">
            ${trace}<br/>
        </c:forEach> 

    </body>
</html>
