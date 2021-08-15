 

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="./assets/styles/main-style.css">
        <title>JSP Page</title>
    </head>
    <body>

        <header class="header">
            <h1 class="logo"><a href="#">Home Inventory</a></h1>
            <ul class="main-nav">
                <li > <a href="/Home_Inventory/inventory">Inventory</a></li>
                <li > <a href="/Home_Inventory/admin">Admin</a></li>

                <li > <a href="/Home_Inventory/categories">Manage Item Categories</a></li>
                <li > <a href="/Home_Inventory/reports">Reports</a></li>
                <a href="/Home_Inventory/editUserInfo">Edit My Info</a>
                <li > <a href="/Home_Inventory/login?logout">Logout</a></li>
            </ul>
        </header>
        <h1>Edit personal Information for ${username}</h1>


        <div class="edit-user">





            <form method="post" action="editUserInfo">

                <label for="username">Username: </label>
                <input type="text" name="username" id="" value="${username}" readonly>

                <br><br>
                <label for="firstName">first name:  </label>
                <input type="text" name="firstName" id="" value="${userFName}"  placeholder="First Name">
                <br><br>
                <label for="lastName">Lastname </label>
                <input type="text" name="lastName" id="" value="${userLName}"  placeholder="First Name">
                <br><br>


                <br><br>
                <input type="submit" value="Save">
                <input type="hidden" name="saveUserNewInfo" value="${username}">

                <p> ${systemMessage}</p>



            </form>
            <form method="post" action="editUserInfo">
                <input type="submit" value="Deactivate Account">
                <input type="hidden" name="deactivateAccount">
            </form>
        </div>
    </body>
</html>
