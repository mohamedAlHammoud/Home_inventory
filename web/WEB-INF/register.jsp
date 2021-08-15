

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link type="text/css" rel="stylesheet" href="./assets/styles/register.css">
        <title>Registration</title>
    </head>
    <body>
        <h1 >Register</h1>


        <div class="container"> 
      

        <form method="post" action="register">

            <label for="username">Username: </label>
            <input type="text" name="newUserName" id="" required>
            <br><br>

            <label for="newEmail">Email: </label>
            <input type="email" name="newEmail" id="" required>
            <br><br>

            <label for="newFirstName">First Name: </label>
            <input type="text" name="newFirstName" id="" required>
            <br><br>
            <label for="newLastName">Last Name: </label>
            <input type="text" name="newLastName" id="" required>
            <br><br>
            <label for="password">Password: </label>
            <input type="password" name="newpassword" id="" required> 
            <br><br>


            <input type="submit" value="Save">
            <input type="hidden" name="saveNewUser" > 

        </form>
              </div>
    </body>
</html>
