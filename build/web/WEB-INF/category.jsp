
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="./assets/styles/main-style.css">
        <title>Manage Categories</title>
    </head>
    <body>


        <div class="flex_container">


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
            <h1>Manage Categories</h1>
            <table class="table">


                <div class="table-headers">
                    <thead>
                        <tr>
                            <th class="category">Category Name</th>
                            <th class="span">Edit</th>
                        </tr>
                    </thead>

                </div>
                <c:forEach  items="${category}" var="c">


                    <tr>
                    <form method="post" action="categories">
                        <td data-label="Category Name"><span name="category" >${c.categoryName}</span></td>
                        <td data-label="Edit"><input type="submit" value="Edit"></td>
                        <input type="hidden" name="editButton" value="${c.categoryID}"> 

                    </form>

                    </tr>



                </c:forEach>

            </table>





            <div class="forms">


                <!--            ADDING THE category-->
                <div class="add-category"> 


                    <h2>Add Category</h2>
                    <form method="post" action="categories">

                        <label for="username">Category Name </label>
                        <input type="text" name="newCategoryName" id="" required>
                        <input type="submit" value="Save">
                        <input type="hidden" name="saveNewCategory" > 

                    </form>

                </div>   
                <br>
                <br>

                <c:if test="${isClicked==true}">


                    <!--        EDIT category-->
                    <div class="edit-category">

                        <h2>Edit Category</h2>
                        <form method="post" action="categories">

                            <label for="categoryName">Category </label>
                            <input type="text" name="editedCategoryName" id="" value="${categoryName}" >
                            <br><br>
                            <input type="submit" value="Save">
                            <input type="hidden" name="saveEditedCategory" >



                        </form>

                        <form method="post" action="admin">
                            <input type="submit" name="clearFields" value="Cancel">
                        </form>
                    </div>
                </c:if>
            </div>
        </div>
    </body>
</html>
