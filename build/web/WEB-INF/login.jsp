

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="./assets/styles/login.css">
        <title>Login</title>
    </head>
    <body>
        <div class="container">
            
            
      
        <h1 class="page-title">Home Inventory</h1>

        <form method="post" action="login">

            <label>Username:</label>
            <input type="text" placeholder="john" name="username" value="${username}" required></input>
            <br>
            <br>
            <label>Password:</label>
            <input type="password" name="password" value="${password}"  required >
            <br>
            <br>
            <input type="submit" value="login">


            <p> ${errorMessage}</p>

            <p>${logoutMessage}</p>
            
            <p>new account?</p>
           <a href="/Home_Inventory/register">Click here!</a>
        </form>
  </div> 
    </body>
</html>
