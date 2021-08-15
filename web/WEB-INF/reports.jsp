
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="./assets/styles/main-style.css">
        <title>Reports</title>
    </head>
    <body>


        <header class="header">
                <h1 class="logo"><a href="#">Home Inventory</a></h1>
                <ul class="main-nav">
                    <li > <a href="/Home_Inventory/inventory">Inventory</a></li>
                    <li > <a href="/Home_Inventory/admin">Admin</a></li>

                    <li > <a href="/Home_Inventory/categories">Manage Item Categories</a></li>
                    <li > <a href="/Home_Inventory/reports">Reports</a></li>
                    <li > <a href="/Home_Inventory/login?logout">Logout</a></li>
                </ul>
            </header> 
        <h1>Reports</h1>

        <h3>Choose a report you would like to generate as an Excel file</h3>
    <li > <a href="/Home_Inventory/usertotals">Users & items totals</a></li>

    <li > <a href="/Home_Inventory/activeusers">Active users</a></li>

</body>
</html>
