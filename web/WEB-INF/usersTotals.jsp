
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Users Items' total</title>
    </head>
    <body>

        <form method="post" action="usertotals">


            <table class="table">

                <thead>
                    <tr>
                        <td>UserName</td>
                        <td>Number of Items</td>
                        <td>total</td>
                    </tr>
                </thead>

                <tbody>
                    <tr>


                        <c:forEach items="${users}" var="user">

                            <td data-label="Username">${user.username}</td>
                            <td data-label="Number of Items"> ${user.itemList.size()}</td>

                            <td data-label="Total">  <c:out value= "${user.itemList.stream().map(e -> e.getPrice()).sum()}"/></td>
                        </tr>

                    </c:forEach>

                </tbody>

            </table>

        </form>

    </body>
</html>
