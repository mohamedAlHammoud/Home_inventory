 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Active Users</title>
    </head>
    <body>

        <form method="post" action="activeusers">


            <table class="table">

                <thead>
                    <tr>
                        <td>UserName</td>
                        <td>Number of Items</td>

                    </tr>
                </thead>

                <tbody>
                    <tr>


                <c:forEach items="${orderdUsers}" var="user">

                    <td>${user.username}</td>
                    <td> ${user.itemList.size()}</td>


                    </tr>

                </c:forEach>

                </tbody>

            </table>

        </form>

    </body>
</html>
